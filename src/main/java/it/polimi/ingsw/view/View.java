package it.polimi.ingsw.view;

import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/** la classe rappresenta la View generica che poi sarà implementata da CLI e GUI*/
public interface View {

    void showLogin(String username, String gameId, boolean wasJoined);

    /** mostra un messaggio generico */
    void showMessage(String message);

    void showPlayerInfluence(int influence);

    void showPlayerTurn(String activePlayer);
    
    void showPlayerList(ArrayList<String> playerOrder);

    void showWinMessage();

    void showLoseMessage();

    /** azioni di gioco */

    /** chiede al giocatore il tipo di partita che vuole giocare */
    void askGameMode(Game game);

    /** chiede al giocatore quale carta assistente vuole giocare */
    void askAssistantCardToPlay(ArrayList<CharacterCard> characterCards);

    /** chiede al giocatore dove muovere gli studenti */
    void askStudentsToMove(ArrayList<Student> studentsInHall, School school, Table table);

    /** chiede al giocatore quale carta personaggio vuole giocare */
    void askCharacterCardToPlay(Table table);

    /** chiede al giocatore quanti passi far fare a madre natura*/
    void askMotherEarthSteps(Table table);

    /** chiede al giocatore di scegliere una tessera nuvola */
    void askChooseCloud(Table table);

}
