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
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.scene.*;
import it.polimi.ingsw.view.View;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

public class GUI extends ObservableView implements View {
    private static ArrayList<String> playerList = new ArrayList<>();

    private ArrayList<SchoolController> schools = new ArrayList<>();

    private Table table;
    private boolean gameStarted = false;

    @Override
    public void askConnect() {
        ConnectToServerScene connectToServer = new ConnectToServerScene();
        connectToServer.addAllObservers(observers);
        Platform.runLater(() -> {
            GuiManager.changeRootPane(observers, "/fxml/connect_to_server_scene");
        });
    }

    @Override
    public void askLobby(Map<String, LobbyForPrint> lobbyMap) {
        SetupGameScene setupGame = new SetupGameScene();
        setupGame.addAllObservers(observers);
        setupGame.setLobbyMap(lobbyMap);

        Platform.runLater(() -> GuiManager.changeRootPane(setupGame, "/fxml/setup_game_scene") );

    }

    @Override
    public void askPlayersNumberAndDifficulty() {
        NewGameScene pNandDifficulty = new NewGameScene();
        pNandDifficulty.addAllObservers(observers);
        Platform.runLater(()-> GuiManager.changeRootPane(observers,"/fxml/new_game_scene") );
    }

    @Override
    public void askTowerColorAndDeck(ArrayList<TColor> towerColors, ArrayList<AssistantDeckName> assistantDeckNames) {
        TowerAndDeckScene towerAndDeckScene = new TowerAndDeckScene();
        towerAndDeckScene.addAllObservers(observers);
        towerAndDeckScene.setAssistantDeckNames(assistantDeckNames);
        towerAndDeckScene.setTowerColors(towerColors);
        Platform.runLater(() -> GuiManager.changeRootPane(towerAndDeckScene, "/fxml/tower_and_deck_scene") );
    }

    @Override
    public void askAction() {

    }

    @Override
    public void showLogin(String nickname, String gameId, GregorianCalendar playerDate, boolean wasJoined) {
        if (wasJoined){
            notifyObserver(obs -> obs.createNickname(nickname));
            notifyObserver(obs -> obs.createPlayerDate(playerDate));
            GUI.playerList.add(nickname);
            showMessage("You joined the game as "+nickname);
        }
        else {
            showMessage("Game is already full");
            notifyObserver(ObserverView::askLobbyServerInfo);
        }
    }

    @Override
    public void showMessage(String message) {
        Platform.runLater(() -> MessageScene.display(null,null, message));
        if (message.equals("Tie")){
            notifyObserver(ObserverView::updateDisconnect);
        }
    }

    @Override
    public void showWaitingMessage(String message) {
        Platform.runLater(() -> GuiManager.changeRootPane(observers, "/fxml/waiting_scene") );
    }

    @Override
    public void showPersonalSchool(School school, String nickname, AssistantCard trash, Difficulty difficulty, int coins, GameMode gameMode, String teamMate) {
        SchoolController schoolController = new SchoolController(school);
        if(nickname.equals("Your "))
            Platform.runLater(() -> {
                try {
                    GuiManager.getMainScene().updatePersonalSchool(schoolController);
                    Platform.runLater(() -> GuiManager.changeRootMainScene(observers));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        else
            Platform.runLater(() ->GuiManager.getMainScene().updateOtherSchool(schoolController, gameMode, nickname));


    }

    @Override
    public void showTable(Table table, Difficulty difficulty) {
        this.table = table;
        if (!gameStarted) {
            Platform.runLater(() -> GuiManager.changeRootMainScene(observers));
            Platform.runLater(() -> GuiManager.getMainScene().initializeDifficulty(difficulty,table.getCoinsOnTable()));
            gameStarted = true;
        }else{
            Platform.runLater(() -> GuiManager.getMainScene());
            Platform.runLater(() -> GuiManager.changeRootMainScene(observers));
        }
    }

    @Override
    public void showDeckAssistant(DeckAssistant deckAssistant) {
        ViewDeckScene viewDeckScene = new ViewDeckScene(deckAssistant);
        Platform.runLater(()->GuiManager.getMainScene().updateAssistantCardDeck(viewDeckScene));
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
        Platform.runLater(() -> MessageScene.display("Error","Error", message));
    }

    @Override
    public void askAssistantCardToPlay() {
        Platform.runLater(() -> {
            GuiManager.newStagePane(GuiManager.getMainScene().getAssistantDeck(), "/fxml/view_deck_scene");
            GuiManager.getMainScene().getAssistantDeck().activatePlayButton();
            GuiManager.getMainScene().getAssistantDeck().addAllObservers(observers);});
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
