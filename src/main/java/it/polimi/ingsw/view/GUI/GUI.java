package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.scene.*;
import it.polimi.ingsw.view.View;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GUI extends ObservableView implements View {
    private static ArrayList<String> playerList = new ArrayList<>();


    @Override
    public void askConnect() {
        ConnectToServer connectToServer = new ConnectToServer();
        connectToServer.addAllObservers(observers);
        Platform.runLater(() -> {
            GuiManager.changeRootPane(observers, "/fxml/connect_to_server_scene");
        });
    }

    @Override
    public void askLobby() {
        SetupGame setupGame = new SetupGame();
        setupGame.addAllObservers(observers);
        Platform.runLater(() -> GuiManager.changeRootPane(observers, "/fxml/setup_game_scene") );
    }

    @Override
    public void askPlayersNumberAndDifficulty() {
        NewGame pNandDifficulty = new NewGame();
        pNandDifficulty.addAllObservers(observers);
        Platform.runLater(()-> GuiManager.changeRootPane(observers,"/fxml/new_game_scene") );
    }

    @Override
    public void askAction() {

    }

    @Override
    public void showLogin(String nickname, String gameId, GregorianCalendar playerDate, boolean wasJoined) {
        GUI.playerList.add(nickname);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showPlayerInfluence(int influence) {

    }

    @Override
    public void showPlayerTurn(String activePlayer) {

    }

    @Override
    public void showPlayerList(ArrayList<String> playerOrder) {

    }

    @Override
    public void showWinMessage() {

    }

    @Override
    public void showPersonalSchool(School school, String nickname, AssistantCard trash, Difficulty difficulty, int coins) {

    }

    @Override
    public void showTable(Table table, Difficulty difficulty) {

    }

    @Override
    public void showDeckAssistant(DeckAssistant deckAssistant) {

    }

    @Override
    public void showWinMessage(int numberOfTower) {
        Winner win = new Winner();
        win.addAllObservers(observers);
        Platform.runLater(() -> GuiManager.changeRootPane(win, "/fxml/winner_scene"));
    }

    @Override
    public void showLoseMessage(String nickname) {
        Loser lost = new Loser();
        lost.addAllObservers(observers);
        Platform.runLater(() -> GuiManager.changeRootPane(observers, "/fxml/lose_scene"));
    }

    @Override
    public void showErrorMessage(String message) {
        Platform.runLater(() -> Message.display("Error", message));
    }

    @Override
    public void askAssistantCardToPlay() {

    }

    @Override
    public void askCharacterCardToPlay(boolean choice, int coins, ArrayList<CharacterCard> list) {

    }

    @Override
    public void askColorToBlock(CardEffect cardEffect) {

    }

    @Override
    public void askPlaceAndStudentForMove(ArrayList<Student> entry) {

    }

    @Override
    public void askMotherEarthSteps(int maxSteps) {

    }

    @Override
    public void askCloud(Table table) {

    }

    @Override
    public void askId(boolean choice, CharacterCard characterCard, int indexAcrobat, School school) {

    }
}
