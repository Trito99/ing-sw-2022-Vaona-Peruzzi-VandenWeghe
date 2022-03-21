package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Team {

    private ArrayList<Player> team;

    public Team() {
        this.team = new ArrayList<>(2);
    }

    public ArrayList<Player> getTeam() {
        return this.team;
    }
}
