package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.AssistantCard;

public class ChooseMotherEarthSteps extends  ServerMessage {
    private int maxSteps;

    public ChooseMotherEarthSteps(int maxSteps){
        super(MessageType.CHOOSE_MOTHER_EARTH_STEPS);
        this.maxSteps = maxSteps;
    }

    public int getMaxSteps(){
        return maxSteps;
    }
}
