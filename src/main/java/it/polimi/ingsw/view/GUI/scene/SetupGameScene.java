package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    public void initialize(){
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickNext);
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickBack);

    }

    /** gestisce il click sul pulsante */
    @FXML
    private void clickNext(Event event){
        nextButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/new_setup_game_scene.fxml");
    }

    @FXML
    private void clickBack(Event event){
        nextButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/connect_to_server.fxml");
    }
}
