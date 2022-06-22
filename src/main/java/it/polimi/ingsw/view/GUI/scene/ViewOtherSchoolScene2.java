package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.StartGUI;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Map;

public class ViewOtherSchoolScene2 extends ObservableView implements GenericScene {

    @FXML
    private Pane Pane;

    @FXML
    private ImageView blueBackground;

    @FXML
    private Button exitButton;

    @FXML
    private Text exitText;

    @FXML
    private Pane player1;

    @FXML
    private Pane player3;

    @FXML
    private Pane schoolPane1;

    @FXML
    private Pane schoolPane2;

    @FXML
    public void initialize(){
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickExit);
    }

    private void clickExit(Event event){
        exitButton.setDisable(true);

    }


}
