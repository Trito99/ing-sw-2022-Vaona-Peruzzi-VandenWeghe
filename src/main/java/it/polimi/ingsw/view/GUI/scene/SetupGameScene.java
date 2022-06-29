package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.network.LobbyForPrint;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.GregorianCalendar;
import java.util.Map;

/**
 * This Scene Controller is used to take part to a match
 * It saves all the infos of a new player, allowing him to create a new lobby or to join a lobby already existing
 */
public class SetupGameScene extends ObservableView implements GenericScene {
    private Map<String, LobbyForPrint> lobbyMap = null;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Text bdayText;
    @FXML
    private Text connectText;
    @FXML
    private Text gameText;
    @FXML
    private Text lobbyText;
    @FXML
    private Text nicknameText;
    @FXML
    private Text titleText;
    @FXML
    private Button nextButton;
    @FXML
    private TextField nicknameField;
    @FXML
    private TextField ddField;
    @FXML
    private TextField mmField;
    @FXML
    private TextField yyyyField;
    @FXML
    private TextField gameIdField;
    @FXML
    private TableView<LobbyForPrint> lobbyList;
    @FXML
    private TableColumn<LobbyForPrint, Integer> gameId;
    @FXML
    private TableColumn<LobbyForPrint, Difficulty> difficulty;
    @FXML
    private TableColumn<LobbyForPrint, GameMode> gameMode;
    @FXML
    private TableColumn<LobbyForPrint, Integer> currentPlayers;

    /**
     * Initialize buttons present in the Scene and generates Lobby
     */
    @FXML
    public void initialize(){
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickNext);
        generateLobbyTable(lobbyMap);
    }

    /**
     * Generates a new Lobby
     * @param lobbyMap map of a new lobby
     */
    private void generateLobbyTable(Map<String, LobbyForPrint> lobbyMap){
        gameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));
        difficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        gameMode.setCellValueFactory(new PropertyValueFactory<>("gameMode"));
        currentPlayers.setCellValueFactory(new PropertyValueFactory<>("currentPlayers"));
        lobbyList.setItems(getLobbyList(lobbyMap));
    }

    /**
     * Returns the list of lobbies available
     * @param lobbyMap map of a certain lobby
     * @return a list with lobbies available
     */
    private ObservableList<LobbyForPrint> getLobbyList(Map<String, LobbyForPrint> lobbyMap){
        ObservableList<LobbyForPrint> lobbyList = FXCollections.observableArrayList();
        if(lobbyMap != null){
            for(String lobbyId : lobbyMap.keySet()){
                if (lobbyMap.get(lobbyId).getDifficulty()!=null)
                    lobbyList.add(new LobbyForPrint(lobbyId,lobbyMap.get(lobbyId).getDifficulty(),lobbyMap.get(lobbyId).getGameMode(),lobbyMap.get(lobbyId).getCurrentPlayers()+"/"+String.valueOf(lobbyMap.get(lobbyId).getMaxPlayers())));
            }
        }
        return lobbyList;
    }

    /**
     * Handles the click on the Next button
     */
    @FXML
    private void clickNext(Event event){
        boolean name = false,birth = false,id = false;
        GregorianCalendar playerDate = new GregorianCalendar();

        nicknameField.setDisable(true);
        ddField.setDisable(true);
        mmField.setDisable(true);
        yyyyField.setDisable(true);
        gameIdField.setDisable(true);

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

    public void setLobbyMap(Map<String, LobbyForPrint> lobbyMap) {
        this.lobbyMap = lobbyMap;
    }
}
