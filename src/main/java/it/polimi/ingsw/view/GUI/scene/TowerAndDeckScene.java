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

/**
 * This Scene Controller is used to select Tower's color and Deck Assistant
 * It saves all the infos of each player, and hides the item already chosen
 */
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

    /**
     * Initialize buttons present in the Scene
     */
    @FXML
    public void initialize(){
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickStart);
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

    /**
     * Handles the click on the Start button
     */
    private void clickStart(Event event){
        if((colorChosen != null )&&(deckChosen != null)) {
            startButton.setDisable(true);
            notifyObserver(obs -> obs.chooseTowerColorAndDeck(colorChosen, deckChosen));
        }
    }

    public void setTowerColors(ArrayList<TColor> towerColors) {
        this.towerColors = towerColors;
    }

    public void setAssistantDeckNames(ArrayList<AssistantDeckName> assistantDeckNames) {
        this.assistantDeckNames = assistantDeckNames;
    }

    /**
     * Handles the click on the Next button
     */
    private void initializeTowerColorsAndAssistantDeck(){
        initializeTowerColors();
        initializeAssistantDeck();
    }

    /**
     * Initialize Assistant Deck's button
     */
    private void initializeAssistantDeck(){
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

    /**
     * Initialize Tower's color button
     */
    private void initializeTowerColors() {
        for (TColor tcolor : towerColors) {
            switch (tcolor) {
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
    }

    /**
     * Initialize buttons present in the Scene
     */
    private void initializeButtons(){
        whiteTower.setDisable(true);
        blackTower.setDisable(true);
        greyTower.setDisable(true);
        wizard1Button.setDisable(true);
        wizard2Button.setDisable(true);
        wizard3Button.setDisable(true);
        wizard4Button.setDisable(true);
    }

    /**
     * Handles the click on the White Tower button
     */
    private void clickWhite(Event event){
        initializeTowerColors();
        whiteShadowPane.setStyle("-fx-background-color: white");
        whiteShadowPane.setVisible(true);
        setColorChosen(TColor.WHITE);
    }

    /**
     * Handles the click on the Black Tower button
     */
    private void clickBlack(Event event){
        initializeTowerColors();
        blackShadowPane.setStyle("-fx-background-color: white");
        blackShadowPane.setVisible(true);
        setColorChosen(TColor.BLACK);
    }

    /**
     * Handles the click on the Grey Tower button
     */
    private void clickGrey(Event event){
        initializeTowerColors();
        greyShadowPane.setStyle("-fx-background-color: white");
        greyShadowPane.setVisible(true);
        setColorChosen(TColor.GREY);
    }

    /**
     * Handles the click on Wizard 1 button
     */
    private void clickWizard1(Event event){
        initializeAssistantDeck();
        wizard1ShadowPane.setStyle("-fx-background-color: white");
        wizard1ShadowPane.setVisible(true);
        setDeckChosen(AssistantDeckName.WIZARD1);
    }

    /**
     * Handles the click on Wizard 2 button
     */
    private void clickWizard2(Event event){
        initializeAssistantDeck();
        wizard2ShadowPane.setStyle("-fx-background-color: white");
        wizard2ShadowPane.setVisible(true);
        setDeckChosen(AssistantDeckName.WIZARD2);
    }

    /**
     * Handles the click on Wizard 3 button
     */
    private void clickWizard3(Event event){
        initializeAssistantDeck();
        wizard3ShadowPane.setStyle("-fx-background-color: white");
        wizard3ShadowPane.setVisible(true);
        setDeckChosen(AssistantDeckName.WIZARD3);
    }

    /**
     * Handles the click on Wizard 4 button
     */
    private void clickWizard4(Event event){
        initializeAssistantDeck();
        wizard4ShadowPane.setStyle("-fx-background-color: white");
        wizard4ShadowPane.setVisible(true);
        setDeckChosen(AssistantDeckName.WIZARD4);
    }

    public void setColorChosen(TColor colorChosen) {
        this.colorChosen = colorChosen;
    }

    public void setDeckChosen(AssistantDeckName deckChosen) {
        this.deckChosen = deckChosen;
    }
}
