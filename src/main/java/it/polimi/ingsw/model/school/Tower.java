package it.polimi.ingsw.model.school;

import java.io.Serializable;

public class Tower implements Serializable {

    private final TColor tColor;

    public Tower(TColor tColor) {
        this.tColor = tColor;
    }

    public TColor getTColour(){
        return tColor;
    }

}
