package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.network.LobbyForPrint;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.GregorianCalendar;
import java.util.Map;

import static java.lang.System.out;

/** scena che chiede al giocatore a quale lobby connettersi, chiedendo username, data di nascita, gameId */

public class SetupGameScene extends ObservableView implements GenericScene {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView setupBackground;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField nicknameField;
    @FXML
    private TextField gameIdField;
    @FXML
    private TextField ddField;
    @FXML
    private TextField mmField;
    @FXML
    private TextField yyyyField;

    @FXML
    private TableView<LobbyForPrint> lobbyList;

    @FXML
    private TableColumn<LobbyForPrint, Integer> gameId = new TableColumn<>("ID");

    @FXML
    private TableColumn<LobbyForPrint, Difficulty> difficulty = new TableColumn<>("Difficulty");

    @FXML
    private TableColumn<LobbyForPrint, GameMode> gameMode = new TableColumn<>("Game Mode");

    @FXML
    private TableColumn<LobbyForPrint, Integer> currentPlayers = new TableColumn<>("Current Players");

    @FXML
    public void initialize(){
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickNext);
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickBack);
    }

    public void generateLobbyTable(Map<String, LobbyForPrint> lobbyMap){

        gameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));
        difficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        gameMode.setCellValueFactory(new PropertyValueFactory<>("gameMode"));
        currentPlayers.setCellValueFactory(new PropertyValueFactory<>("currentPlayers"));
        if(lobbyList!=null) {
            lobbyList.setItems(getLobbyList(lobbyMap));
            lobbyList.getColumns().addAll(gameId, difficulty, gameMode, currentPlayers);
        }
    }

    private ObservableList<LobbyForPrint> getLobbyList(Map<String, LobbyForPrint> lobbyMap){
        ObservableList<LobbyForPrint> lobbyList = FXCollections.observableArrayList();
        if(lobbyMap != null){
            for(String lobbyId : lobbyMap.keySet()){
                lobbyList.add(new LobbyForPrint(lobbyId,lobbyMap.get(lobbyId).getDifficulty(),lobbyMap.get(lobbyId).getGameMode(),lobbyMap.get(lobbyId).getCurrentPlayers()+"/"+String.valueOf(lobbyMap.get(lobbyId).getMaxPlayers())));
                }
        }
        return lobbyList;
    }

    /** gestisce il click sul pulsante */
    private void clickNext(Event event){
        boolean name = false,birth = false,id = false;
        GregorianCalendar playerDate = new GregorianCalendar();

        nicknameField.setDisable(true);
        ddField.setDisable(true);
        mmField.setDisable(true);
        yyyyField.setDisable(true);
        gameIdField.setDisable(true);

        /** non sono sicura se vada qui o nell'override della GUI */
        String nickname = null;
        String gameId = null;
        try{
            nickname = nicknameField.getText();
            name = true;
        }catch(Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR, "NAME ERROR", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                nicknameField.setDisable(false);
                nicknameField.clear();
            }
        }

        try {
            Integer birthDay = Integer.parseInt(ddField.getText());
            Integer birthMonth = Integer.parseInt(mmField.getText());
            Integer birthYear = Integer.parseInt(yyyyField.getText());

            playerDate.setLenient(false);
            playerDate.set(birthYear,birthMonth -1,birthDay,0,0,0);
            playerDate.getTime();
            if(birthYear < 1900 || birthYear > 2021){
                Alert alert = new Alert(Alert.AlertType.ERROR, "INVALID YEAR", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    yyyyField.setDisable(false);
                    yyyyField.clear();
                }
            }else{
                birth = true;
            }
        }
        catch(IllegalArgumentException exception){
            Alert alert = new Alert(Alert.AlertType.ERROR, "INVALID DATE", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                yyyyField.setDisable(false);
                mmField.setDisable(false);
                ddField.setDisable(false);
                yyyyField.clear();
                mmField.clear();
                ddField.clear();
            }
        }

        try{
            gameId = gameIdField.getText();
            id = true;
        }catch(Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID ERROR", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                gameIdField.setDisable(false);
                gameIdField.clear();
            }
        }

        if (name && birth && id) {
            String finalGameId = gameId;
            String finalNickname = nickname;
            nextButton.setDisable(true);
            notifyObserver(obs -> obs.updateLobby(finalNickname, playerDate, finalGameId));
        }
    }

    private void clickBack(Event event){
        nextButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/connect_to_server");
    }
}
