package it.polimi.ingsw.model.school;

import java.util.ArrayList;

public class Tower {

    private int idTower;
    private TColour tColour;

    public Tower(int idTower, TColour tColour) {
        this.idTower = idTower;
        this.tColour = tColour;
    }

    public int getIdTower() {
        return idTower;
    }

    public TColour getTColour(){
        return tColour;
    }

}
