package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * This Scene Controller is used to make the player choose between 2 or more cloud cards
 */
public class CloudCards extends ObservableView implements GenericScene {
    private ArrayList<CloudCard> cloudCards;
    private CloudCard cloudSelected;
    @FXML
    private Pane StudentsPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView cloud1_2Players;
    @FXML
    private ImageView cloud2_2Players;
    @FXML
    private ImageView stud1_c1_2Players;
    @FXML
    private ImageView stud1_c2_2Players;
    @FXML
    private ImageView stud2_c1_2Players;
    @FXML
    private ImageView stud2_c2_2Players;
    @FXML
    private ImageView stud3_c1_2Players;
    @FXML
    private ImageView stud3_c2_2Players;

    public void setCloudCard(ArrayList<CloudCard> cloudCards){
        this.cloudCards = cloudCards;
    }

    public void initialize(){
        hide();
        updateStudents(cloudCards);
        addEventHandler(cloudCards);
    }

    private void addEventHandler(ArrayList<CloudCard> cloudCards) {
        for(int i=cloudCards.size();i>0;i--){
            anchorPane.getChildren().get(anchorPane.getChildren().size()-i).addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCloud);
        }
    }

    private void clickCloud(Event event){
        for(int i=cloudCards.size();i>0;i--){
            if(event.getSource().equals(anchorPane.getChildren().get(anchorPane.getChildren().size()-i))){
                cloudSelected = cloudCards.get(cloudCards.size()-i);
                notifyObserver(obs -> obs.chooseCloudCard(cloudSelected.getIdCloud(), ""));
                disabilitateCloud(true);
            }
        }
    }

    public void disabilitateCloud(boolean disabilitate){
        for(int i=cloudCards.size();i>0;i--){
            anchorPane.getChildren().get(anchorPane.getChildren().size()-i).setDisable(disabilitate);
        }
    }

    public void updateStudents(ArrayList<CloudCard> cloudCards){
        this.cloudCards = cloudCards;
        ArrayList<Student> allStudents = new ArrayList<>();
        for(CloudCard c : cloudCards) {
            if(c.getStudentOnCloud().size()>0) {
                for (Student s : c.getStudentOnCloud()) {
                    allStudents.add(s);
                }
            }else{
                for(int i = 0;i<c.getNumberOfSpaces();i++){
                    allStudents.add(new Student(131, SColor.GREEN));
                }
            }
        }
        for(Student s : allStudents){
            if(s.getIdStudent()!=131) {
                StudentsPane.getChildren().get(allStudents.indexOf(s)).setVisible(true);
                StudentsPane.getChildren().get(allStudents.indexOf(s)).setDisable(false);
                switch (s.getsColour()) {
                    case GREEN:
                        ((ImageView) (StudentsPane.getChildren().get(allStudents.indexOf(s)))).setImage(new Image("/images/students/Gstudent.png"));
                        break;
                    case RED:
                        ((ImageView) (StudentsPane.getChildren().get(allStudents.indexOf(s)))).setImage(new Image("/images/students/Rstudent.png"));
                        break;
                    case YELLOW:
                        ((ImageView) (StudentsPane.getChildren().get(allStudents.indexOf(s)))).setImage(new Image("/images/students/Ystudent.png"));
                        break;
                    case PINK:
                        ((ImageView) (StudentsPane.getChildren().get(allStudents.indexOf(s)))).setImage(new Image("/images/students/Pstudent.png"));
                        break;
                    case BLUE:
                        ((ImageView) (StudentsPane.getChildren().get(allStudents.indexOf(s)))).setImage(new Image("/images/students/Bstudent.png"));
                        break;
                }
            }else{
                StudentsPane.getChildren().get(allStudents.indexOf(s)).setVisible(false);
                StudentsPane.getChildren().get(allStudents.indexOf(s)).setDisable(true);
            }
        }
    }

    private void hide() {
        for (Node node : StudentsPane.getChildren()) {
            node.setVisible(false);
            node.setDisable(true);
        }
        for(Node node : anchorPane.getChildren()){
            node.setDisable(true);
        }
    }
}
