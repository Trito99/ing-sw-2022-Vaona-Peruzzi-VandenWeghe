package it.polimi.ingsw.model.cloud;


import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class CloudCard {
    private int idCloud;
    private ArrayList<Student> studentOnCloud;
    private int numberOfSpaces;


    public CloudCard(int idCloud, int numberOfSpaces){
        this.idCloud = idCloud;
        this.studentOnCloud = new ArrayList<Student>(3);
        this.numberOfSpaces = numberOfSpaces;
    }

    public ArrayList<Student> getStudentOnCloud() {
        return studentOnCloud;
    }


    public int getNumberOfSpaces(){
        return numberOfSpaces;
    }

    public int getIdCloud(){
        return idCloud;
    }

    public void moveStudentToIsland(CloudCard cloudCard, IslandCard islandCard, int id){ //Specifico Studente va spostato (sceglie player)
        Student student = new Student(131,null);
        for(int i = 0; i < cloudCard.getStudentOnCloud().size(); i++) {
            if(id==cloudCard.getStudentOnCloud().get(i).getIdStudent())
                student = cloudCard.getStudentOnCloud().get(i);
        }
        islandCard.getStudentOnIsland().add(cloudCard.getStudentOnCloud().get(cloudCard.getStudentOnCloud().indexOf(student)));
        cloudCard.getStudentOnCloud().remove(cloudCard.getStudentOnCloud().get(cloudCard.getStudentOnCloud().indexOf(student)));
    }

}
