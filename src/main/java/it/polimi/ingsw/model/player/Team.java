package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.TColor;

import java.util.ArrayList;

public class Team {
    private ArrayList<Player> team;
    private TColor teamColor;

    public Team(){
        this.team = new ArrayList<>(2);
    }

    public ArrayList<Player> getTeam(){
        return (ArrayList<Player>) team.clone();
    }

    public TColor getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(TColor teamColor) {
        this.teamColor = teamColor;
    }

    public void intializeTeam(Player player1, Player player2, TColor tColor){
        team.add(0,player1);
        team.add(1,player2);
        setTeamColor(tColor);
    }
}