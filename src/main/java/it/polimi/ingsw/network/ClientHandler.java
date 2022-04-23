package it.polimi.ingsw.network;

import it.polimi.ingsw.message.ClientMessage;
import it.polimi.ingsw.message.GeneralMessage;
import it.polimi.ingsw.message.LoginRequest;
import it.polimi.ingsw.message.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements ClientHandlerInterface /** Runnable */ {
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Lobby lobby;
    private LobbyServer lobbyServer;
    private String gameId;
    private final Object lockSendMessage;
    private final Object lockHandleMessage;

    /** costruttore di default */
    /**
     * @param client la socket client a cui vengono inviati i messaggi
     * @param lobbyServer la lobby server di default
     */
    public ClientHandler(Socket client, LobbyServer lobbyServer) {
        this.client = client;
        this.lobbyServer=lobbyServer;
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

    /** disconnete la socket dal server */
    public void disconnect() {
        try {
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.currentThread().interrupt();
        lobbyServer.leaveLobby(gameId, this);
    }

    /** invia messaggio al client */
    public void sendMessage(GeneralMessage message) {
        try{
            synchronized (lockSendMessage) {
                output.writeObject((Object) message);
                output.reset();
            }
        }
        catch(IOException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
            disconnect();
        }

    }

    /** capire SE SERVE o da togliere */
    /** gestisce messaggio inviato dal client */
    private void handleMessage() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (lockHandleMessage) {
                    ClientMessage message = (ClientMessage) input.readObject();

                    if (message.getMessageType() == MessageType.LOGIN) {
                        LoginRequest loginMsg = (LoginRequest) message;
                        lobby = (lobbyServer.getLobby(loginMsg.getGameId()));
                        gameId=loginMsg.getGameId();
                        lobby.addPlayer(loginMsg.getNickname(), this);
                    } else if (lobby != null) {
                        lobby.getMessage(message);
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("invalid stream from client");
            disconnect();
        }
    }

}
