package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ViewDeckScene  extends ObservableView implements GenericScene  {

    @FXML
    private Pane Pane;
    @FXML
    private ImageView blueBackground;

    @FXML
    private Button card1;
    @FXML
    private Button card2;
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

    @FXML
    public void initialize(){
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickPlay);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickExit);
    }

    @FXML
    private void clickPlay(Event event) {
        playButton.setDisable(true);

    }

    private void clickExit(Event event){
        exitButton.setDisable(true);

    }
}
