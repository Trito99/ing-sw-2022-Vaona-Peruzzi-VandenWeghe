package it.polimi.ingsw.model.school;

import java.io.Serializable;

/**
 * Represents the pawn Tower
 */

public class Tower implements Serializable {
    private final TColor tColor;

    /**
     * default constructor
     * @param tColor tower's color
     */
    public Tower(TColor tColor) {
        this.tColor = tColor;
    }

    public TColor getTColour(){
        return tColor;
    }

}
