package it.polimi.ingsw.model.bag;

import it.polimi.ingsw.model.student.SColour;
import it.polimi.ingsw.model.student.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

public class BagTest {
    private Bag testBag;

    @Before
    public void setUp() {
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
        testBag=b;
    }

    @Test
    public void Testme(){
        System.out.println("test");
    }

}
