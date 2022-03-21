package it.polimi.ingsw.model.bag;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class Bag {

    private ArrayList<Student> bag = new ArrayList<>(130);

    public ArrayList<Student> shuffleBag(){
        //da fare
        return this.bag;
    }

    public ArrayList<Student> extractStudent(ArrayList<Student> stud){

        GameMode gameMode = Game.getGameMode();
        School entry = School.getEntry();

        if(gameMode.equals(GameMode.THREEPLAYERS)){                           // DA RIVEDERE
            for (int i = bag.size(); i <= bag.size()-4; i--) {
                entry.addStudent(int id, SColour sColour);
                bag[i].remove(new Student(int id, SColour sColour));
            }
        }
        else {
            for (int i = bag.size(); i <= bag.size()-3; i--) {
                entry.addStudent(int id, SColour sColour);
                bag[i].remove(new Student(int id, SColour sColour));
        }
        }
        return bag;
    }

    public boolean checkIsEmpty(){
        return bag.toArray().length != 0;
    }

}
