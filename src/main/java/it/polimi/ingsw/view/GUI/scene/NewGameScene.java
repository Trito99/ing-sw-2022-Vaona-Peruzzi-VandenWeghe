package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.GameMode;
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

    private int playerNumber;
    private Difficulty difficulty;


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
        twoPlayersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickTwoPlayersButton);
        threePlayersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickThreePlayersButton);
        coopButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCoopButton);
        standardButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickStandardButton);
        expertButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickExpertButton);
    }

    private void clickTwoPlayersButton(Event event){
        setPlayerNumber(2);
    }

    private void clickThreePlayersButton(Event event){
        setPlayerNumber(3);
    }

    private void clickCoopButton(Event event){
        setPlayerNumber(4);
    }

    private void clickStandardButton(Event event){
        setDifficulty(Difficulty.STANDARDMODE);
    }

    private void clickExpertButton(Event event){
        setDifficulty(Difficulty.EXPERTMODE);
    }

    private void setPlayerNumber(int playerNumber){
        this.playerNumber = playerNumber;
    }

    private void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    /** gestisce il click sul pulsante */
    private void clickNext(Event event){
        disableButtons();
        notifyObserver(obs -> obs.choosePlayersNumberAndDifficulty(playerNumber,difficulty));
    }

    /** DA IMPLEMENTARE la exit */
    private void clickExit(Event event){
        notifyObserver(ObserverView :: updateDisconnect);
        disableButtons();
        System.exit(0);
    }

    private void disableButtons(){
        nextButton.setDisable(true);
        exitButton.setDisable(true);
        twoPlayersButton.setDisable(true);
        threePlayersButton.setDisable(true);
        coopButton.setDisable(true);
        standardButton.setDisable(true);
        expertButton.setDisable(true);
    }

}
