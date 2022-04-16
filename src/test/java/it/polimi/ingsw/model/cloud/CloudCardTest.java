package it.polimi.ingsw.model.cloud;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CloudCardTest {

    private Table table;

    @BeforeEach
    @EnumSource(GameMode.class)
    public void setup(GameMode gameMode){
        table = new Table();
        table.generateIslandCards();
        table.addFinalStudents();
        table.generateCloudNumber(gameMode);
    }

 /**   @Test
    void moveStudentToIsland() {

        assertEquals(1, table.getListOfIsland().get(0).getStudentOnIsland());
        CloudCard cloud = table.getCloudNumber().get(0);
        Student student = table.getCloudNumber().get(0).getStudentOnCloud().get(0);
        int idStudent = table.getCloudNumber().get(0).getStudentOnCloud().get(0).getIdStudent();


        moveStudentToIsland();

    } */
}