package it.polimi.ingsw.observer;

import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.school.TColor;

import java.util.GregorianCalendar;

/**
 * Default Observer interface for the view
 */
public interface ObserverView {

    /**
     * Tries to log the player to a certain lobby
     */
    void updateLobby(String nickname, GregorianCalendar playerDate, String gameID);

    /**
     * Tries to connect a client (new player) to a server socket
     */
    void updateConnect(String address, int port);

    /**
     * Asks infos to a Lobby and notifies
     */
    void askLobbyServerInfo();

    /**
     * Disconnects a player from the game
     */
    void updateDisconnect();

    /**
     * Sets the player's nickname
     */
    void createNickname(String nickname);

    /**
     * Creates player's birthday date
     */
    void createPlayerDate(GregorianCalendar playerDate);

    /**
     * Sends a messagge to the server with the number of players and the difficulty of a match chosen
     * by the first player of a certain lobby
     * @param playersNumber number of players chosen for a match, in a certain lobby
     * @param difficulty chosen for a match, in a certain lobby
     */
    void choosePlayersNumberAndDifficulty(int playersNumber, Difficulty difficulty);

    /**
     * Sends a messagge to the server with the color of the towers and the assistant deck chosen by a player
     * @param towerColorChosen color of the towers chosen
     * @param assistantDeckChosen image of the assistant deck chosen
     */
    void chooseTowerColorAndDeck(TColor towerColorChosen, AssistantDeckName assistantDeckChosen);

    /**
     * Sends a messagge to the server with the assistant card chosen by the player
     * @param cardNickname string which identifies the assistant card chosen
     */
    void chooseAssistantCard(String cardNickname);

    /**
     * Sends a messagge to the server with the cloud card chosen by the player at the end of his turn
     * @param id of the cloud card parsed
     * @param idString of a character card (can be null)
     */
    void chooseCloudCard(int id, String idString);

    /**
     * Sends a message to the server with the new position of a student chosen by the player during the Action phase
     * @param place where the player wants to move the student
     * @param id of the student to move
     */
    void choosePlaceAndStudentForMove(String place,int id);

    /**
     * Sends a message to the server with a character card's effect which involves moving a certain student
     * @param id of the student to move
     * @param choice true for herald and curator effect, otherwise false
     * @param index for the effect of the Acrobat Card
     * @param none boolean used to finish the effect for the Acrobat Card (it can lasts up to 3 times)
     */
    void chooseId(int id, boolean choice, int index, boolean none);

    /**
     * Sends a message to the server with the new position of Mother Earth chosen by the player
     * @param steps of Mother Earth chosen
     * @param maxSteps maximum number of steps in accord to the value of the last assistant card chosen by the player
     * @param string of a character card (can be null)
     */
    void chooseMotherEarthSteps(int steps, int maxSteps, String string);

    /**
     * Sends a message to the server with the Character Card chosen by the player
     * @param characterNickname name of the Character Card chosen
     * @param choice set true when the card is activated
     */
    void chooseCharacterCard(String characterNickname, boolean choice);

    /**
     * Handles the effect of the Herbalist Card and Junkdealer Card
     * Sends a message to the server with the color of the students that the player wants to block
     * @param color of the students to block in the influence calculation or to remove from the entry
     */
    void chooseColorToBlock(String color);

}
