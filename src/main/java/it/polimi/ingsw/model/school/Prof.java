package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.student.SColour;

public class Prof {

    private final SColour sColour;
    private boolean isInHall;

    public Prof(SColour sColour){
        this.sColour=sColour;
    }

    public SColour getSColour() {
        return sColour;
    }

    public void setInHall(boolean inHall) {
        isInHall = inHall;
    }
}
