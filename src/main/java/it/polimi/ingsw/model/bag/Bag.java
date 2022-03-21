package it.polimi.ingsw.model.bag;

import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;

public class Bag {

    private ArrayList<Student> bag = new ArrayList<>(130);

    public ArrayList<Student> shuffleBag(){
        //da fare
        return this.bag;
    }

    public ArrayList<Student> extractStudent(int[] extracted){
        public final int length = 4;
        public int capacity = 130;
        Game gameMode = Game.getGameMode();
        assert gameMode != null;
        if(gameMode.equals(GameMode.THREEPLAYERS)){
            for (int i = 0; i <= extracted.length; i++) {
                    bag.toArray(Student[] bag(capacity));
                    new capacity = capacity - 1;
            }
        }
        else {
            for (int i = 0; i < extracted.length; i++) {
                bag.toArray(Student[] bag(capacity));
                new capacity = capacity - 1;
        }
        }
        return this.bag;
    }

    public boolean CheckIsEmpty(){
        return bag.toArray().length != 0;
    }

}
