package it.polimi.ingsw.model.bag;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Bag {

    private ArrayList<Student> bag = new ArrayList<>(130);

    //Costruttore??

    public void shuffleBag(ArrayList<Student> bag){
        Collections.shuffle(bag);
    }

    public ArrayList<Student> extractStudent(ArrayList<Student> stud, CloudCard cloudCard, GameMode gameMode){   //estrae dal sacchetto 3/4 studenti

        //GameMode gameMode = Game.getGameMode();                          // Da rivedere
        //School entry = School.getEntry();    (FEDERICO: Li ho tolti e ho inserito school e gamemode come parametri della funzione)
        int x = bag.size();

        if(gameMode.equals(GameMode.THREEPLAYERS)){
            for (int i = bag.size(); i >= x -4; i--) {
                cloudCard.getStudentOnCloud().add(stud.get(i));
                bag.remove(stud);
            }
        }
        else {
            for (int i = bag.size(); i >= x -3; i--) {
                cloudCard.getStudentOnCloud().add(stud.get(i));
                bag.remove(stud);
            }
        }
        return bag;
    }

    public boolean checkIsEmpty(){
        return bag.toArray().length == 0;
    }

}
