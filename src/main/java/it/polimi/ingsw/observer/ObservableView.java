package it.polimi.ingsw.observer;

import it.polimi.ingsw.view.GUI.StartGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class
ObservableView {
    protected final List<ObserverView> observers = new ArrayList<>();
    protected StartGUI main;

    /** aggiunge observer */
    public void addObserver(ObserverView observer) {
        observers.add(observer);
    }

    /** aggiunge lista di observer */
    public void addAllObservers(List<ObserverView> observerList) {
        observers.addAll(observerList);
    }

    /** rimuove observer */
    public void removeObserver(ObserverView observerView) {
        observers.remove(observerView);
    }

    /** rimuove lista di observer */
    public void removeAllObservers(List<ObserverView> observerList) {
        observers.removeAll(observerList);
    }

    /** CONTROLLARE !! */
    /** notifica tutti i current observer tramite argomento (lambda) chiamato dall'observer */
    protected void notifyObserver(Consumer<ObserverView> lambda) {
        for (ObserverView observer : observers) {
            lambda.accept(observer);
        }
    }

    /** da verificare
    @FXML
    protected void handleCloseButton(){
        this.observers.removeAll(observers);
    }*/
}
