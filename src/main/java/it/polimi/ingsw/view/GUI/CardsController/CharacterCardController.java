package it.polimi.ingsw.view.GUI.CardsController;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CharacterCardController {

    private static Map<CardEffect, String> characterCardMap;
    static {
        characterCardMap = new HashMap<>();
        characterCardMap.put(CardEffect.ABBOT, "/images/characterCards/abbot.jpg");
        characterCardMap.put(CardEffect.HOST, "/images/characterCards/host.jpg");
        characterCardMap.put(CardEffect.HERALD, "/images/characterCards/herald.jpg");
        characterCardMap.put(CardEffect.BEARER, "/images/characterCards/bearer.jpg");
        characterCardMap.put(CardEffect.CURATOR, "/images/characterCards/curator.jpg");
        characterCardMap.put(CardEffect.CENTAUR, "/images/characterCards/centaur.jpg");;
        characterCardMap.put(CardEffect.ACROBAT, "/images/characterCards/acrobat.jpg");
        characterCardMap.put(CardEffect.KNIGHT, "/images/characterCards/knight.jpg");
        characterCardMap.put(CardEffect.HERBALIST, "/images/characterCards/herbalist.jpg");
        characterCardMap.put(CardEffect.BARD, "/images/characterCards/bard.jpg");
        characterCardMap.put(CardEffect.COURTESAN, "/images/characterCards/courtesan.jpg");
        characterCardMap.put(CardEffect.JUNKDEALER, "/images/characterCards/junkdealer.jpg");
    }

   @FXML
   private ImageView cardImage;

    public void setData(CharacterCard card){
        cardImage.setImage(new Image(characterCardMap.get(card.getCardEffect())));
    }
}
