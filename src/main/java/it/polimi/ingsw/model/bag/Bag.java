package it.polimi.ingsw.model.bag;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.student.SColour;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;
import java.util.Collections;


public class Bag {

    private ArrayList<Student> bag = new ArrayList<>();

    public Bag generateBagInit(){
        SColour green = SColour.GREEN;
        SColour red = SColour.RED;
        SColour yellow = SColour.YELLOW;
        SColour pink = SColour.PINK;
        SColour blue = SColour.BLUE;
        Bag b = new Bag();

        for(int s=1;s<11;s++){
            if(s<3)
                b.getBag().add(new Student(s,green));
            else if(s<5)
                b.getBag().add(new Student(s,red));
            else if(s<7)
                b.getBag().add(new Student(s,yellow));
            else if(s<9)
                b.getBag().add(new Student(s,pink));
            else
                b.getBag().add(new Student(s,blue));
        }
        Collections.shuffle(b.getBag());
        return b;
    }

    public Bag addFinalStudents(Bag b){
        SColour green = SColour.GREEN;
        SColour red = SColour.RED;
        SColour yellow = SColour.YELLOW;
        SColour pink = SColour.PINK;
        SColour blue = SColour.BLUE;

        for(int s=11;s<131;s++){
            if(s<35)
                b.getBag().add(new Student(s,green));
            else if(s<59)
                b.getBag().add(new Student(s,red));
            else if(s<83)
                b.getBag().add(new Student(s,yellow));
            else if(s<107)
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

    public void extractStudent(Bag bag, Table table) {   //estrae dal sacchetto 3/4 studenti
        for(int c=0;c<table.getCloudNumber().size();c++){
            if (table.getCloudNumber().get(c).getNumberOfSpaces() == 4) {
                for (int i = 0; i < 4; i++) {
                    table.getCloudNumber().get(c).getStudentOnCloud().add(bag.getBag().get(i));
                    bag.getBag().remove(bag.getBag().get(i));
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    table.getCloudNumber().get(c).getStudentOnCloud().add(bag.getBag().get(i));
                    bag.getBag().remove(bag.getBag().get(i));
                }
            }
        }
    }

    public boolean checkIsEmpty(){
        return bag.toArray().length == 0;
    }

}
