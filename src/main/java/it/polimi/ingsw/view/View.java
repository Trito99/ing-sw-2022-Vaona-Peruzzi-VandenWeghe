package it.polimi.ingsw.view;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColor;
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

    void showLoseMessage();

    /** mostra tutti i dati del giocatore */
    void showPlayer(String nickname, PlayerNumber playerNumber, TColor tColor, int influenceOnIsland, School personalSchool,
                    DeckAssistant deckOfPlayer, AssistantCard trash, int coinscore, String player);

    void showPersonalSchool(School school, String nickname);

    void showTable(Table table);

    void showDeckAssistant(DeckAssistant deckAssistant, String nickname);

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
    void askAssistantCardToPlay(ArrayList<AssistantCard> assistantDeck);

    /** chiede al giocatore dove muovere gli studenti */
    void askStudentsToMove(ArrayList<Student> studentsInHall, School school, Table table);

    /** chiede al giocatore quale carta personaggio vuole giocare */
    void askCharacterCardToPlay(ArrayList<CharacterCard> characterCard);

    /** chiede al giocatore quanti passi far fare a madre natura*/
    void askMotherEarthSteps(Table table);

    /** chiede al giocatore di scegliere una tessera nuvola */
    void askChooseCloud(Table table);



}
