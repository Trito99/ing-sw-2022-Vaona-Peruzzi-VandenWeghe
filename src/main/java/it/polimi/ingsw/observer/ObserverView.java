package it.polimi.ingsw.observer;

import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.school.TColor;

import java.util.GregorianCalendar;

/** interfaccia Observer per la view */
public interface ObserverView {

    /** cerca di connettere un giocatore ad una data lobby */
    void updateLobby(String nickname, GregorianCalendar playerDate, String gameID);

    /** cerca di connettere un client ad una socket */
    void updateConnect(String address, int port);


    void askLobbyServerInfo();

    /** cerca di disconnettere un client da una socket */
    void updateDisconnect();

    /** carica nuovo nickname */
    void createNickname(String nickname);


    void createPlayerDate(GregorianCalendar playerDate);

    /** invia messaggio al server con il numero di giocatori scelti dal giocatore */
    void choosePlayersNumberAndDifficulty(int playersNumber, Difficulty difficulty);


    void chooseTowerColorAndDeck(TColor towerColorChosen, AssistantDeckName assistantDeckChosen);


    void chooseAssistantCard(String cardNickname);


    void chooseCloudCard(int id);


    void choosePlaceAndStudentForMove(String place,int id);


    void chooseId(int id, boolean choice, int index, boolean none);


    void chooseMotherEarthSteps(int steps, int maxSteps, String string);


    void chooseCharacterCard(String characterNickname, boolean choice);


    void chooseColorToBlock(String color);

}
