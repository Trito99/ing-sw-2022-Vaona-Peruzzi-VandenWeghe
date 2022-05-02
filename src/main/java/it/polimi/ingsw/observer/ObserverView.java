package it.polimi.ingsw.observer;

/** interfaccia Observer per la view */
public interface ObserverView {

    /** da implementare con la cli */

    /** cerca di connettere un giocatore ad una data lobby */
    public void updateLobby(String username, String gameID);

}
