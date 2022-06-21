package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
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

    private TColor colorChosen;

    private AssistantDeckName deckChosen;

    @FXML
    private Pane blackShadowPane;

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
    private Pane greyShadowPane;

    @FXML
    private Text greyT;

    @FXML
    private Button greyTower;

    @FXML
    private Text nicknameText;

    @FXML
    private Text nicknameText1;

    @FXML
    private Button startButton;

    @FXML
    private Text titleText;

    @FXML
    private Pane whiteShadowPane;

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
    private Pane wizard1ShadowPane;

    @FXML
    private ImageView wizard2;

    @FXML
    private Button wizard2Button;

    @FXML
    private Pane wizard2Pane;

    @FXML
    private Pane wizard2ShadowPane;

    @FXML
    private ImageView wizard3;

    @FXML
    private Button wizard3Button;

    @FXML
    private Pane wizard3Pane;

    @FXML
    private Pane wizard3ShadowPane;

    @FXML
    private ImageView wizard4;

    @FXML
    private Button wizard4Button;

    @FXML
    private Pane wizard4Pane;

    @FXML
    private Pane wizard4ShadowPane;

    @FXML
    public void initialize(){
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickStart);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickExit);
        initializeButtons();
        initializeTowerColorsAndAssistantDeck();
        whiteTower.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickWhite);
        blackTower.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickBlack);
        greyTower.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickGrey);
        wizard1Button.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickWizard1);
        wizard2Button.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickWizard2);
        wizard3Button.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickWizard3);
        wizard4Button.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickWizard4);
    }

    /** gestisce il click sul pulsante */
    private void clickStart(Event event){
        if((colorChosen != null )&&(deckChosen != null)) {
            startButton.setDisable(true);
            notifyObserver(obs -> obs.chooseTowerColorAndDeck(colorChosen, deckChosen));
        }
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

    private void initializeTowerColorsAndAssistantDeck(){
        initializeButtons();
        for(TColor tcolor : towerColors){
            switch(tcolor){
                case WHITE:
                    whiteTower.setDisable(false);
                    whiteShadowPane.setVisible(false);
                    break;
                case BLACK:
                    blackTower.setDisable(false);
                    blackShadowPane.setVisible(false);
                    break;
                case GREY:
                    greyTower.setDisable(false);
                    greyShadowPane.setVisible(false);
                    break;
            }
        }
        for(AssistantDeckName assistantDeckName : assistantDeckNames){
            switch (assistantDeckName){
                case WIZARD1:
                    wizard1Button.setDisable(false);
                    wizard1ShadowPane.setVisible(false);
                    break;
                case WIZARD2:
                    wizard2Button.setDisable(false);
                    wizard2ShadowPane.setVisible(false);
                    break;
                case WIZARD3:
                    wizard3Button.setDisable(false);
                    wizard3ShadowPane.setVisible(false);
                    break;
                case WIZARD4:
                    wizard4Button.setDisable(false);
                    wizard4ShadowPane.setVisible(false);
                    break;
            }
        }
    }

    private void initializeButtons(){
        whiteTower.setDisable(true);
        blackTower.setDisable(true);
        greyTower.setDisable(true);
        wizard1Button.setDisable(true);
        wizard2Button.setDisable(true);
        wizard3Button.setDisable(true);
        wizard4Button.setDisable(true);
    }

    private void clickWhite(Event event){
        setColorChosen(TColor.WHITE);
    }

    private void clickBlack(Event event){
        setColorChosen(TColor.BLACK);
    }

    private void clickGrey(Event event){
        setColorChosen(TColor.GREY);
    }

    private void clickWizard1(Event event){
        setDeckChosen(AssistantDeckName.WIZARD1);
    }

    private void clickWizard2(Event event){
        setDeckChosen(AssistantDeckName.WIZARD2);
    }

    private void clickWizard3(Event event){
        setDeckChosen(AssistantDeckName.WIZARD3);
    }

    private void clickWizard4(Event event){
        setDeckChosen(AssistantDeckName.WIZARD4);
    }

    public void setColorChosen(TColor colorChosen) {
        this.colorChosen = colorChosen;
    }

    public void setDeckChosen(AssistantDeckName deckChosen) {
        this.deckChosen = deckChosen;
    }
}
