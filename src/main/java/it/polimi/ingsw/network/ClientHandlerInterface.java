package it.polimi.ingsw.network;


import it.polimi.ingsw.message.GeneralMessage;

public interface ClientHandlerInterface {

    /** send a message to the client
     *
     * @param message
     */
    void sendMessage(GeneralMessage message);

    /**
     * disconnect the socket from the server
     */
    void disconnect();

}
