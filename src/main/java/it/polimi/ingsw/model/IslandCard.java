package it.polimi.ingsw.model;

import java.util.ArrayList;

public class IslandCard {

    private int idIsland;
    private ArrayList<Student> studentOnIsland;
    private boolean towerIsOnIsland;
    private TColour towerOnIsland;

    public IslandCard() {
    }

    public int getIdIsland() {
        return this.idIsland;
    }

    public ArrayList<Student> getStudentOnIsland() {
        return this.studentOnIsland;
    }

    public boolean isTowerIsOnIsland() {
        return this.towerIsOnIsland;
    }

    public TColour getTowerOnIsland() {
        return this.towerOnIsland;
    }

    public void setTowerIsOnIsland(boolean towerIsOnIsland) {
        this.towerIsOnIsland = towerIsOnIsland;
    }

    public PlayerNumber calculateInfluence(){

    }

    public TColour buildTowerOnIsland(){

    }
}
