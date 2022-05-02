package it.polimi.ingsw.observer;

/** interfaccia Observer per la view */
public interface ObserverView {

    /** da implementare con la cli */

    /** cerca di connettere un giocatore ad una data lobby */
    public void updateLobby(String username, String gameID);

    /** cerca di connettere un client ad una socket */
    public void updateConnect(String address, int port);

    /** carica nuovo nickname */
    public void createNickname(String nickname);

    /** invia messaggio al server con il numero di giocatori scelti dal giocatore */
    public void choosePlayersNumber(int playersNumber);
}
