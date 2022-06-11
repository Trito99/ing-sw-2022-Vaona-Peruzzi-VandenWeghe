package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class StartGameScene extends ObservableView implements GenericScene{

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button startButton;
    /**@FXML
    private Button rulesButton;*/
    @FXML
    private ImageView bluBackground;
    @FXML
    private ImageView cloudBackground;
    @FXML
    private ImageView logo;
    @FXML
    private Text startText;

    @FXML
    public void initialize(){
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickButtonStart);
    }

    /** gestisce il click sul pulsante */
    @FXML
    private void clickButtonStart(Event event){
        startButton.setDisable(true);

        GuiManager.changeRootPane(observers, event,"/fxml/new_connect_to_server_scene");
    }

    /** DA IMPLEMENTARE schermata rules
    @FXML
    private void clickButtonRules(Event event){
        rulesButton.setDisable(true);
        GuiManager.changeRootPane(observers, event, "/fxml/rules_scene.fxml");
    }*/

}
