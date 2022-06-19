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

/** la classe rappresenta la View generica che poi sarà implementata da CLI e GUI*/
public interface View {

    void showLogin(String nickname, String gameId, GregorianCalendar playerDate, boolean wasJoined);

    /** mostra un messaggio generico */
    void showMessage(String message);

    void showWaitingMessage(String message);

    void showWinMessage();

    void showLoseMessage(String nickname);

    void showPersonalSchool(School school, String nickname, AssistantCard trash, Difficulty difficulty, int coins, GameMode gameMode, String teamMate);

    void showTable(Table table, Difficulty difficulty);

    void showDeckAssistant(DeckAssistant deckAssistant);

    void showErrorMessage(String message);


    /** azioni di gioco */

    /** chiede al giocatore quale sarà la sua prossima azione */
    void askAction();

    /** chiede al giocatore di connettersi al server fornendo indirizzo ip e porta */
    void askConnect();

    /** chiede al giocatore di connettersi al gioco fornendo username e game id
     * @param lobbyMap*/
    void askLobby(Map<String, LobbyForPrint> lobbyMap);

    /** chiede numero di giocatori e la difficoltà del nuovo gioco */
    void askPlayersNumberAndDifficulty();

    void askTowerColorAndDeck(ArrayList<TColor> towerColors, ArrayList<AssistantDeckName> assistantDeckNames);

    /** chiede al giocatore quale carta assistente vuole giocare */
    void askAssistantCardToPlay();

    /** chiede al giocatore quale carta personaggio vuole giocare
     * @param choice
     * @param list*/
    void askCharacterCardToPlay(boolean choice, int coins, ArrayList<CharacterCard> list);

    void askColorToBlock(CardEffect cardEffect);

    /** chiede al giocatore quanti passi far fare a madre natura
     * @param maxSteps*/
    void askMotherEarthSteps(int maxSteps, Table table, Difficulty difficulty);

    /** chiede al giocatore di scegliere una tessera nuvola */
    void askCloud(Table table);

    void askPlaceAndStudentForMove(ArrayList<Student> entry);

    void askId(boolean choice, CharacterCard characterCard, int indexAcrobat, School school);

}
