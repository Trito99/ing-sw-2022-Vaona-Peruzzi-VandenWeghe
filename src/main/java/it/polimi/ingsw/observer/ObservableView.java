package it.polimi.ingsw.observer;

import it.polimi.ingsw.view.GUI.StartGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class
ObservableView {
    protected final List<ObserverView> observers = new ArrayList<>();
    protected StartGUI main;

    public void addObserver(ObserverView observer) {
        observers.add(observer);
    }

    public void addAllObservers(List<ObserverView> observerList) {
        observers.addAll(observerList);
    }

    public void removeObserver(ObserverView observerView) {
        observers.remove(observerView);
    }


    public void removeAllObservers(List<ObserverView> observerList) {
        observers.removeAll(observerList);
    }

    protected void notifyObserver(Consumer<ObserverView> lambda) {
        for (ObserverView observer : observers) {
            lambda.accept(observer);
        }
    }

}
