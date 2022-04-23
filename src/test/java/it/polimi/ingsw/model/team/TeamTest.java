package it.polimi.ingsw.model.team;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.school.TColor;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void initializeTeam(){
        Team team = new Team();
        team.intializeTeam(new Player(TColor.BLACK, PlayerNumber.PLAYER1), new Player(TColor.BLACK, PlayerNumber.PLAYER2));
        assertNotNull(team.getTeam());
        assertEquals(2, team.getTeam().size());

        int countPlayer = 0;
        for(Player player : team.getTeam()){
            countPlayer ++;
        }
        assertEquals(2, countPlayer);
        assertNotEquals(3, countPlayer);
        assertNotEquals(4, countPlayer);
    }

}
