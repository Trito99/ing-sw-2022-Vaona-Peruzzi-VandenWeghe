package it.polimi.ingsw.message;

import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WinMessage extends ServerMessage{

    public WinMessage() {
        super(MessageType.WIN);
    }

}
