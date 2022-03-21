package it.polimi.ingsw.model;

import java.util.ArrayList;

public class CloudCard{
    private ArrayList<Student> studentOnCloud = new ArrayList<>(3);
    private int numberOfSpaces;
    private int studentCount;

    public CloudCard(ArrayList<Student> studentOnCloud, int numberOfSpaces, int studentCount){
        this.studentOnCloud=new ArrayList<Student>(3);
        this.numberOfSpaces=numberOfSpaces;
        this.studentCount=studentCount;
    }

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
