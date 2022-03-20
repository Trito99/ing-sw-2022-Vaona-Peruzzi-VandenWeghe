package it.polimi.ingsw.model;

import java.util.ArrayList;

public class CloudCard {
    private ArrayList<Student> studentOnCloud = new ArrayList<>(3);
    private int numberOfSpaces;
    private int studentCount;

    public ArrayList<Student> getStudentOnCloud() {
        return this.studentOnCloud;
    }

    public int getNumberOfSpaces() {
        return this.numberOfSpaces;
    }

    public int getStudentCount() {
        return this.studentCount;
    }
}
