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
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Map;

public class ViewOtherSchoolScene3 extends ObservableView implements GenericScene {

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
    private Pane schoolPane11;

    @FXML
    private Pane schoolPane2;

    @FXML
    private Pane schoolPane22;

    @FXML
    private Pane schoolPane3;

    @FXML
    private Pane schoolPane33;

    @FXML
    public void initialize(){
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this ::clickExit);
    }

    private void clickExit(Event event){
        exitButton.setDisable(true);

    }

    public void updatePersonalSchool(Map<String, SchoolController> map) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader.setController(map.get(map.keySet().toArray()[0]));
        AnchorPane School1 = loader.load();
        schoolPane11.getChildren().setAll(School1);
        loader.setController(map.get(map.keySet().toArray()[1]));
        AnchorPane School2 = loader.load();
        schoolPane22.getChildren().setAll(School2);
        loader.setController(map.get(map.keySet().toArray()[2]));
        AnchorPane School3 = loader.load();
        schoolPane33.getChildren().setAll(School3);
    }

}
