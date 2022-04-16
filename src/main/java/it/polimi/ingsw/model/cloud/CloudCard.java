package it.polimi.ingsw.model.cloud;


import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class CloudCard {
    private int idCloud;
    private ArrayList<Student> studentOnCloud;
    private int numberOfSpaces;


    public CloudCard(int idCloud, int numberOfSpaces){
        this.idCloud = idCloud;
        this.studentOnCloud = new ArrayList<Student>();
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


    /** Fede: Penso non funzioni (andrebbe messo come parametro l'id dell'isola, non l'isola)
     ---> Da spostare in gameController*/

}
