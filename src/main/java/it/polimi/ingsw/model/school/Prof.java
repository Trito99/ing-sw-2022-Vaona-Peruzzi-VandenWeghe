package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.student.SColor;

import java.io.Serializable;

/**
 * Represents the pawn Professor
 */

public class Prof implements Serializable {
    private final SColor sColor;
    private boolean isInHall = false;

    /**
     * Default constructor
     * @param sColor2 professor's color (relating to each student's color)
     */
    public Prof(SColor sColor2){
        sColor = sColor2;
    }

    public SColor getSColour() {
        return sColor;
    }

    public boolean getIsInHall(){
        return isInHall;
    }

    public void setInHall(boolean inHall) {
        this.isInHall = inHall;
    }
}
