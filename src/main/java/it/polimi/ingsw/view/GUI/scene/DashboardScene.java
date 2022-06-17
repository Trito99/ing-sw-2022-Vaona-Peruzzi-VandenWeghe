package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.CardsController.CharacterCardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashboardScene extends ObservableView implements GenericScene {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private VBox characterCardLayout;

    public void initialize(){
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
