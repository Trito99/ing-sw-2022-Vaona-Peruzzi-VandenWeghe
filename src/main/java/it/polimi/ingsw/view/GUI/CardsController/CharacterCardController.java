package it.polimi.ingsw.view.GUI.CardsController;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.student.Student;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
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

    public void activateStudentPane(ArrayList<Student> studentsOnCard){
        longPane.setVisible(true);
        shortPane.setVisible(false);

        coinPane.setVisible(false);
        xCardPane.setVisible(false);
        xCardPane.setDisable(true);
        studentPane.setVisible(true);
        studentPane.setDisable(false);
        for(int i=0;i<studentsOnCard.size();i++){
            switch (studentsOnCard.get(i).getsColour()){
                case GREEN:
                    ((ImageView) studentPane.getChildren().get(i)).setImage(new Image("/images/students/Gstudent.png"));
                    break;
                case RED:
                    ((ImageView) studentPane.getChildren().get(i)).setImage(new Image("/images/students/Rstudent.png"));
                    break;
                case YELLOW:
                    ((ImageView) studentPane.getChildren().get(i)).setImage(new Image("/images/students/Ystudent.png"));
                    break;
                case PINK:
                    ((ImageView) studentPane.getChildren().get(i)).setImage(new Image("/images/students/Pstudent.png"));
                    break;
                case BLUE:
                    ((ImageView) studentPane.getChildren().get(i)).setImage(new Image("/images/students/Bstudent.png"));
                    break;
            }
        }
    }

    public void activateXCardPane(int XCardsOnCard){
        longPane.setVisible(true);
        shortPane.setVisible(false);

        coinPane.setVisible(false);
        studentPane.setVisible(false);
        studentPane.setDisable(true);
        xCardPane.setVisible(true);
        xCardPane.setDisable(false);
        for(int i=0;i<XCardsOnCard;i++){
            xCardPane.getChildren().get(i).setVisible(true);
            xCardPane.getChildren().get(i).setDisable(false);
        }
    }

    public void disableAll(){
        longPane.setVisible(false);
        shortPane.setVisible(true);

        coinPane.setVisible(false);
        studentPane.setVisible(false);
        studentPane.setDisable(true);
        xCardPane.setVisible(false);
        xCardPane.setDisable(true);
        for(int i=0;i<xCardPane.getChildren().size();i++){
            xCardPane.getChildren().get(i).setVisible(false);
            xCardPane.getChildren().get(i).setDisable(true);
        }
        for(int i=0;i<studentPane.getChildren().size();i++){
            studentPane.getChildren().get(i).setVisible(false);
            studentPane.getChildren().get(i).setDisable(true);
        }
    }

}
