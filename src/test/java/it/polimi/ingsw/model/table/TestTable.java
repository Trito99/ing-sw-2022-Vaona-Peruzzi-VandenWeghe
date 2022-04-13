package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.student.Student;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


public class TestTable {

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
        assertEquals(10, table.getBag().size());

        for(Student s : table.getBag()){
            switch (s.getsColour()){
                case GREEN:
                    countgreen++;
                    break;
                case YELLOW:
                    countyellow++;
                    break;
                case RED:
                    countred++;
                    break;
                case BLUE:
                    countblue++;
                    break;
                case PINK:
                    countpink++;
                    break;
            }
        }
        assertEquals(2, countgreen);
        assertEquals(2, countyellow);
        assertEquals(2, countred);
        assertEquals(2, countblue);
        assertEquals(2, countpink);

    }

    @Test
    public void addFinalStudents(){
        Table table= new Table();
        int countgreen=0;
        int countyellow=0;
        int countred=0;
        int countblue=0;
        int countpink=0;
        table.addFinalStudents();
        assertNotNull(table.getBag());
        assertNotNull(table.getBag().get(119));

        for(Student s : table.getBag()){
            switch (s.getsColour()){
                case GREEN:
                    countgreen++;
                    break;
                case YELLOW:
                    countyellow++;
                    break;
                case RED:
                    countred++;
                    break;
                case BLUE:
                    countblue++;
                    break;
                case PINK:
                    countpink++;
                    break;
            }
        }
        assertEquals(24, countgreen);
        assertEquals(24, countyellow);
        assertEquals(24, countred);
        assertEquals(24, countblue);
        assertEquals(24, countpink);

    }

    @Test
    public void extraxtStudentsInit(){
        Table table = new Table();
        int islandEx = 0;
        table.generateBagInit();
        table.generateIslandCards();
        table.generateMotherEarth();
        table.extractStudentsInit();
        for(IslandCard is:table.getListOfIsland()){
            if(is.getMotherEarthOnIsland()==true)
                islandEx=(is.getIdIsland()+6)%table.getListOfIsland().size();
        }
        for(IslandCard is:table.getListOfIsland()){
            if(is.getMotherEarthOnIsland()==false&&(is.getIdIsland()!=islandEx))
                assertEquals(1,is.getStudentOnIsland().size());
            else
                assertEquals(0,is.getStudentOnIsland().size());
        }
    }
}
