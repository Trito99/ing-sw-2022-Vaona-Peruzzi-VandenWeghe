package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.student.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableTest {

    @Test
    public void generateBagInit() {
        Table table= new Table();

        int countgreen=0;
        int countyellow=0;
        int countred=0;
        int countblue=0;
        int countpink=0;
        table.generateBagInit();
        assertNotNull(table.getBag());
        assertNotNull(table.getBag().get(9));

        for(Student student : table.getBag()){
            switch (student.getsColour()){
                case GREEN:
                    countgreen++;
                case YELLOW:
                    countyellow++;
                case RED:
                    countred++;
                case BLUE:
                    countblue++;
                case PINK:
                    countpink++;
            }
        }
        assertEquals(2,countgreen);
        assertEquals(2,countyellow);
        assertEquals(2,countred);
        assertEquals(2,countblue);
        assertEquals(2,countpink);
    }

}