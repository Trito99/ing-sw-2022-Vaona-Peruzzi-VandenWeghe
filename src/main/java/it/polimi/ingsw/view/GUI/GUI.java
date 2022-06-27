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

import static java.lang.System.out;

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
        SchoolController schoolController = new SchoolController(school,trash, difficulty, coins);
        if(nickname.equals("Your "))
            Platform.runLater(() -> {
                try {
                    GuiManager.getMainScene().updatePersonalSchool(schoolController, gameMode, teamMate);
                    Platform.runLater(() -> GuiManager.changeRootMainScene(observers));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        else
            Platform.runLater(() ->GuiManager.getMainScene().updateOtherSchool(schoolController, gameMode, nickname));


    }

    @Override
    public void showListOfIsland(Table table, Difficulty difficulty) {
       Platform.runLater(() -> GuiManager.getMainScene().updateIslands(table.getListOfIsland()));
    }

    @Override
    public void showTable(Table table, Difficulty difficulty) {
        this.table = table;
        if (!gameStarted) {
            Platform.runLater(() -> GuiManager.changeRootMainScene(observers));
            Platform.runLater(() -> {
                try {
                    GuiManager.getMainScene().initializeTable(difficulty, table);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            gameStarted = true;
        }else{
            Platform.runLater(() -> GuiManager.getMainScene().updateTable(table));
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
            GuiManager.getMainScene().setPlanning(true);
            GuiManager.getMainScene().getAssistantDeck().addAllObservers(observers);});
        showMessage("Choose an Assistant Card");

    }

    @Override
    public void askCharacterCardToPlay(boolean choice, int coins, ArrayList<CharacterCard> list) {

    }

    @Override
    public void askColorToBlock(CardEffect cardEffect) {

    }

    @Override
    public void askPlaceAndStudentForMove(ArrayList<Student> entry) {
        Platform.runLater(() -> {
            GuiManager.getMainScene().getPersonalSchoolController().disabilitateEntry(false);
            GuiManager.getMainScene().getPersonalSchoolController().addAllObservers(observers);});
        showMessage("Move a student from your entry ");

    }

    @Override
    public void askMotherEarthSteps(int maxSteps, Table table, Difficulty difficulty) {
        Platform.runLater(() ->
            {GuiManager.getMainScene().getPersonalSchoolController().disabilitateEntry(true);
            GuiManager.getMainScene().disabilitateMother(table,maxSteps,false);});
        showMessage("Move MotherEarth");
    }

    @Override
    public void askCloud(Table table) {

    }

    @Override
    public void askId(boolean choice, CharacterCard characterCard, int indexAcrobat, School school) {
        if (choice) {
            if (characterCard != null) {
                if (characterCard.getCardEffect().equals(CardEffect.HERALD))
                    System.out.print("\nHERALD EFFECT\nIn which island do you want to calculate influence? (id)\n");
                if (characterCard.getCardEffect().equals(CardEffect.CURATOR))
                    System.out.print("\nCURATOR EFFECT\nIn which island do you want to place the forbidden card? (id)\n");
            } else
                notifyObserver(obs -> obs.chooseId(GuiManager.getMainScene().getStudentDestinantionIslandId(),choice, indexAcrobat, false));
        }
    }
}
