package it.polimi.ingsw.observer;

import it.polimi.ingsw.message.GeneralMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Default Observable class
 */

public class Observable {

    private final List<Observer> observerList = new ArrayList<>();

    /**
     * Adds an observer
     * @param observer the observer to be added in the list.
     */
    public void add(Observer observer) {
        observerList.add(observer);
    }

    /**
     * Notifies all the observer with a message
     * @param message notifed to the observers
     */
    protected void notifyObserver(GeneralMessage message) {
        for (Observer observer : observerList) {
            observer.update(message);
        }
    }

    /**
     * Removes an observer
     * @param observer to remove
     */
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

}
