package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TowerAndDeckScene extends ObservableView implements GenericScene {

    @FXML
    private Pane pane;
    @FXML
    private Pane deck1Pane;
    @FXML
    private Pane deck2Pane;
    @FXML
    private Pane deck3Pane;
    @FXML
    private Pane deck4Pane;
    @FXML
    private ImageView deck1;
    @FXML
    private ImageView deck2;
    @FXML
    private ImageView deck3;
    @FXML
    private ImageView deck4;
    @FXML
    private Text titleText;
    @FXML
    private Text towerText;
    @FXML
    private Text deckText;
    @FXML
    private Text whiteTtext;
    @FXML
    private Text blackTtext;
    @FXML
    private Text greyTtext;
    @FXML
    private Text startText;
    @FXML
    private Text exitText;
    @FXML
    private Button whiteTbutton;
    @FXML
    private Button blackTbutton;
    @FXML
    private Button greyTbutton;
    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;

    @FXML
    public void initialize(){
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickStart);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickExit);
    }

    /** gestisce il click sul pulsante */
    private void clickStart(Event event){
        startButton.setDisable(true);
    }

    private void clickExit(Event event){
        exitButton.setDisable(true);
        GuiManager.changeRootPane(observers, event,"/fxml/setup_game_scene");
    }

}
