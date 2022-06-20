package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.observer.ObservableView;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class SchoolController extends ObservableView implements GenericScene {
    private School school;
    @FXML
    private Pane entrySchool;
    @FXML
    private Pane greenTable;
    @FXML
    private Pane redTable;
    @FXML
    private Pane yellowTable;
    @FXML
    private Pane pinkTable;
    @FXML
    private Pane blueTable;
    @FXML
    private Pane profHall;
    @FXML
    private Pane towerZone;


    public SchoolController(School school) {
        this.school = school;
    }

    public void initialize(){

    }
}
