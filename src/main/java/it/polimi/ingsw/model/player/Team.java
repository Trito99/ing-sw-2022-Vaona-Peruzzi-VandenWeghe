package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.school.TColor;

import java.util.ArrayList;

public class Team {
    private ArrayList<Player> team = new ArrayList<>(2);
    private TColor teamColor;

    public Team(Player player1, Player player2, TColor tColor){
        team.add(player1);
        team.add(player2);
        setTeamColor(tColor);
    }

    public ArrayList<Player> getTeam(){
        return team;
    }

    public TColor getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(TColor teamColor) {
        this.teamColor = teamColor;
    }

    public Player getTeamLeader(){
        Player teamLeader = null;
        for(Player player : team){
            if (player.isTeamLeader())
                teamLeader = player;
        }
        return  teamLeader;
    }

}