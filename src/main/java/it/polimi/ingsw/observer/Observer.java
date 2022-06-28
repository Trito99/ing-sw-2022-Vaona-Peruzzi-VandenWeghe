package it.polimi.ingsw.observer;

import it.polimi.ingsw.message.GeneralMessage;

/**
 * Default Observer interface
 */
public interface Observer {

    /**
     * Generic update
     * @param message sent for updates
     */
    void update(GeneralMessage message);

}