package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import it.polimi.ingsw.view.GUI.StartGUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Map;

public class ViewOtherSchoolScene1 extends ObservableView implements GenericScene {

    @FXML
    private ImageView ImageTrash1_2Players;

    @FXML
    private Pane Pane_2players;

    @FXML
    private ImageView blueBackground;

    @FXML
    private ImageView coinImage1_2Players;

    @FXML
    private Pane coinPane1_2Players;

    @FXML
    private Text coinText1_2players;

    @FXML
    private Text player1Text;

    @FXML
    private Pane player1_2players;

    @FXML
    private Pane schoolPane11;

    @FXML
    private Pane schoolPane1_2players;

    @FXML
    private Pane trashPane1_2Players;

    @FXML
    public void initialize(){
    }


    public void updatePersonalSchool(Map<String, SchoolController> map) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader.setController(map.get(map.keySet().toArray()[0]));
        AnchorPane School1 = loader.load();
        schoolPane11.getChildren().setAll(School1);
        if(map.get(map.keySet().toArray()[0]).getDifficulty().equals(Difficulty.EXPERTMODE))
            coinText1_2players.setText(String.valueOf(map.get(map.keySet().toArray()[0]).getCoinsOfPlayer()));
        else {
            coinText1_2players.setVisible(false);
            coinImage1_2Players.setVisible(false);
            coinPane1_2Players.setVisible(false);
        }

        if(map.get(map.keySet().toArray()[0]).getTrash()!=null) {
            ImageTrash1_2Players.setVisible(true);
            ImageTrash1_2Players.setImage(new Image(GuiManager.getMainScene().getAssistantCardMap().get(map.get(map.keySet().toArray()[0]).getTrash().getAssistantName())));
        }
        else
            ImageTrash1_2Players.setVisible(false);
        player1Text.setText(((String) map.keySet().toArray()[0]).substring(0,((String) map.keySet().toArray()[0]).length()-3));

    }
}
