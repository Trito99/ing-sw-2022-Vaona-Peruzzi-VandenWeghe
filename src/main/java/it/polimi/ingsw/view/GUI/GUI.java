package it.polimi.ingsw.view.GUI;

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
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.scene.*;
import it.polimi.ingsw.view.View;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

public class GUI extends ObservableView implements View {
    private static ArrayList<String> playerList = new ArrayList<>();


    @Override
    public void askConnect() {
        ConnectToServerScene connectToServer = new ConnectToServerScene();
        connectToServer.addAllObservers(observers);
        Platform.runLater(() -> {
            GuiManager.changeRootPane(observers, "/fxml/setup_game_scene");
        });
    }

    @Override
    public void askLobby(Map<String, LobbyForPrint> lobbyMap) {
        SetupGameScene setupGame = new SetupGameScene();
        setupGame.addAllObservers(observers);

        Platform.runLater(() -> GuiManager.changeRootPane(observers, "/fxml/setup_game_scene") );

    }

    @Override
    public void askPlayersNumberAndDifficulty() {
        NewGameScene pNandDifficulty = new NewGameScene();
        pNandDifficulty.addAllObservers(observers);
        Platform.runLater(()-> GuiManager.changeRootPane(observers,"/fxml/new_game_scene") );
    }

    @Override
    public void askTowerColorAndDeck(ArrayList<TColor> towerColors, ArrayList<AssistantDeckName> assistantDeckNames) {

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
    public void showPersonalSchool(School school, String nickname, AssistantCard trash, Difficulty difficulty, int coins, GameMode gameMode, String teamMate) {

    }

    @Override
    public void showTable(Table table, Difficulty difficulty) {

    }

    @Override
    public void showDeckAssistant(DeckAssistant deckAssistant) {

    }

    @Override
    public void showWinMessage() {
        WinnerScene win = new WinnerScene();
        win.addAllObservers(observers);
        Platform.runLater(() -> GuiManager.changeRootPane(win, "/fxml/winner_scene"));
    }

    @Override
    public void showLoseMessage(String nickname) {
        LoserScene lost = new LoserScene();
        lost.addAllObservers(observers);
        Platform.runLater(() -> GuiManager.changeRootPane(observers, "/fxml/lose_scene"));
    }

    @Override
    public void showErrorMessage(String message) {
        Platform.runLater(() -> MessageScene.display("Error", message));
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
    public void askMotherEarthSteps(int maxSteps, Table table, Difficulty difficulty) {

    }

    @Override
    public void askCloud(Table table) {

    }

    @Override
    public void askId(boolean choice, CharacterCard characterCard, int indexAcrobat, School school) {

    }
}
