package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.school.Prof;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.observer.ObservableView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.io.File;
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
    private Pane profPane;

    @FXML
    private ImageView red_1;

    @FXML
    private ImageView red_10;

    @FXML
    private ImageView red_101;

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
    private ImageView schoolBackground;

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
        updateEntry();
        updateHall();
        updateProf();
        updateTowerZone();
    }

    private void updateEntry(){
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

    private void updateHall() {
        for(Student student : school.getGTable()){
            green_table.getChildren().get(school.getGTable().indexOf(student)).setVisible(true);
            green_table.getChildren().get(school.getGTable().indexOf(student)).setDisable(false);
            //entryMap.put((ImageView) entry.getChildren().get(school.getEntry().indexOf(student)),student.getIdStudent());
        }
        for(Student student : school.getRTable()){
            red_table.getChildren().get(school.getRTable().indexOf(student)).setVisible(true);
            red_table.getChildren().get(school.getRTable().indexOf(student)).setDisable(false);
            //entryMap.put((ImageView) entry.getChildren().get(school.getEntry().indexOf(student)),student.getIdStudent());
        }
        for(Student student : school.getYTable()){
            yellow_table.getChildren().get(school.getYTable().indexOf(student)).setVisible(true);
            yellow_table.getChildren().get(school.getYTable().indexOf(student)).setDisable(false);
            //entryMap.put((ImageView) entry.getChildren().get(school.getEntry().indexOf(student)),student.getIdStudent());
        }
        for(Student student : school.getPTable()){
            pink_table.getChildren().get(school.getPTable().indexOf(student)).setVisible(true);
            pink_table.getChildren().get(school.getPTable().indexOf(student)).setDisable(false);
            //entryMap.put((ImageView) entry.getChildren().get(school.getEntry().indexOf(student)),student.getIdStudent());
        }
        for(Student student : school.getBTable()){
            blue_table.getChildren().get(school.getBTable().indexOf(student)).setVisible(true);
            blue_table.getChildren().get(school.getBTable().indexOf(student)).setDisable(false);
            //entryMap.put((ImageView) entry.getChildren().get(school.getEntry().indexOf(student)),student.getIdStudent());
        }

    }

    private void updateProf() {
        for (Prof prof : school.getProfOfPlayer()){
            if(prof.getIsInHall()){
                switch(prof.getSColour()){
                    case GREEN:
                        green_prof.setVisible(true);
                        break;
                    case RED:
                        red_prof.setVisible(true);
                        break;
                    case YELLOW:
                        yellow_prof.setVisible(true);
                        break;
                    case PINK:
                        pink_prof.setVisible(true);
                        break;
                    case BLUE:
                        blue_prof.setVisible(true);
                        break;
                }
            }
        }
    }

    private void updateTowerZone() {
        for(Tower tower : school.getTowers()){
            towerZone.getChildren().get(school.getTowers().indexOf(tower)).setDisable(false);
            switch (tower.getTColour()){
                case WHITE:
                    ((ImageView) towerZone.getChildren().get(school.getTowers().indexOf(tower))).setImage(new Image("/images/towers/Twhite.png"));
                    break;
                case BLACK:
                    ((ImageView) towerZone.getChildren().get(school.getTowers().indexOf(tower))).setImage(new Image("/images/towers/Tblack.png"));
                    break;
                case GREY:
                    ((ImageView) towerZone.getChildren().get(school.getTowers().indexOf(tower))).setImage(new Image("/images/towers/Tgrey.png"));
                    break;
            }
        }
    }


    private void hide(){
        for(Node node : entry.getChildren()){
            node.setVisible(false);
            node.setDisable(true);
        }
        for(Node table : hall.getChildren()) {
            if (table instanceof Pane) {
                for (Node student : ((Pane) table).getChildren()) {
                    student.setVisible(false);
                    student.setDisable(true);
                }
            }
        }
        for(Node prof : profPane.getChildren()) {
            prof.setVisible(false);
            prof.setDisable(true);
        }
        for(Node node : towerZone.getChildren()){
            node.setDisable(true);
        }
    }
}
