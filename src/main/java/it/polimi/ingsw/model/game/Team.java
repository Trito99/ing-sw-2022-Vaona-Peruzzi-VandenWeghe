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

    public void initializeTeam(GameMode gameMode, Player player) {
        if(gameMode.equals(GameMode.COOP))
        /** team.add(Player.playerNumber.PLAYER1);
        team.add(Player.playerNumber.PLAYER3);  */          //non va bene
    }

}