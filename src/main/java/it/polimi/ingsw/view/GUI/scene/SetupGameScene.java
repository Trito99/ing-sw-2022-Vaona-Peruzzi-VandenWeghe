package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.GregorianCalendar;

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
    public void initialize(){
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickNext);
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickBack);
    }

    /** gestisce il click sul pulsante */
    private void clickNext(Event event){
        GregorianCalendar playerDate = new GregorianCalendar();

        nicknameField.setDisable(true);
        ddField.setDisable(true);
        mmField.setDisable(true);
        yyyyField.setDisable(true);
        gameIdField.setDisable(true);

        /** non sono sicura se vada qui o nell'override della GUI */
        String nickname = nicknameField.getText();
        Integer day = Integer.parseInt(ddField.getText());
        Integer month = Integer.parseInt(mmField.getText());
        Integer year = Integer.parseInt(yyyyField.getText());
        String gameId = gameIdField.getText();

        playerDate.setLenient(false);
        playerDate.set(year,month -1,day,0,0,0);
        playerDate.getTime();

        nextButton.setDisable(true);

        notifyObserver(obs -> obs.updateLobby(nickname, playerDate, gameId));

       /** GuiManager.changeRootPane(observers, event, "/fxml/new_game_scene"); */
    }

    private void clickBack(Event event){
        nextButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/connect_to_server");
    }
}
