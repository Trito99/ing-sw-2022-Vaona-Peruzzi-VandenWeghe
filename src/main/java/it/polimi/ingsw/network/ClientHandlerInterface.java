package it.polimi.ingsw.network;


import it.polimi.ingsw.message.GeneralMessage;

public interface ClientHandlerInterface {

    /** invia messaggio al client */
    public void sendMessage(GeneralMessage message);

    /** disconnette la socket dal server */
    public void disconnect();

}