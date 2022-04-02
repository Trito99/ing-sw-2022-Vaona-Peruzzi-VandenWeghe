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
            else if(s<53)
                b.getBag().add(new Student(s,red));
            else if(s<79)
                b.getBag().add(new Student(s,yellow));
            else if(s<105)
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

    public void extractStudent(Bag bag, CloudCard cloudCard){   //estrae dal sacchetto 3/4 studenti
        /** chiara: penso vada passato anche l'id cloud */
        if(cloudCard.getNumberOfSpaces()==4){
            for (int i=0; i<4; i++) {
                cloudCard.getStudentOnCloud().add(bag.getBag().get(i));
                bag.getBag().remove(bag.getBag().get(i));
            }
        }
        else {
            for (int i=0; i<3; i++) {
                cloudCard.getStudentOnCloud().add(bag.getBag().get(i));
                bag.getBag().remove(bag.getBag().get(i));
            }
        }
    }

    public boolean checkIsEmpty(){
        return bag.toArray().length == 0;
    }

}
