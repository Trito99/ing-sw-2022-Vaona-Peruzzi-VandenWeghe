package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.observer.ObservableView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class SchoolController extends ObservableView implements GenericScene {
    private School school;
    private Map<ImageView,Integer> entryMap = new HashMap<>();
    @FXML
    private ImageView blue_1;

    @FXML
    private ImageView blue_10;

    @FXML
    private ImageView blue_2;

    @FXML
    private ImageView blue_3;

    @FXML
    private ImageView blue_4;

    @FXML
    private ImageView blue_5;

    @FXML
    private ImageView blue_6;

    @FXML
    private ImageView blue_7;

    @FXML
    private ImageView blue_8;

    @FXML
    private ImageView blue_9;

    @FXML
    private ImageView blue_entry_1;

    @FXML
    private ImageView blue_entry_2;

    @FXML
    private ImageView blue_entry_3;

    @FXML
    private ImageView blue_entry_4;

    @FXML
    private ImageView blue_entry_5;

    @FXML
    private ImageView blue_entry_6;

    @FXML
    private ImageView blue_entry_7;

    @FXML
    private ImageView blue_entry_8;

    @FXML
    private ImageView blue_entry_9;

    @FXML
    private ImageView blue_prof;

    @FXML
    private Pane blue_table;

    @FXML
    private Pane entry;

    @FXML
    private ImageView green_1;

    @FXML
    private ImageView green_10;

    @FXML
    private ImageView green_2;

    @FXML
    private ImageView green_3;

    @FXML
    private ImageView green_4;

    @FXML
    private ImageView green_5;

    @FXML
    private ImageView green_6;

    @FXML
    private ImageView green_7;

    @FXML
    private ImageView green_8;

    @FXML
    private ImageView green_9;

    @FXML
    private ImageView green_prof;

    @FXML
    private Pane green_table;

    @FXML
    private Pane hall;

    @FXML
    private ImageView pink_1;

    @FXML
    private ImageView pink_10;

    @FXML
    private ImageView pink_2;

    @FXML
    private ImageView pink_3;

    @FXML
    private ImageView pink_4;

    @FXML
    private ImageView pink_5;

    @FXML
    private ImageView pink_6;

    @FXML
    private ImageView pink_7;

    @FXML
    private ImageView pink_8;

    @FXML
    private ImageView pink_9;

    @FXML
    private ImageView pink_prof;

    @FXML
    private Pane pink_table;

    @FXML
    private ImageView red_1;

    @FXML
    private ImageView red_10;

    @FXML
    private ImageView red_2;

    @FXML
    private ImageView red_3;

    @FXML
    private ImageView red_4;

    @FXML
    private ImageView red_5;

    @FXML
    private ImageView red_6;

    @FXML
    private ImageView red_7;

    @FXML
    private ImageView red_8;

    @FXML
    private ImageView red_9;

    @FXML
    private ImageView red_prof;

    @FXML
    private Pane red_table;

    @FXML
    private Pane towerZone;

    @FXML
    private ImageView yellow_1;

    @FXML
    private ImageView yellow_10;

    @FXML
    private ImageView yellow_2;

    @FXML
    private ImageView yellow_3;

    @FXML
    private ImageView yellow_4;

    @FXML
    private ImageView yellow_5;

    @FXML
    private ImageView yellow_6;

    @FXML
    private ImageView yellow_7;

    @FXML
    private ImageView yellow_8;

    @FXML
    private ImageView yellow_9;

    @FXML
    private ImageView yellow_prof;

    @FXML
    private Pane yellow_table;


    public SchoolController(School school) {
        this.school = school;
    }

    public void initialize(){
        hide();
        updateSchool();
    }

    private void updateSchool(){
        for(Student student : school.getEntry()){
            entry.getChildren().get(school.getEntry().indexOf(student)).setVisible(true);
            entry.getChildren().get(school.getEntry().indexOf(student)).setDisable(false);
            switch(student.getsColour()){
                case GREEN:
                    ((ImageView) entry.getChildren().get(school.getEntry().indexOf(student))).setImage(green_1.getImage());
                    break;
                case RED:
                    ((ImageView) entry.getChildren().get(school.getEntry().indexOf(student))).setImage(red_1.getImage());
                    break;
                case YELLOW:
                    ((ImageView) entry.getChildren().get(school.getEntry().indexOf(student))).setImage(yellow_1.getImage());
                    break;
                case PINK:
                    ((ImageView) entry.getChildren().get(school.getEntry().indexOf(student))).setImage(pink_1.getImage());
                    break;
                case BLUE:
                    ((ImageView) entry.getChildren().get(school.getEntry().indexOf(student))).setImage(blue_1.getImage());
                    break;
            }
            entryMap.put((ImageView) entry.getChildren().get(school.getEntry().indexOf(student)),student.getIdStudent());
        }

    }

    private void hide(){
        for(Node node : entry.getChildren()){
            node.setVisible(false);
            node.setDisable(true);
        }
    }
}
