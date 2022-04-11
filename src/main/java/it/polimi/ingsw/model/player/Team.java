package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Player;
import java.util.ArrayList;

public class Team {
    private ArrayList<Player> team;

    public Team(){
        this.team = new ArrayList<>(2);
    }

    public ArrayList<Player> getTeam(){
        return new ArrayList<Player>(team);
    }

    public void intializeTeam(Player player1, Player player2){
        team.add(0,player1);
        team.add(1,player2);
    }
}