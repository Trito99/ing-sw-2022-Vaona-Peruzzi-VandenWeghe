package it.polimi.ingsw.network;

import it.polimi.ingsw.message.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Represents the client seen from the server
 * Handles messages received from the client and sends messages from the Game Controller  to the client
 */
public class ClientHandler implements ClientHandlerInterface, Runnable {
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Lobby lobby;
    private LobbyServer lobbyServer;
    private String gameId;
    private final Object lockSendMessage;
    private final Object lockHandleMessage;

    /**
     * Default constructor
     * @param client client socket to send messages to
     * @param lobbyServer default lobby server used
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
     * Disconnects the socket from the server
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

    /**
     * Sends a message to the client
     * @param message sent
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
     * Handles message sent by the client
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

    /**
     *  Executes thread service to read messages
     */
    @Override
    public void run() {
        System.out.println("Connected to " + client.getInetAddress());
        handleMessage();
    }
}
