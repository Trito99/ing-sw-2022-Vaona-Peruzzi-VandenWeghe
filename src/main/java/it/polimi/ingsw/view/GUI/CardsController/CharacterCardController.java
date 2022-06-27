package it.polimi.ingsw.view.GUI.CardsController;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.scene.GenericScene;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

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
    private Pane PaneCharacterCard;
    @FXML
    private Pane coinPane;
    @FXML
    private ImageView cardImage;
    @FXML
    private ImageView coinImagePersonal;

    @FXML
    private Pane studentPane;
    @FXML
    private Pane xCardPane;

    @FXML
    private ImageView stud1;
    @FXML
    private ImageView stud2;
    @FXML
    private ImageView stud3;
    @FXML
    private ImageView stud4;
    @FXML
    private ImageView stud5;
    @FXML
    private ImageView stud6;

    @FXML
    private ImageView xCard1;
    @FXML
    private ImageView xCard2;
    @FXML
    private ImageView xCard3;
    @FXML
    private ImageView xCard4;

    @FXML
    private Pane longPane;

    @FXML
    private Pane shortPane;


    public void initialize() {
    }

    public void setData(CharacterCard card){
        cardImage.setImage(new Image(characterCardMap.get(card.getCardEffect())));
    }

    public void abilitateStudentPane(){
        longPane.setVisible(true);
        shortPane.setVisible(false);

        coinPane.setVisible(false);
        xCardPane.setVisible(false);
        xCardPane.setDisable(true);
        studentPane.setVisible(true);
        studentPane.setDisable(false);
    }

    public void abilitateXCardPane(){
        longPane.setVisible(true);
        shortPane.setVisible(false);

        coinPane.setVisible(false);
        studentPane.setVisible(false);
        studentPane.setDisable(true);
        xCardPane.setVisible(true);
        xCardPane.setDisable(false);
    }

    public void disableAll(){
        longPane.setVisible(false);
        shortPane.setVisible(true);

        coinPane.setVisible(false);
        studentPane.setVisible(false);
        studentPane.setDisable(true);
        xCardPane.setVisible(false);
        xCardPane.setDisable(true);
    }

}
