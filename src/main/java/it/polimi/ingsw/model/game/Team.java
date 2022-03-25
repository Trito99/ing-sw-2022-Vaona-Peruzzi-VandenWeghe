package it.polimi.ingsw.model.game;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;

import java.util.ArrayList;

public class Team {

    private ArrayList<Player> team;

    public Team() {
        this.team = new ArrayList<>(2);
    }

    public ArrayList<Player> getTeam() {
        return this.team;
    }

    public void initializeTeam(GameMode gameMode, Player player1, Player player2){
        team.set(0, Player.setPlayerNumber(player1.getPlayerNumber()));
        team.set(1, Player.setPlayerNumber(player2.getPlayerNumber()));   //Si Può fare??
    }
}
