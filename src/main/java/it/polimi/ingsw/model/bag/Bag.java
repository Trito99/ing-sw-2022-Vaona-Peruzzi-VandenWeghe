package it.polimi.ingsw.model.bag;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.student.SColour;

import java.util.ArrayList;
import java.util.Collections;


public class Bag {

    private ArrayList<Student> bag = new ArrayList<>();

    public Bag generateBag(){
        SColour green = SColour.GREEN;
        SColour red = SColour.RED;
        SColour yellow = SColour.YELLOW;
        SColour pink = SColour.PINK;
        SColour blue = SColour.BLUE;
        Bag b = new Bag();

        for(int s=1;s<131;s++){
            if(s<27)
                b.getBag().add(new Student(s,green));
            else if(s>26 && s<53)
                b.getBag().add(new Student(s,red));
            else if(s>52 && s<79)
                b.getBag().add(new Student(s,yellow));
            else if(s>78 && s<105)
                b.getBag().add(new Student(s,pink));
            else
                b.getBag().add(new Student(s,blue));
        }
        Collections.shuffle(b.getBag());
        return b;
    }

    public ArrayList<Student> getBag() {
        return bag;
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
