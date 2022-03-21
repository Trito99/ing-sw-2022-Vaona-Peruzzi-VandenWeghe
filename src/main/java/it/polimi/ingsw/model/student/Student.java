package it.polimi.ingsw.model.student;

public class Student {

    private int idStudent;
    private SColour sColour;

    public Student(int idStudent, SColour sColour) {
        this.idStudent = idStudent;
        this.sColour = sColour;
    }

    public int getIdStudent() {
        return this.idStudent;
    }

    public SColour getsColour() {
        return this.sColour;
    }
}
