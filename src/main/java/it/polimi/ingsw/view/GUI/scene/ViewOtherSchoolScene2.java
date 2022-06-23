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

public class ViewOtherSchoolScene2 extends ObservableView implements GenericScene{

    @FXML
    private Pane Pane_3players;

    @FXML
    private ImageView blueBackground;

    @FXML
    private Button exitButton;

    @FXML
    private Text exitText;

    @FXML
    private Text player1Text3players;

    @FXML
    private Pane player1_3players;

    @FXML
    private Text player2Text3players;

    @FXML
    private Pane player3_3players;

    @FXML
    private Pane schoolPane1_3players;

    @FXML
    private Pane schoolPane21;

    @FXML
    private Pane schoolPane22;

    @FXML
    private Pane schoolPane2_3players;

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
        schoolPane21.getChildren().setAll(School1);
        player1Text3players.setText(((String) map.keySet().toArray()[0]).substring(0,((String) map.keySet().toArray()[0]).length()-3));
        loader.setController(map.get(map.keySet().toArray()[1]));
        AnchorPane School2 = loader.load();
        schoolPane22.getChildren().setAll(School2);
        player1Text3players.setText(((String) map.keySet().toArray()[1]).substring(1,((String) map.keySet().toArray()[1]).length()- 2));
    }

}
