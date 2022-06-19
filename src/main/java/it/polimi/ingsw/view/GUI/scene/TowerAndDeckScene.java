package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class TowerAndDeckScene extends ObservableView implements GenericScene {

    private ArrayList<TColor> towerColors;
    private ArrayList<AssistantDeckName> assistantDeckNames;


    @FXML
    private Text blackT;

    @FXML
    private Button blackTower;

    @FXML
    private Text connectText;

    @FXML
    private Button exitButton;

    @FXML
    private Text exitText;

    @FXML
    private Text grayT;

    @FXML
    private Button grayTower;

    @FXML
    private Text nicknameText;

    @FXML
    private Text nicknameText1;

    @FXML
    private Button startButton;

    @FXML
    private Text titleText;

    @FXML
    private Text whiteT;

    @FXML
    private Button whiteTower;

    @FXML
    private ImageView wizard1;

    @FXML
    private Button wizard1Button;

    @FXML
    private Pane wizard1Pane;

    @FXML
    private ImageView wizard2;

    @FXML
    private Button wizard2Button;

    @FXML
    private Pane wizard2Pane;

    @FXML
    private ImageView wizard3;

    @FXML
    private Button wizard3Button;

    @FXML
    private Pane wizard3Pane;

    @FXML
    private ImageView wizard4;

    @FXML
    private Button wizard4Button;

    @FXML
    private Pane wizard4Pane;

    @FXML
    public void initialize(){
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickStart);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickExit);
        initializeButtons();
        intializeTowerColorsAndAssistantDeck();
    }

    /** gestisce il click sul pulsante */
    private void clickStart(Event event){
        startButton.setDisable(true);
    }

    private void clickExit(Event event){
        exitButton.setDisable(true);
        notifyObserver(ObserverView:: updateDisconnect);
        System.exit(0);
    }


    public void setTowerColors(ArrayList<TColor> towerColors) {
        this.towerColors = towerColors;
    }

    public void setAssistantDeckNames(ArrayList<AssistantDeckName> assistantDeckNames) {
        this.assistantDeckNames = assistantDeckNames;
    }

    private void intializeTowerColorsAndAssistantDeck(){
        initializeButtons();
        for(TColor tcolor : towerColors){
            switch(tcolor){
                case WHITE:
                    whiteTower.setDisable(false);
                    whiteTower.setVisible(true);
                    whiteT.setVisible(true);
                    break;
                case BLACK:
                    blackTower.setDisable(false);
                    blackTower.setVisible(true);
                    blackT.setVisible(true);
                    break;
                case GREY:
                    grayTower.setDisable(false);
                    grayTower.setVisible(true);
                    whiteT.setVisible(true);
                    break;
            }
        }
        for(AssistantDeckName assistantDeckName : assistantDeckNames){
            switch (assistantDeckName){
                case WIZARD1:
                    wizard1.setVisible(true);
                    wizard1Pane.setVisible(true);
                    wizard1Button.setDisable(false);
                    break;
                case WIZARD2:
                    wizard2.setVisible(true);
                    wizard2Pane.setVisible(true);
                    wizard2Button.setDisable(false);
                    break;
                case WIZARD3:
                    wizard3.setVisible(true);
                    wizard3Pane.setVisible(true);
                    wizard3Button.setDisable(false);
                    break;
                case WIZARD4:
                    wizard4.setVisible(true);
                    wizard4Pane.setVisible(true);
                    wizard4Button.setDisable(false);
                    break;
            }
        }
    }

    private void initializeButtons(){
        whiteTower.setDisable(true);
        blackTower.setDisable(true);
        grayTower.setDisable(true);
        whiteTower.setVisible(false);
        blackTower.setVisible(false);
        grayTower.setVisible(false);
        whiteT.setVisible(false);
        blackT.setVisible(false);
        grayT.setVisible(false);
        wizard1.setVisible(false);
        wizard2.setVisible(false);
        wizard3.setVisible(false);
        wizard4.setVisible(false);
        wizard1Pane.setVisible(false);
        wizard2Pane.setVisible(false);
        wizard3Pane.setVisible(false);
        wizard4Pane.setVisible(false);
        wizard1Button.setDisable(true);
        wizard2Button.setDisable(true);
        wizard3Button.setDisable(true);
        wizard4Button.setDisable(true);
    }
}
