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

/**
 * This Scene Controller is used to start the game
 */
public class StartGameScene extends ObservableView implements GenericScene{
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button startButton;
    @FXML
    private ImageView bluBackground;
    @FXML
    private ImageView cloudBackground;
    @FXML
    private ImageView logo;

    /**
     * Initialize buttons present in the Scene
     */
    @FXML
    public void initialize(){
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickButtonStart);
    }

    /**
     * Handles the click on the Start button
     */
    @FXML
    private void clickButtonStart(Event event){
        startButton.setDisable(true);

        GuiManager.changeRootPane(observers, event,"/fxml/connect_to_server_scene");
    }

}
