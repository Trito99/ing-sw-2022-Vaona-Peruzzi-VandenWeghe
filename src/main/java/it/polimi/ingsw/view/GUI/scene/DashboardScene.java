package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.CardsController.CharacterCardController;
import it.polimi.ingsw.view.GUI.GuiManager;
import it.polimi.ingsw.view.GUI.StartGUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashboardScene extends ObservableView implements GenericScene {

    private SchoolController schoolController;
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
    private Pane schoolViewPane;

    @FXML
    private Pane sxPane;

    @FXML
    private Pane trashPane;

    @FXML
    private Pane turnInfoPane;


    public void updateSchool(SchoolController controller) throws IOException {

        FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource("/fxml/school.fxml"));
        loader.setController(controller);
        schoolPane = loader.load();
    }

    public void initializeDifficulty(Difficulty difficulty, int coinsOnTable){
        if(difficulty.equals(Difficulty.STANDARDMODE)){
            coinPane.setVisible(false);
            coinPane.setDisable(true);
            coinImage.setVisible(false);
            coinImage.setDisable(true);
        }else{
            coinText.setText(String.valueOf(coinsOnTable));
        }
    }
    public void initialize(){


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

    /** esempio inizializzazione carta personaggio -> da sistemare con */
    private List<CharacterCard> characterCardsPlaying(){
        List<CharacterCard> cards = new ArrayList<>();
        CharacterCard characterCard = null /**= new CharacterCard() */ ;
        characterCard.setImageSrc("/images/characterCards/characterCard_front.jpg");

        /** per le tre carte */
        return cards;
    }

    public void setSchoolController(SchoolController schoolController) {
        this.schoolController = schoolController;
    }
}
