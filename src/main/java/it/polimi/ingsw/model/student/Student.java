package it.polimi.ingsw.model.student;

import java.io.Serializable;

/**
 * Represents the pawn Student
 */
public class Student implements Serializable {
    private final int idStudent;
    private final SColor sColor;

    /**
     * default constructor
     * @param idStudent id which identifies each student
     * @param sColor student's color
     */
    public Student(int idStudent, SColor sColor) {
        this.idStudent = idStudent;
        this.sColor = sColor;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public SColor getSColor() {
        return sColor;
    }

}
