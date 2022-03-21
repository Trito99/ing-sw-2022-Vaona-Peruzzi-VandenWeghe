package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.student.SColour;

public class Prof {

    private SColour sColour;

    public Prof(SColour sColour){
        this.sColour=sColour;
    }

    public SColour getSColour() {
        return this.sColour;
    }
}
