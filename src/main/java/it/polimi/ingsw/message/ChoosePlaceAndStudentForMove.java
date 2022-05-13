package it.polimi.ingsw.message;


import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class ChoosePlaceAndStudentForMove extends ServerMessage {

    private ArrayList<Student> entry;

    public ChoosePlaceAndStudentForMove(ArrayList<Student> entry) {
        super(MessageType.CHOOSE_PLACE_AND_STUDENT_FOR_MOVE);
        this.entry = entry;
    }

    public ArrayList<Student> getEntry() {
        return entry;
    }
}
