package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.student.SColor;

public class Prof {

    private final SColor sColor;
    private boolean isInHall;

    public Prof(SColor sColor){
        this.sColor = sColor;
    }

    public SColor getSColour() {
        return sColor;
    }

    public boolean getInHall(){
        return isInHall;
    }

    public void setInHall(boolean inHall) {
        isInHall = inHall;
    }
}
