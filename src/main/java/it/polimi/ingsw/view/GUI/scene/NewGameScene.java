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

    private void clickTwoPlayersButton(Event event){
        fourShadowPane.setVisible(false);
        threeShadowPane.setVisible(false);
        twoShadowPane.setStyle("-fx-background-color: white");
        twoShadowPane.setVisible(true);
        setPlayerNumber(2);
    }

    private void clickThreePlayersButton(Event event){
        twoShadowPane.setVisible(false);
        fourShadowPane.setVisible(false);
        threeShadowPane.setStyle("-fx-background-color: white");
        threeShadowPane.setVisible(true);
        setPlayerNumber(3);
    }

    private void clickCoopButton(Event event){
        twoShadowPane.setVisible(false);
        threeShadowPane.setVisible(false);
        fourShadowPane.setStyle("-fx-background-color: white");
        fourShadowPane.setVisible(true);
        setPlayerNumber(4);
    }

    private void clickStandardButton(Event event){
        expertShadowPane.setVisible(false);
        standardShadowPane.setStyle("-fx-background-color: white");
        standardShadowPane.setVisible(true);
        setDifficulty(Difficulty.STANDARDMODE);
    }

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

    /** gestisce il click sul pulsante */
    private void clickNext(Event event){
        if(playerNumber!=0 && difficulty!=null) {
            disableButtons();
            notifyObserver(obs -> obs.choosePlayersNumberAndDifficulty(playerNumber, difficulty));
        }
    }

    private void disableButtons(){
        nextButton.setDisable(true);
        twoPlayersButton.setDisable(true);
        threePlayersButton.setDisable(true);
        coopButton.setDisable(true);
        standardButton.setDisable(true);
        expertButton.setDisable(true);
    }

}
