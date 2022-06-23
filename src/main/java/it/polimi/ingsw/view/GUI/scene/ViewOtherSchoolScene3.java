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

public class ViewOtherSchoolScene3 extends ObservableView implements GenericScene{

    @FXML
    private Pane Pane_4players;

    @FXML
    private ImageView blueBackground;

    @FXML
    private Button exitButton;

    @FXML
    private Text exitText;

    @FXML
    private Text player1Text4players;

    @FXML
    private Pane player1_4players;

    @FXML
    private Text player2Text4players;

    @FXML
    private Pane player2_4players;

    @FXML
    private Text player3Text4players;

    @FXML
    private Pane player3_4players;

    @FXML
    private Pane schoolPane1_4players;

    @FXML
    private Pane schoolPane2_4players;

    @FXML
    private Pane schoolPane31;

    @FXML
    private Pane schoolPane32;

    @FXML
    private Pane schoolPane33;

    @FXML
    private Pane schoolPane3_4players;

    @FXML
    public void initialize(){

    }


    public void updatePersonalSchool(Map<String, SchoolController> map) throws IOException {
        FXMLLoader loader1 = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader1.setController(map.get(map.keySet().toArray()[0]));
        AnchorPane School1 = loader1.load();
        schoolPane31.getChildren().setAll(School1);
        player1Text4players.setText(((String) map.keySet().toArray()[0]).substring(0,((String) map.keySet().toArray()[0]).length()-2));
        FXMLLoader loader2 = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader2.setController(map.get(map.keySet().toArray()[1]));
        AnchorPane School2 = loader2.load();
        schoolPane32.getChildren().setAll(School2);
        player2Text4players.setText(((String) map.keySet().toArray()[1]).substring(0,((String) map.keySet().toArray()[1]).length()- 3));
        FXMLLoader loader3 = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader3.setController(map.get(map.keySet().toArray()[2]));
        AnchorPane School3 = loader3.load();
        schoolPane33.getChildren().setAll(School3);
        player3Text4players.setText(((String) map.keySet().toArray()[2]).substring(0,((String) map.keySet().toArray()[2]).length()- 3));
    }

}
