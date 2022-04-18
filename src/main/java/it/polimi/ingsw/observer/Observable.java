package it.polimi.ingsw.observer;

import java.util.ArrayList;
import java.util.List;

/** classe Observable */

public class Observable {

    private final List<Observer> observerList = new ArrayList<>();

    /** aggiungi observer */
    public void add(Observer observer) {
        observerList.add(observer);
    }
}
