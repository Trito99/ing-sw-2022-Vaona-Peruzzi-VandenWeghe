package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


import java.util.Locale;

public class NewGameScene extends ObservableView implements GenericScene {

    @FXML
    private ImageView backgroundImage;
    @FXML
    private Button exitButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button twoPlayersButton;
    @FXML
    private Button threePlayersButton;
    @FXML
    private Button coopButton;
    @FXML
    private Button standardButton;
    @FXML
    private Button expertButton;
    @FXML
    private Text titleText;
    @FXML
    private Text playerText;
    @FXML
    private Text gameText;
    @FXML
    private Text exitText;
    @FXML
    private Text connectText;


    @FXML
    public void initialize(){
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickNext);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickExit);
    }

    /** gestisce il click sul pulsante */
    private void clickNext(Event event){
        nextButton.setDisable(true);
    }


    /** DA IMPLEMENTARE la exit */
    private void clickExit(Event event){
        exitButton.setDisable(true);
        notifyObserver(ObserverView :: updateDisconnect);
        System.exit(0);
    }


}
