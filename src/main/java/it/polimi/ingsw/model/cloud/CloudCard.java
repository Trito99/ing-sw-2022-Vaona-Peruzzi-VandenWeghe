package it.polimi.ingsw.model.cloud;

import it.polimi.ingsw.model.student.Student;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the Cloud Card
 */
public class CloudCard implements Serializable {
    private final int idCloud;
    private ArrayList<Student> studentOnCloud;
    private final int numberOfSpaces;

    /**
     * Default constructor
     * @param idCloud id of a certain Cloud card
     * @param numberOfSpaces number of students that can be put on a certain card
     */
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
