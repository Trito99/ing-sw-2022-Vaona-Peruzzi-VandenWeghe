package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import it.polimi.ingsw.view.GUI.StartGUI;
import it.polimi.ingsw.view.View;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardScene extends ObservableView implements GenericScene {

    private Map<String,SchoolController> schoolControllers;

    private GameMode gameMode;
    private Difficulty difficulty;

    @FXML
    private VBox characterCardLayout;

    @FXML
    private ImageView coinImage;

    @FXML
    private Pane coinPane;

    @FXML
    private Text coinText;

    @FXML
    private Button deckButton;

    @FXML
    private ImageView deckLogo;

    @FXML
    private ImageView island0;

    @FXML
    private ImageView island1;

    @FXML
    private ImageView island10;

    @FXML
    private ImageView island11;

    @FXML
    private ImageView island2;

    @FXML
    private ImageView island3;

    @FXML
    private ImageView island4;

    @FXML
    private ImageView island5;

    @FXML
    private ImageView island6;

    @FXML
    private ImageView island7;

    @FXML
    private ImageView island8;

    @FXML
    private ImageView island9;

    @FXML
    private Pane islandPane;

    @FXML
    private ImageView motherEarth;

    @FXML
    private Button otherSchoolButton;

    @FXML
    private ImageView school;

    @FXML
    private Pane schoolPane;

    @FXML
    private Pane schoolPane1;

    @FXML
    private Pane schoolViewPane;

    @FXML
    private Pane sxPane;

    @FXML
    private Pane trashPane;

    @FXML
    private Pane turnInfoPane;


    public void updatePersonalSchool(SchoolController controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader.setController(controller);
        AnchorPane personalSchool = loader.load();
        schoolPane.getChildren().setAll(personalSchool);
    }

    public void initializeDifficulty(Difficulty difficulty, int coinsOnTable){
        this.difficulty = difficulty;
        if(difficulty.equals(Difficulty.STANDARDMODE)){
            coinPane.setVisible(false);
            coinPane.setDisable(true);
            coinImage.setVisible(false);
            coinImage.setDisable(true);
        }else{
            coinText.setText(String.valueOf(coinsOnTable));
        }
    }

    @FXML
    public void initialize(){
        otherSchoolButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                otherSchoolClicked(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        /**
        ArrayList<CharacterCard> characterCardsPlaying = new ArrayList<>((characterCardsPlaying()));
        try{
            for(int i = 0; i < characterCardsPlaying().size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/characterCard.fxml"));
                ImageView characterCardImage = loader.load();

                CharacterCardController characterCardController = loader.getController();
                characterCardController.setData(characterCardsPlaying.get(i));
                characterCardLayout.getChildren().add(characterCardImage);
                }
        } catch (IOException e) {
                e.printStackTrace();

        }*/
    }

    private void otherSchoolClicked(Event event) throws IOException {
        otherSchoolButton.setDisable(true);

        switch(gameMode){
            case TWOPLAYERS:
                ViewOtherSchoolScene1 scene1 = new ViewOtherSchoolScene1();
                scene1.updatePersonalSchool(schoolControllers);
                GuiManager.setScene("/fxml/view_other_school_1_scene",scene1);
                break;
            case THREEPLAYERS:
                ViewOtherSchoolScene2 scene2 = new ViewOtherSchoolScene2();
                //scene2.updatePersonalSchool(schoolControllers);
                //GuiManager.setScene("/fxml/view_other_school_2_scene");
                break;
            case COOP:
                ViewOtherSchoolScene3 scene3 = new ViewOtherSchoolScene3();
                scene3.updatePersonalSchool(schoolControllers);
                GuiManager.setScene("/fxml/view_other_school_3_scene",scene3);
                break;
        }
    }

    public void updateOtherSchool(SchoolController controller, GameMode gameMode, String nickname){
        this.gameMode = gameMode;
        if(schoolControllers.containsKey(nickname)){
            schoolControllers.remove(nickname);
            schoolControllers.put(nickname,controller);
        }else{
            schoolControllers.put(nickname,controller);
        }

    }


    /** esempio inizializzazione carta personaggio -> da sistemare con */
    private List<CharacterCard> characterCardsPlaying(){
        List<CharacterCard> cards = new ArrayList<>();
        CharacterCard characterCard = null /**= new CharacterCard() */ ;
        characterCard.setImageSrc("/images/characterCards/characterCard_front.jpg");

        /** per le tre carte */
        return cards;
    }

}
