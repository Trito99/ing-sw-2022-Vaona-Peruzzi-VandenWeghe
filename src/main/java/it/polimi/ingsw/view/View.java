package it.polimi.ingsw.view;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.network.LobbyForPrint;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Represents the generic View implemented by CLI and GUI
 */
public interface View {

    /**
     * Shows if the login was successful
     * @param nickname of the player
     * @param gameId chosen by the player
     * @param playerDate of the player
     * @param gameNotFull false if the game selected is already full
     */
    void showLogin(String nickname, String gameId, GregorianCalendar playerDate, boolean gameNotFull);

    /**
     * Shows a generic message
     * @param message to be shown
     */
    void showMessage(String message);

    /**
     * Shows a waiting message
     * @param message to be shown
     */
    void showWaitingMessage(String message);

    /**
     * Shows Winning message and disconnects the player
     */
    void showWinMessage();

    /**
     * Shows Losing message and disconnects the player
     * @param nickname of the player that won the match
     */
    void showLoseMessage(String nickname);

    /**
     * Shows the updated School of the player
     * @param school of the player
     * @param nickname of the player
     * @param trash card of the player
     * @param difficulty of the match
     * @param coins coinScore of the player
     * @param gameMode of the match
     * @param teamMate of the player (if coop mode)
     */
    void showPersonalSchool(School school, String nickname, AssistantCard trash, Difficulty difficulty, int coins, GameMode gameMode, String teamMate);

    /**
     * Shows the updated list of islands
     * @param table of the match
     * @param difficulty of the match
     */
    void showListOfIsland(Table table, Difficulty difficulty);

    /**
     * Shows the updated table of the match
     * @param table of the match
     * @param difficulty mode of the match
     */
    void showTable(Table table, Difficulty difficulty);

    /**
     * Shows the updated cards in hand of the player
     * @param deckAssistant of the player
     */
    void showDeckAssistant(DeckAssistant deckAssistant);

    /**
     * Shows the player an error message
     * @param message to be shown
     */
    void showErrorMessage(String message);

    /**
     * Shows coins and character cards on table
     * @param coins on table
     * @param characterCardsOnTable on table
     */
    void showCoinAndCharacterCards(int coins, ArrayList<CharacterCard> characterCardsOnTable);

    /**
     * Asks the ip address and the server port for the connection to the server
     */
     void askConnect();

    /**
     * Asks the player's info and the game id that the player wants to join (or create)
     * @param lobbyMap map for print the information of the lobby
     */
    void askLobby(Map<String, LobbyForPrint> lobbyMap);

    /**
     * Asks at the player who creates the lobby the difficulty and the gameMode
     */
    void askPlayersNumberAndDifficulty();

    /**
     * Asks at the player to select deck and towers
     * @param towerColors array with the colors of the towers
     * @param assistantDeckNames array with the string's name of decks
     */
    void askTowerColorAndDeck(ArrayList<TColor> towerColors, ArrayList<AssistantDeckName> assistantDeckNames);

    /**
     * Asks the player which Assistant Card wants to play
     */
    void askAssistantCardToPlay();

    /**
     * Asks at the player which Character Card he wants to play
     * @param choice false if the player hasn't already decided if he wants to activate a character card,
     *               true if the player wants to play a certain character card
     * @param coins of player
     * @param list of Character Cards on table
     */
    void askCharacterCardToPlay(boolean choice, int coins, ArrayList<CharacterCard> list);

    /**
     * Handles the effect of the Herbalist Card and Junkdealer Card
     * Asks the player which color wants to select
     * @param color color of the students to block in the influence calculation or to remove from the entry
     */
    void askColorToBlock(CardEffect color);

    /**
     * Asks the player how many steps wants to move Mother Earth
     * @param maxSteps max steps the player can choose
     * @param table of the match
     * @param difficulty of the match
     */
    void askMotherEarthSteps(int maxSteps, Table table, Difficulty difficulty);

    /**
     * Asks the player which cloud wants to choose
     * @param table of the match
     */
    void askCloud(Table table);

    /**
     * Asks the player which students and where wants to move
     * @param entry of the player's school
     */
    void askPlaceAndStudentForMove(ArrayList<Student> entry);

    /**
     * Asks the player which island he wants to select (or student on card for cards)
     * @param choice true for the selection of an island (that includes the effect of herald and curator) otherwise
     * @param characterCard played in the round
     * @param indexAcrobat index for the acrobat effect
     * @param school of current player
     */
    void askId(boolean choice, CharacterCard characterCard, int indexAcrobat, School school);

}
