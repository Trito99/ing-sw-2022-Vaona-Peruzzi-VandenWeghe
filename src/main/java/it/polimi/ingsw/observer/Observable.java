package it.polimi.ingsw.observer;

import it.polimi.ingsw.message.GeneralMessage;

import java.util.ArrayList;
import java.util.List;

/** classe Observable */

public class Observable {

    private final List<Observer> observerList = new ArrayList<>();

    /** aggiungi observer */
    public void add(Observer observer) {
        observerList.add(observer);
    }

    /** rimuovi observer */
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    /** notifica tutti gli observers passandogli un messaggio */
    protected void notifyObserver(GeneralMessage message) {
        for (Observer observer : observerList) {
            observer.update(message);
        }
    }

}
