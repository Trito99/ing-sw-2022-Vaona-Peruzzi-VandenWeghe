package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import it.polimi.ingsw.view.GUI.StartGUI;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Map;

public class ViewOtherSchoolScene2 extends ObservableView implements GenericScene{

    @FXML
    private ImageView ImageTrash1_3Players;

    @FXML
    private ImageView ImageTrash2_3Players;

    @FXML
    private Pane Pane_3players;

    @FXML
    private ImageView blueBackground;

    @FXML
    private ImageView coinImage1_3Players;

    @FXML
    private ImageView coinImage2_3Players;

    @FXML
    private Pane coinPane1_3Players;

    @FXML
    private Pane coinPane2_3Players;

    @FXML
    private Text coinText1_3players;

    @FXML
    private Text coinText2_3players;

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
    private Pane trashPane1_3Players;

    @FXML
    private Pane trashPane2_3Players;

    @FXML
    public void initialize(){
    }

    public void updatePersonalSchool(Map<String, SchoolController> map) throws IOException {

        /** Player 1 */
        FXMLLoader loader1 = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader1.setController(map.get(map.keySet().toArray()[0]));
        AnchorPane School1 = loader1.load();
        schoolPane21.getChildren().setAll(School1);
        if(map.get(map.keySet().toArray()[0]).getDifficulty().equals(Difficulty.EXPERTMODE))
            coinText1_3players.setText(String.valueOf(map.get(map.keySet().toArray()[0]).getCoins()));
        else {
            coinText1_3players.setVisible(false);
            coinImage1_3Players.setVisible(false);
            coinPane1_3Players.setVisible(false);
        }

        if(map.get(map.keySet().toArray()[0]).getTrash()!=null) {
            ImageTrash1_3Players.setVisible(true);
            ImageTrash1_3Players.setImage(new Image(GuiManager.getMainScene().getAssistantCardMap().get(map.get(map.keySet().toArray()[0]).getTrash().getAssistantName())));
        }
        else
            ImageTrash1_3Players.setVisible(false);
        player1Text3players.setText(((String) map.keySet().toArray()[0]).substring(0,((String) map.keySet().toArray()[0]).length()-3));

        /**Player 2 */
        FXMLLoader loader2 = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader2.setController(map.get(map.keySet().toArray()[1]));
        AnchorPane School2 = loader2.load();
        schoolPane22.getChildren().setAll(School2);

        if(map.get(map.keySet().toArray()[1]).getDifficulty().equals(Difficulty.EXPERTMODE))
            coinText2_3players.setText(String.valueOf(map.get(map.keySet().toArray()[1]).getCoins()));
        else {
            coinText2_3players.setVisible(false);
            coinImage2_3Players.setVisible(false);
            coinPane2_3Players.setVisible(false);
        }

        if(map.get(map.keySet().toArray()[1]).getTrash()!=null) {
            ImageTrash2_3Players.setVisible(true);
            ImageTrash2_3Players.setImage(new Image(GuiManager.getMainScene().getAssistantCardMap().get(map.get(map.keySet().toArray()[1]).getTrash().getAssistantName())));
        }
        else
            ImageTrash2_3Players.setVisible(false);
        player2Text3players.setText(((String) map.keySet().toArray()[1]).substring(0,((String) map.keySet().toArray()[1]).length()-3));

    }
}
