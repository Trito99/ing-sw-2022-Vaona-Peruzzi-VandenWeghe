package it.polimi.ingsw.observer;

import it.polimi.ingsw.model.game.Difficulty;

import java.util.GregorianCalendar;

/** interfaccia Observer per la view */
public interface ObserverView {

    /** da implementare con la cli */

    /** cerca di connettere un giocatore ad una data lobby */
    public void updateLobby(String nickname, GregorianCalendar playerDate, String gameID);

    /** cerca di connettere un client ad una socket */
    public void updateConnect(String address, int port);

    public void endGame();

    /** carica nuovo nickname */
    public void createNickname(String nickname);

    public void createPlayerDate(GregorianCalendar playerDate);

    /** invia messaggio al server con il numero di giocatori scelti dal giocatore */
    public void choosePlayersNumberAndDifficulty(int playersNumber, Difficulty difficulty);

    public void chooseAssistantCard(String cardNickname);

    public void chooseCloudCard(int id);

    public void choosePlaceAndStudentForMove(String place,int id);

    public void chooseId(int id, boolean choice);

    public void chooseMotherEarthSteps(int steps);

    public void chooseCharacterCard(String characterNickname, boolean choice);

}
