package it.polimi.ingsw.network;

import it.polimi.ingsw.message.GeneralMessage;

/**
 * Handles messages sent to a client
 */
public interface ClientHandlerInterface {
    /**
     * Sends a message to the client
     * @param message sent
     */
    void sendMessage(GeneralMessage message);

    /**
     * Disconnects the socket from the server
     */
    void disconnect();
}
