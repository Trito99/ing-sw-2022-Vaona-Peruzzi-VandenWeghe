package it.polimi.ingsw.observer;

import it.polimi.ingsw.view.GUI.StartGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Default Observable class which can be observed from ObserverView
 */

public abstract class ObservableView {
    protected final List<ObserverView> observers = new ArrayList<>();

    /**
     * Adds an observer
     * @param observer added
     */
    public void addObserver(ObserverView observer) {
        observers.add(observer);
    }

    /**
     * Adds all the Observers to the list
     * @param observerList list of observers to be added
     */
    public void addAllObservers(List<ObserverView> observerList) {
        observers.addAll(observerList);
    }

    /**
     * Notifies all the observers through the lambda argument as default
     * @param lambda to be called on the observers
     */
    protected void notifyObserver(Consumer<ObserverView> lambda) {
        for (ObserverView observer : observers) {
            lambda.accept(observer);
        }
    }

}
