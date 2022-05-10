package it.polimi.ingsw.model.cloud;


import it.polimi.ingsw.model.student.Student;

import java.io.Serializable;
import java.util.ArrayList;

public class CloudCard implements Serializable {
    private int idCloud;
    private ArrayList<Student> studentOnCloud;
    private int numberOfSpaces;


    public CloudCard(int idCloud, int numberOfSpaces){
        this.idCloud = idCloud;
        this.studentOnCloud = new ArrayList<>();
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

}
