package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ViewDeckScene  extends ObservableView implements GenericScene  {

    private DeckAssistant deckAssistant;

    private String cardChosen;

    @FXML
    private Pane Pane;
    @FXML
    private ImageView blueBackground;

    @FXML
    private Button card1;
    @FXML
    private Button card2;
    @FXML
    private Button card3;
    @FXML
    private Button card4;
    @FXML
    private Button card5;
    @FXML
    private Button card6;
    @FXML
    private Button card7;
    @FXML
    private Button card8;
    @FXML
    private Button card9;
    @FXML
    private Button card10;

    @FXML
    private Pane card1ShadowPane;
    @FXML
    private Pane card2ShadowPane;
    @FXML
    private Pane card3ShadowPane;
    @FXML
    private Pane card4ShadowPane;
    @FXML
    private Pane card5ShadowPane;
    @FXML
    private Pane card6ShadowPane;
    @FXML
    private Pane card7ShadowPane;
    @FXML
    private Pane card8ShadowPane;
    @FXML
    private Pane card9ShadowPane;
    @FXML
    private Pane card10ShadowPane;

    @FXML
    private ImageView imageCard1;
    @FXML
    private ImageView imageCard2;
    @FXML
    private ImageView imageCard3;
    @FXML
    private ImageView imageCard4;
    @FXML
    private ImageView imageCard5;
    @FXML
    private ImageView imageCard6;
    @FXML
    private ImageView imageCard7;
    @FXML
    private ImageView imageCard8;
    @FXML
    private ImageView imageCard9;
    @FXML
    private ImageView imageCard10;

    @FXML
    private Button playButton;
    @FXML
    private Text playText;
    @FXML
    private Button exitButton;
    @FXML
    private Text exitText;

    public ViewDeckScene(DeckAssistant deckAssistant) {
        this.deckAssistant = deckAssistant;
    }

    @FXML
    public void initialize(){
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickPlay);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickExit);
        initializeButtons();
        initializeCardsInHand();
        card1.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCard1);
        card2.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCard2);
        card3.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCard3);
        card4.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCard4);
        card5.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCard5);
        card6.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCard6);
        card7.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCard7);
        card8.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCard8);
        card9.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCard9);
        card10.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCard10);
    }

    @FXML
    private void clickPlay(Event event) {
        if(cardChosen != null){
            playButton.setDisable(true);
            playButton.setVisible(false);
            notifyObserver(obs -> obs.chooseAssistantCard(cardChosen));
            GuiManager.getMainScene().setPlanning(false);
            GuiManager.closeStage(this);
        }else {
            playButton.setDisable(false);
            playButton.setVisible(true);
        }
    }

    private void clickExit(Event event){
        exitButton.setDisable(true);
        /** da fare */
    }
    private void initializeButtons(){
        playButton.setDisable(true);
        playButton.setVisible(false);
        playText.setVisible(false);
        card1.setDisable(true);
        card2.setDisable(true);
        card3.setDisable(true);
        card4.setDisable(true);
        card5.setDisable(true);
        card6.setDisable(true);
        card7.setDisable(true);
        card8.setDisable(true);
        card9.setDisable(true);
        card10.setDisable(true);
    }

    private void initializeCardsInHand(){
        for(AssistantCard card : deckAssistant.getCardsInHand()){
            switch(card.getAssistantName()){
                case "lion":
                    card1.setDisable(false);
                    card1ShadowPane.setVisible(false);
                case "ostrich":
                    card2.setDisable(false);
                    card2ShadowPane.setVisible(false);
                case "cat":
                    card3.setDisable(false);
                    card3ShadowPane.setVisible(false);
                case "falcon":
                    card4.setDisable(false);
                    card4ShadowPane.setVisible(false);
                case "fox":
                    card5.setDisable(false);
                    card5ShadowPane.setVisible(false);
                case "lizard":
                    card6.setDisable(false);
                    card6ShadowPane.setVisible(false);
                case "octopus":
                    card7.setDisable(false);
                    card7ShadowPane.setVisible(false);
                case "dog":
                    card8.setDisable(false);
                    card8ShadowPane.setVisible(false);
                case "elephant":
                    card9.setDisable(false);
                    card9ShadowPane.setVisible(false);
                case "turtle":
                    card10.setDisable(false);
                    card10ShadowPane.setVisible(false);
            }
        }
    }

    private void clickCard1(Event event){
        initializeCardsInHand();
        card1ShadowPane.setStyle("-fx-background-color: blue");
        card1ShadowPane.setVisible(true);
        setCardChosen("lion");
    }

    private void clickCard2(Event event){
        initializeCardsInHand();
        card2ShadowPane.setStyle("-fx-background-color: blue");
        card2ShadowPane.setVisible(true);
        setCardChosen("ostrich");
    }

    private void clickCard3(Event event){
        initializeCardsInHand();
        card3ShadowPane.setStyle("-fx-background-color: blue");
        card3ShadowPane.setVisible(true);
        setCardChosen("cat");
    }

    private void clickCard4(Event event){
        initializeCardsInHand();
        card4ShadowPane.setStyle("-fx-background-color: blue");
        card4ShadowPane.setVisible(true);
        setCardChosen("falcon");
    }

    private void clickCard5(Event event){
        initializeCardsInHand();
        card5ShadowPane.setStyle("-fx-background-color: blue");
        card5ShadowPane.setVisible(true);
        setCardChosen("fox");
    }

    private void clickCard6(Event event){
        initializeCardsInHand();
        card6ShadowPane.setStyle("-fx-background-color: blue");
        card6ShadowPane.setVisible(true);
        setCardChosen("lizard");
    }

    private void clickCard7(Event event){
        initializeCardsInHand();
        card7ShadowPane.setStyle("-fx-background-color: blue");
        card7ShadowPane.setVisible(true);
        setCardChosen("octopus");
    }

    private void clickCard8(Event event){
        initializeCardsInHand();
        card8ShadowPane.setStyle("-fx-background-color: blue");
        card8ShadowPane.setVisible(true);
        setCardChosen("dog");
    }
    private void clickCard9(Event event){
        initializeCardsInHand();
        card9ShadowPane.setStyle("-fx-background-color: blue");
        card9ShadowPane.setVisible(true);
        setCardChosen("elephant");
    }
    private void clickCard10(Event event){
        initializeCardsInHand();
        card9ShadowPane.setStyle("-fx-background-color: blue");
        card9ShadowPane.setVisible(true);
        setCardChosen("turtle");
    }

    private void setCardChosen(String cardChosen){
        this.cardChosen = cardChosen;
    }

    public DeckAssistant getDeckAssistant(){
        return deckAssistant;
    }

    public void activatePlayButton(){
        playButton.setDisable(false);
        playButton.setVisible(true);
        playText.setVisible(true);
    }


}
