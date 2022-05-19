package it.polimi.ingsw.view;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/** la classe rappresenta la View generica che poi sarà implementata da CLI e GUI*/
public interface View {

    void showLogin(String nickname, String gameId, GregorianCalendar playerDate, boolean wasJoined);

    /** mostra un messaggio generico */
    void showMessage(String message);

    void showPlayerInfluence(int influence);

    void showPlayerTurn(String activePlayer);
    
    void showPlayerList(ArrayList<String> playerOrder);

    void showWinMessage();

    void showLoseMessage(String nickname);

    void showPersonalSchool(School school, String nickname, AssistantCard trash, Difficulty difficulty,int coins);

    void showTable(Table table, Difficulty difficulty);

    void showDeckAssistant(DeckAssistant deckAssistant);

    void showWinMessage(int numberOfTower);

    void showErrorMessage(String message);


    /** azioni di gioco */

    /** chiede al giocatore quale sarà la sua prossima azione */
    void askAction();

    /** chiede al giocatore di connettersi al server fornendo indirizzo ip e porta */
    void askConnect();

    /** chiede al giocatore di connettersi al gioco fornendo username e game id */
    void askLobby();

    /** chiede quanti giocatori avrà il nuovo gioco */
    void askPlayersNumberAndDifficulty();

    /** chiede al giocatore quale carta assistente vuole giocare */
    void askAssistantCardToPlay();

    /** chiede al giocatore quale carta personaggio vuole giocare
     * @param choice*/
    void askCharacterCardToPlay(boolean choice, Player player);

    /** chiede al giocatore quanti passi far fare a madre natura*/
    void askMotherEarthSteps(AssistantCard trash);

    /** chiede al giocatore di scegliere una tessera nuvola */
    void askCloud(Table table);

    void askPlaceAndStudentForMove(ArrayList<Student> entry);

    void askId(boolean choice, CharacterCard characterCard);

}
