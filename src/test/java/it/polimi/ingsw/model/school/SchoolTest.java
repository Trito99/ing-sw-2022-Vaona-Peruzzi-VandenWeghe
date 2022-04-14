package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SchoolTest {

    Table table;
    @Test
    @BeforeEach
    public void init(){
        table= new Table();
        table.addFinalStudents();
    }
    @ParameterizedTest
    @EnumSource(GameMode.class)

    void numberOfProf(GameMode gameMode) {
        School school= new School();
        Player player= new Player(TColor.BLACK, PlayerNumber.PLAYER1);
        player.generateSchool(table, gameMode);
        for (int i = 0; i < 5; i++) {
            player.getPersonalSchool().getProfOfPlayer().get(i).setInHall(true);
        }
        assertEquals(5, school.numberOfProf());

    }
}