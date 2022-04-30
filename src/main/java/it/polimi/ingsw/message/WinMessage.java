package it.polimi.ingsw.message;

import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WinMessage extends ServerMessage{
    private int numberOfTower;
    private int numberOfIsland;
    private ArrayList<Student> lastStudents; //quando size=0, finisce gioco

    public WinMessage(int numberOfTower, int numberOfIsland, ArrayList<Student> lastStudents) {
        super(MessageType.WIN);
        this.numberOfTower = numberOfTower;
        this.numberOfIsland = numberOfIsland;
        this.lastStudents = new ArrayList<Student>();
    }

    public int getNumberOfTower() {
        return numberOfTower;
    }

    public int getNumberOfIsland(){
        return numberOfIsland;
    }

    public int getLastStudents(){
        return lastStudents.size();
    }

    /** inserire anche gli altri casi di possibile vittoria */
}
