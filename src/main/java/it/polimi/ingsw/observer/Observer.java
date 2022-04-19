package it.polimi.ingsw.observer;
//import it.polimi.ingsw.messages.GeneralMessage;

import it.polimi.ingsw.message.GeneralMessage;

/**
 * Observer interface for generic update.
 */
public interface Observer {

    void update(GeneralMessage message);

}