package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.school.Prof;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

/**
 * This Scene Controller is used to update and play with a specific personal school
 * It also updates the schools of the other players
 */
public class SchoolController extends ObservableView implements GenericScene {
    private School school;
    private boolean dragDone = false;
    private int studentSelected;
    private int[] studentSelectedForSwitch = new int[3];

    private int[] studentSelectedForSwitchHall = new int[2];
    private String placeSelected;
    private int coinsOfPlayer;
    private AssistantCard trash;
    private Difficulty difficulty;
    private Map<ImageView,Integer> entryMap = new HashMap<>();
    private Map<ImageView,Integer> hallMap = new HashMap<>();

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
    private AnchorPane rootPane;
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

    /**
     * Default constructor
     * @param school player's personal school
     * @param trash last Assistant Card played by the player
     * @param difficulty mode of the match
     * @param coinsOfPlayer amount of coins of the player
     */
    public SchoolController(School school, AssistantCard trash, Difficulty difficulty, int coinsOfPlayer) {
        this.school = school;
        this.trash = trash;
        this.difficulty = difficulty;
        this.coinsOfPlayer = coinsOfPlayer;
    }

    /**
     * Initialize players school
     */
    public void initialize(){
        hide();
        updateSchool();
        addDragDetected();
        addDragOver();
    }

    /**
     * Updates schools situation
     */
    private void updateSchool(){
        updateEntry();
        updateHall();
        updateProf();
        updateTowerZone();
    }

    /**
     * Updates schools entry
     */
    private void updateEntry(){
        entryMap.clear();
        for(Student student : school.getEntry()){
            entry.getChildren().get(school.getEntry().indexOf(student)).setVisible(true);
            switch(student.getSColor()){
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

    /**
     * Updates schools hall
     */
    private void updateHall() {
        hallMap.clear();
        for(Student student : school.getGTable()){
            green_table.getChildren().get(school.getGTable().indexOf(student)).setVisible(true);
            hallMap.put((ImageView) green_table.getChildren().get(school.getGTable().indexOf(student)),student.getIdStudent());
        }
        for(Student student : school.getRTable()){
            red_table.getChildren().get(school.getRTable().indexOf(student)).setVisible(true);
            hallMap.put((ImageView) red_table.getChildren().get(school.getRTable().indexOf(student)),student.getIdStudent());
        }
        for(Student student : school.getYTable()){
            yellow_table.getChildren().get(school.getYTable().indexOf(student)).setVisible(true);
            hallMap.put((ImageView) yellow_table.getChildren().get(school.getYTable().indexOf(student)),student.getIdStudent());
        }
        for(Student student : school.getPTable()){
            pink_table.getChildren().get(school.getPTable().indexOf(student)).setVisible(true);
            hallMap.put((ImageView) pink_table.getChildren().get(school.getPTable().indexOf(student)),student.getIdStudent());
        }
        for(Student student : school.getBTable()){
            blue_table.getChildren().get(school.getBTable().indexOf(student)).setVisible(true);
            hallMap.put((ImageView) blue_table.getChildren().get(school.getBTable().indexOf(student)),student.getIdStudent());
        }

    }

    /**
     * Updates Professors hall
     */
    private void updateProf() {
        for (Prof prof : school.getProfOfPlayer()){
            if(prof.getIsInHall()){
                switch(prof.getSColor()){
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

    /**
     * Updates schools Tower Zone
     */
    private void updateTowerZone() {
        for(Tower tower : school.getTowers()){
            //towerZone.getChildren().get(school.getTowers().indexOf(tower)).setDisable(false);
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

    /**
     * Hides panes that are not used yet
     */
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

    public int getCoinsOfPlayer() {
        return coinsOfPlayer;
    }

    public AssistantCard getTrash() {
        return trash;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Disables moves in school entry
     */
    public void disableEntry(boolean disable){
        for(Student student : school.getEntry()) {
            entry.getChildren().get(school.getEntry().indexOf(student)).setDisable(disable);
        }
    }

    public Map<ImageView,Integer> getEntryMap(){
        return entryMap;
    }

    public int getStudentSelected() {
        return studentSelected;
    }

    public void setStudentSelected(int studentSelected) {
        this.studentSelected = studentSelected;
    }

    public String getPlaceSelected() {
        return placeSelected;
    }

    public void setPlaceSelected(String placeSelected) {
        this.placeSelected = placeSelected;
    }

    /**
     * Enables drag and drop of students
     */
    private void addDragDetected() {
        for(Student student : school.getEntry()) {
            ImageView studentNode = (ImageView) entry.getChildren().get(school.getEntry().indexOf(student));
            studentNode.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard db =  studentNode.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(((ImageView) studentNode).getImage());
                    db.setContent(content);
                    studentSelected = entryMap.get((ImageView) studentNode);
                    if(GuiManager.getMainScene().getCardSelected()!=null){
                        GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).disableStudents(true);
                        GuiManager.getMainScene().disableDoneButton(true);
                    }
                    mouseEvent.consume();
                }
            });

            studentNode.setOnDragDone(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    if (event.getTransferMode() == TransferMode.MOVE) {
                        notifyObserver(obs -> obs.choosePlaceAndStudentForMove(placeSelected,studentSelected));
                    }
                    event.consume();
                }
            });
        }
    }

    /**
     * Enables drag over hall of students
     */
    private void addDragOver(){
        hall.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != hall &&
                        event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        hall.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != hall &&
                        event.getDragboard().hasString()) {
                    hall.setVisible(false);
                }

                event.consume();
            }
        });

        hall.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                hall.setVisible(true);

                event.consume();
            }
        });

        hall.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    setPlaceSelected("SCHOOL");
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });
    }

    /**
     * Adds mouse event to students of the entry
     */
    public void addMouseEventToStudentsOfEntry(){
        for(Student student : school.getEntry()) {
            ImageView studentNode = (ImageView) entry.getChildren().get(school.getEntry().indexOf(student));
            studentNode.addEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClicked);
        }
    }

    /**
     * Adds mouse event to the last students of each table
     */
    public void addMouseEventToLastStudentsOfHall() {
        if (!school.getGTable().isEmpty()) {
            green_table.getChildren().get(school.getGTable().size() - 1).addEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClickedHall);
            green_table.getChildren().get(school.getGTable().size() - 1).setCursor(Cursor.HAND);
        }else if (!school.getRTable().isEmpty()) {
            red_table.getChildren().get(school.getRTable().size() - 1).addEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClickedHall);
            red_table.getChildren().get(school.getRTable().size() - 1).setCursor(Cursor.HAND);
        }else if (!school.getYTable().isEmpty()) {
            yellow_table.getChildren().get(school.getYTable().size() - 1).addEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClickedHall);
            yellow_table.getChildren().get(school.getYTable().size() - 1).setCursor(Cursor.HAND);
        }else if (!school.getPTable().isEmpty()) {
            pink_table.getChildren().get(school.getPTable().size() - 1).addEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClickedHall);
            pink_table.getChildren().get(school.getPTable().size() - 1).setCursor(Cursor.HAND);
        }else if (!school.getBTable().isEmpty()) {
            blue_table.getChildren().get(school.getBTable().size() - 1).addEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClickedHall);
            blue_table.getChildren().get(school.getBTable().size() - 1).setCursor(Cursor.HAND);
        }else{
            GuiManager.getMainScene().disableDoneButton(true);
            removeMouseEventToStudentsOfEntry();
            removeMouseEventToLastStudentsOfHall();
            disableEntry(true);
            notifyObserver(obs -> obs.chooseCharacterCard(GuiManager.getMainScene().getCardSelected().getCardEffect().toString(), true));
        }
    }

    private int acrobatIndex=0;
    private int acrobatIndexHall=0;

    public int getAcrobatIndex(){
        return acrobatIndex;
    }

    public int getAcrobatIndexHall(){
        return acrobatIndex;
    }


    /**
     * Handles the click on the students of the entry.
     * @param t the mouse click event.
     */
    private <T extends Event> void studentClicked(T t) {
        studentSelectedForSwitch[acrobatIndex] = entryMap.get(t.getSource());
        disableEntry(true);
        if(GuiManager.getMainScene().getCardSelected().getCardEffect().equals(CardEffect.ACROBAT)) {
            GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).disableStudents(false);
            for (ImageView student : GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).getStudentMap().keySet()) {
                int id = GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).getStudentMap().get(student);
                for (int i = 0; i < 3; i++) {
                    if (GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).getStudentSelectedForSwitch()[i] == id)
                        student.setDisable(true);
                }
            }
            acrobatIndex++;
            if (acrobatIndex == 3) {
                GuiManager.getMainScene().disableDoneButton(true);
                removeMouseEventToStudentsOfEntry();
                GuiManager.getMainScene().getCharacterCardControllerMap().get(GuiManager.getMainScene().getCardSelected().getCardEffect()).disableStudents(true);
                notifyObserver(obs -> obs.chooseCharacterCard(GuiManager.getMainScene().getCardSelected().getCardEffect().toString(), true));
            }
        } else if(GuiManager.getMainScene().getCardSelected().getCardEffect().equals(CardEffect.BARD)) {
            disableHall(false);
            for(ImageView student : hallMap.keySet()){
                int id = hallMap.get(student);
                for(int i=0; i<2;i++){
                    if (studentSelectedForSwitchHall[i]==id)
                        student.setDisable(true);
                }
            }
            acrobatIndex++;
            if (acrobatIndex == 2) {
                acrobatIndex=0;
            }
        }

    }

    /**
     * Removes mouse event of the students of the entry
     */
    public void removeMouseEventToStudentsOfEntry(){
        acrobatIndex=0;
        for(Student student : school.getEntry()) {
            ImageView studentNode = (ImageView) entry.getChildren().get(school.getEntry().indexOf(student));
            studentNode.removeEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClicked);
        }
    }

    /**
     * Removes mouse event of the last student of each table
     */
    public void removeMouseEventToLastStudentsOfHall(){
        acrobatIndexHall=0;
        if (!school.getGTable().isEmpty()) {
            green_table.getChildren().get(school.getGTable().size() - 1).removeEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClickedHall);
            green_table.getChildren().get(school.getGTable().size() - 1).setCursor(Cursor.DEFAULT);
        }
        if (!school.getRTable().isEmpty()) {
            red_table.getChildren().get(school.getRTable().size() - 1).removeEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClickedHall);
            red_table.getChildren().get(school.getRTable().size() - 1).setCursor(Cursor.DEFAULT);
        }
        if (!school.getYTable().isEmpty()) {
            yellow_table.getChildren().get(school.getYTable().size() - 1).removeEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClickedHall);
            yellow_table.getChildren().get(school.getYTable().size() - 1).setCursor(Cursor.DEFAULT);
        }
        if (!school.getPTable().isEmpty()) {
            pink_table.getChildren().get(school.getPTable().size() - 1).removeEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClickedHall);
            pink_table.getChildren().get(school.getPTable().size() - 1).setCursor(Cursor.DEFAULT);
        }
        if (!school.getBTable().isEmpty()) {
            blue_table.getChildren().get(school.getBTable().size() - 1).removeEventHandler(MouseEvent.MOUSE_CLICKED, this::studentClickedHall);
            blue_table.getChildren().get(school.getBTable().size() - 1).setCursor(Cursor.DEFAULT);
        }
    }

    public int[] getStudentSelectedForSwitch() {
        return studentSelectedForSwitch;
    }

    public int[] getStudentSelectedForSwitchHall() {
        return studentSelectedForSwitchHall;
    }

    /**
     * Handles the click on the last student of each table.
     * @param t the mouse click event.
     */
    private <T extends Event> void studentClickedHall(T t) {
        studentSelectedForSwitchHall[acrobatIndexHall] = hallMap.get(t.getSource());
        disableHall(true);
        if(GuiManager.getMainScene().getCardSelected().getCardEffect().equals(CardEffect.BARD)) {
            disableEntry(false);
            for(ImageView student : entryMap.keySet()){
                int id = entryMap.get(student);
                for(int i=0; i<2;i++){
                    if (studentSelectedForSwitch[i]==id)
                        student.setDisable(true);
                }
            }
            acrobatIndexHall++;
            if (acrobatIndexHall == 2) {
                GuiManager.getMainScene().disableDoneButton(true);
                removeMouseEventToStudentsOfEntry();
                removeMouseEventToLastStudentsOfHall();
                disableEntry(true);
                notifyObserver(obs -> obs.chooseCharacterCard(GuiManager.getMainScene().getCardSelected().getCardEffect().toString(), true));
            }
        }

    }

    /**
     * Enables or disables last student of each table
     * @param disable boolean that enables or disables the last student of each table
     */
    public void disableHall(boolean disable){
        if(!school.getGTable().isEmpty())
            green_table.getChildren().get(school.getGTable().size()-1).setDisable(disable);
        if(!school.getRTable().isEmpty())
            red_table.getChildren().get(school.getGTable().size()-1).setDisable(disable);
        if(!school.getYTable().isEmpty())
            yellow_table.getChildren().get(school.getGTable().size()-1).setDisable(disable);
        if(!school.getPTable().isEmpty())
            pink_table.getChildren().get(school.getGTable().size()-1).setDisable(disable);
        if(!school.getBTable().isEmpty())
            blue_table.getChildren().get(school.getGTable().size()-1).setDisable(disable);
    }
}
