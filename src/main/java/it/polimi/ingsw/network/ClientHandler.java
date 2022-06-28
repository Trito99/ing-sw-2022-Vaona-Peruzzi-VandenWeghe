package it.polimi.ingsw.network;

import it.polimi.ingsw.message.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/** rappresenta il client a lato server.
 * Gestisce i messaggi ricevuti dal client, e invia i messaggi del GameController al client */

public class ClientHandler implements ClientHandlerInterface, Runnable {
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Lobby lobby;
    private LobbyServer lobbyServer;
    private String gameId;
    private final Object lockSendMessage;
    private final Object lockHandleMessage;

    /** Default constructor
     *
     * @param client la socket client a cui vengono inviati i messaggi
     * @param lobbyServer la lobby server di default
     */
    public ClientHandler(Socket client, LobbyServer lobbyServer) {
        this.client = client;
        this.lobbyServer = lobbyServer;
        lockHandleMessage = new Object();
        lockSendMessage = new Object();
        lobby = null;

        try {
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * disconnect the socket from the server
     */
    public void disconnect() {
        try {
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.currentThread().interrupt();
        lobby.remove(this);
        lobbyServer.leaveLobby(gameId);
    }

    /** send a message to the client
     *
     * @param message
     */
    public void sendMessage(GeneralMessage message) {
        try{
            synchronized (lockSendMessage) {
                output.writeObject((Object) message);
                output.reset();
            }
        }
        catch(SocketException socketException){
            Thread.currentThread().interrupt();
            disconnect();
        }
        catch(IOException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
            disconnect();
        }

    }

    /**
     * handles message sent by the client
     */
    private void handleMessage() {
        String nickname = null;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (lockHandleMessage) {
                    ClientMessage message = (ClientMessage) input.readObject();

                    if (message.getMessageType() == MessageType.LOBBY_SERVER_REQUEST) {
                        sendMessage(new LobbyServerInfo(lobbyServer.getNewLobbyMap()));
                    }
                    else if (message.getMessageType() == MessageType.LOGIN) {
                        LoginRequest loginMsg = (LoginRequest) message;
                        lobby = (lobbyServer.getLobby(loginMsg.getGameId()));
                        gameId = loginMsg.getGameId();
                        lobby.addPlayer(loginMsg.getNickname(), loginMsg.getPlayerDate(), this);
                        nickname = loginMsg.getNickname();
                    } else if (lobby != null) {
                        lobby.getMessage(message);
                    }
                }
            }
        }
        catch (EOFException eofException) {
            lobby.getGameController().endGame(null);
            disconnect();
        }
        catch (SocketException socketException) {
            lobby.getGameController().endGame(nickname);
            disconnect();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            lobby.getGameController().endGame(null);
            disconnect();
        }
    }


    @Override
    public void run() {
        System.out.println("Connected to " + client.getInetAddress());
        handleMessage();
    }
}
