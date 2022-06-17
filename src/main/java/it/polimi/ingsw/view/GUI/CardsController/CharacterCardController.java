package it.polimi.ingsw.view.GUI.CardsController;

import it.polimi.ingsw.model.character.CharacterCard;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.*;

public class CharacterCardController {

   @FXML
   private ImageView cardImage;

    public void setData(CharacterCard card){
        Image image = new Image(getClass().getResourceAsStream(card.getImageSrc()));
        cardImage.setImage(image);
    }
}
