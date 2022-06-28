package it.polimi.ingsw.network;

import it.polimi.ingsw.message.ClientMessage;
import it.polimi.ingsw.message.ServerMessage;
import it.polimi.ingsw.observer.Observable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** implementa le connessioni tra client e server, lato client */

public class ClientSocket extends Observable {
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ExecutorService queue;

    /** default constructor */
    public ClientSocket(String address, int port) throws IOException {
        try {
            this.client = new Socket(address, port);
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());
            this.queue = Executors.newSingleThreadExecutor();

        } catch (Exception exception) {
            throw new IOException();
        }
    }

    /** send a message to the server
     *
     * @param message
     */
    public void sendMessage(ClientMessage message) {
        try {
            output.writeObject(message);
            output.reset();
        } catch (IOException e) {
            disconnect();
        }
    }


    /**
     * listen messages from the server
     */
    public void listen() {
        queue.execute(() ->
        {
            while (!queue.isShutdown()) {
                ServerMessage message;
                try {
                    message = (ServerMessage) input.readObject();
                    notifyObserver(message);
                } catch (Exception e) {
                    disconnect();
                }
            }
        });
    }


    /**
     * disconnect client from the server
     */
    public void disconnect(){
        if (!client.isClosed()) {
            queue.shutdownNow();
            try {
                client.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

}
