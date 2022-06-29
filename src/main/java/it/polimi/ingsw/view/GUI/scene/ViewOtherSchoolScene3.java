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
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Map;

/**
 * This Scene Controller is used to show and update other players' school and trash card
 * Used in COOP mode (4 players)
 */

public class ViewOtherSchoolScene3 extends ObservableView implements GenericScene{
    @FXML
    private ImageView ImageTrash1_4Players;
    @FXML
    private ImageView ImageTrash2_4Players;
    @FXML
    private ImageView ImageTrash3_4Players;
    @FXML
    private Pane Pane_4players;
    @FXML
    private ImageView blueBackground;
    @FXML
    private ImageView coinImage1_4Players;
    @FXML
    private ImageView coinImage2_4Players;
    @FXML
    private ImageView coinImage3_4Players;
    @FXML
    private Pane coinPane1_4Players;
    @FXML
    private Pane coinPane2_4Players;
    @FXML
    private Pane coinPane3_4Players;
    @FXML
    private Text coinText1_4players;
    @FXML
    private Text coinText2_4players;
    @FXML
    private Text coinText3_4players;
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
    private Pane trashPane1_4Players;
    @FXML
    private Pane trashPane2_4Players;
    @FXML
    private Pane trashPane3_4Players;

    /**
     * Initialize player's school
     */
    @FXML
    public void initialize(){

    }

    /**
     * Updates player's school and trash card
     */
    public void updatePersonalSchool(Map<String, SchoolController> map) throws IOException {

        /** Player 1 */
        FXMLLoader loader1 = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader1.setController(map.get(map.keySet().toArray()[0]));
        AnchorPane School1 = loader1.load();
        schoolPane31.getChildren().setAll(School1);
        if(map.get(map.keySet().toArray()[0]).getDifficulty().equals(Difficulty.EXPERTMODE))
            coinText1_4players.setText(String.valueOf(map.get(map.keySet().toArray()[0]).getCoinsOfPlayer()));
        else {
            coinText1_4players.setVisible(false);
            coinImage1_4Players.setVisible(false);
            coinPane1_4Players.setVisible(false);
        }

        if(map.get(map.keySet().toArray()[0]).getTrash()!=null) {
            ImageTrash1_4Players.setVisible(true);
            ImageTrash1_4Players.setImage(new Image(GuiManager.getMainScene().getAssistantCardMap().get(map.get(map.keySet().toArray()[0]).getTrash().getAssistantName())));
        }
        else
            ImageTrash1_4Players.setVisible(false);
        player1Text4players.setText(((String) map.keySet().toArray()[0]).substring(0,((String) map.keySet().toArray()[0]).length()-3));

        /**Player 2 */
        FXMLLoader loader2 = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader2.setController(map.get(map.keySet().toArray()[1]));
        AnchorPane School2 = loader2.load();
        schoolPane32.getChildren().setAll(School2);

        if(map.get(map.keySet().toArray()[1]).getDifficulty().equals(Difficulty.EXPERTMODE))
            coinText2_4players.setText(String.valueOf(map.get(map.keySet().toArray()[1]).getCoinsOfPlayer()));
        else {
            coinText2_4players.setVisible(false);
            coinImage2_4Players.setVisible(false);
            coinPane2_4Players.setVisible(false);
        }

        if(map.get(map.keySet().toArray()[1]).getTrash()!=null) {
            ImageTrash2_4Players.setVisible(true);
            ImageTrash2_4Players.setImage(new Image(GuiManager.getMainScene().getAssistantCardMap().get(map.get(map.keySet().toArray()[1]).getTrash().getAssistantName())));
        }
        else
            ImageTrash2_4Players.setVisible(false);
        player2Text4players.setText(((String) map.keySet().toArray()[1]).substring(0,((String) map.keySet().toArray()[1]).length()-3));

        /** Player 3 */
        FXMLLoader loader3 = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader3.setController(map.get(map.keySet().toArray()[2]));
        AnchorPane School3 = loader3.load();
        schoolPane33.getChildren().setAll(School3);

        if(map.get(map.keySet().toArray()[2]).getDifficulty().equals(Difficulty.EXPERTMODE))
            coinText3_4players.setText(String.valueOf(map.get(map.keySet().toArray()[2]).getCoinsOfPlayer()));
        else {
            coinText3_4players.setVisible(false);
            coinImage3_4Players.setVisible(false);
            coinPane3_4Players.setVisible(false);
        }

        if(map.get(map.keySet().toArray()[2]).getTrash()!=null) {
            ImageTrash3_4Players.setVisible(true);
            ImageTrash3_4Players.setImage(new Image(GuiManager.getMainScene().getAssistantCardMap().get(map.get(map.keySet().toArray()[2]).getTrash().getAssistantName())));
        }
        else
            ImageTrash3_4Players.setVisible(false);
        player3Text4players.setText(((String) map.keySet().toArray()[2]).substring(0,((String) map.keySet().toArray()[2]).length()-3));
    }

}
