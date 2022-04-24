package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.school.TColor;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void initializeTeam(){
        Team team = new Team();
        team.intializeTeam(new Player(TColor.BLACK, PlayerNumber.PLAYER1), new Player(TColor.BLACK, PlayerNumber.PLAYER2), TColor.BLACK);
        assertNotNull(team.getTeam().get(0));
        assertNotNull(team.getTeam().get(1));
        assertEquals(2, team.getTeam().size());
        assertEquals(TColor.BLACK, team.getTeam().get(0).getTColour());
        assertEquals(TColor.BLACK, team.getTeamColor());

        int countPlayer = 0;
        for(Player player : team.getTeam()){
            countPlayer ++;
        }
        assertEquals(2, countPlayer);
        assertNotEquals(3, countPlayer);
        assertNotEquals(4, countPlayer);
    }

}
