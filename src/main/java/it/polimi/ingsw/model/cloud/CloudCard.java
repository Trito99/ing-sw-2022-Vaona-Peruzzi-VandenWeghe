package it.polimi.ingsw.model.cloud;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class CloudCard{
    private ArrayList<Student> studentOnCloud = new ArrayList<>(3);
    private int numberOfSpaces;
    private int studentCount;

    public CloudCard(ArrayList<Student> studentOnCloud, int numberOfSpaces, int studentCount){
        this.studentOnCloud = new ArrayList<Student>(3);
        this.studentCount = 0;

        if (Game.gameMode equals (GameMode.THREEPLAYERS)){
            this.numberOfSpaces = 4;
        }
        else { this.numberOfSpaces = 3;
        }

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
