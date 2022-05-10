package it.polimi.ingsw.model.school;

import java.io.Serializable;

public class Tower implements Serializable {

    private int idTower;
    private TColor tColor;

    public Tower(int idTower, TColor tColor) {
        this.idTower = idTower;
        this.tColor = tColor;
    }

    public int getIdTower() {
        return idTower;
    }

    public TColor getTColour(){
        return tColor;
    }

}
