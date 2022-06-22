package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.observer.ObservableView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

public class ViewOtherSchoolScene extends ObservableView implements GenericScene {

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
        private Pane player2;
        @FXML
        private Pane player3;
        @FXML
        private Pane schoolPane1;
        @FXML
        private Pane schoolPane2;
        @FXML
        private Pane schoolPane3;

    @FXML
    public void initialize(){
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickExit);
    }

    private void clickExit(Event event){
        exitButton.setDisable(true);

    }

}
