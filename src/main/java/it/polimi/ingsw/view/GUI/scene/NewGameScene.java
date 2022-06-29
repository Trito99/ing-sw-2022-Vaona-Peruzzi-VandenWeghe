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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


import java.util.Locale;

/**
 * This Scene Controller is used to create a new lobby
 */
public class NewGameScene extends ObservableView implements GenericScene {
    private int playerNumber = 0;
    private Difficulty difficulty;

    @FXML
    private ImageView backgroundImage;
    @FXML
    private Text connectText;
    @FXML
    private Button coopButton;
    @FXML
    private Text exitText1;
    @FXML
    private Text exitText11;
    @FXML
    private Button expertButton;
    @FXML
    private Pane expertShadowPane;
    @FXML
    private Pane fourShadowPane;
    @FXML
    private Text gameText;
    @FXML
    private Button nextButton;
    @FXML
    private Text playerText;
    @FXML
    private Button standardButton;
    @FXML
    private Pane standardShadowPane;
    @FXML
    private Button threePlayersButton;
    @FXML
    private Pane threeShadowPane;
    @FXML
    private Text titleText;
    @FXML
    private Button twoPlayersButton;
    @FXML
    private Pane twoShadowPane;

    /**
     * Initialize buttons and panes present in the Scene
     */
    @FXML
    public void initialize(){
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickNext);
        twoPlayersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickTwoPlayersButton);
        threePlayersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickThreePlayersButton);
        coopButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCoopButton);
        standardButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickStandardButton);
        expertButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickExpertButton);

        twoShadowPane.setVisible(false);
        threeShadowPane.setVisible(false);
        fourShadowPane.setVisible(false);
        standardShadowPane.setVisible(false);
        expertShadowPane.setVisible(false);
    }

    /**
     * Handles the click on "Two players" button
     */
    private void clickTwoPlayersButton(Event event){
        fourShadowPane.setVisible(false);
        threeShadowPane.setVisible(false);
        twoShadowPane.setStyle("-fx-background-color: white");
        twoShadowPane.setVisible(true);
        setPlayerNumber(2);
    }

    /**
     * Handles the click on "Three players" button
     */
    private void clickThreePlayersButton(Event event){
        twoShadowPane.setVisible(false);
        fourShadowPane.setVisible(false);
        threeShadowPane.setStyle("-fx-background-color: white");
        threeShadowPane.setVisible(true);
        setPlayerNumber(3);
    }

    /**
     * Handles the click on "Four players" button
     */
    private void clickCoopButton(Event event){
        twoShadowPane.setVisible(false);
        threeShadowPane.setVisible(false);
        fourShadowPane.setStyle("-fx-background-color: white");
        fourShadowPane.setVisible(true);
        setPlayerNumber(4);
    }

    /**
     * Handles the click on "Standard Mode" button
     */
    private void clickStandardButton(Event event){
        expertShadowPane.setVisible(false);
        standardShadowPane.setStyle("-fx-background-color: white");
        standardShadowPane.setVisible(true);
        setDifficulty(Difficulty.STANDARDMODE);
    }

    /**
     * Handles the click on "Expert Mode" button
     */
    private void clickExpertButton(Event event){
        standardShadowPane.setVisible(false);
        expertShadowPane.setStyle("-fx-background-color: white");
        expertShadowPane.setVisible(true);
        setDifficulty(Difficulty.EXPERTMODE);
    }

    private void setPlayerNumber(int playerNumber){
        this.playerNumber = playerNumber;
    }

    private void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    /**
     * Handles the click on the Next button
     */
    private void clickNext(Event event){
        if(playerNumber!=0 && difficulty!=null) {
            disableButtons();
            notifyObserver(obs -> obs.choosePlayersNumberAndDifficulty(playerNumber, difficulty));
        }
    }

    /**
     * Disable all buttons
     */
    private void disableButtons(){
        nextButton.setDisable(true);
        twoPlayersButton.setDisable(true);
        threePlayersButton.setDisable(true);
        coopButton.setDisable(true);
        standardButton.setDisable(true);
        expertButton.setDisable(true);
    }

}
