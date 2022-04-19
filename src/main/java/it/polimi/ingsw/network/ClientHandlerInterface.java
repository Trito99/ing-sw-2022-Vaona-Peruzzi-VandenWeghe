package it.polimi.ingsw.network;


import it.polimi.ingsw.message.GeneralMessage;

public interface ClientHandlerInterface {

    /**
     *Sends a message to the client
     * @param message the message to send
     */
    public void sendMessage(GeneralMessage message);

    /**
     *Disconnect the socket from the server.
     */
    public void disconnect();

}
