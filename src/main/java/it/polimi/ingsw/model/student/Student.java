package it.polimi.ingsw.model.student;

import java.io.Serializable;

public class Student implements Serializable {

    private int idStudent;
    private SColor sColor;

    public Student(int idStudent, SColor sColor) {
        this.idStudent = idStudent;
        this.sColor = sColor;
    }


    public int getIdStudent() {
        return idStudent;
    }

    public SColor getsColour() {
        return sColor;
    }
}
