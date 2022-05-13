package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.AssistantCard;

public class ChooseMotherEarthSteps extends  ServerMessage {
    private AssistantCard trash;

    public ChooseMotherEarthSteps(AssistantCard trash){
        super(MessageType.CHOOSE_MOTHER_EARTH_STEPS);
        this.trash = trash;
    }

    public AssistantCard getTrash(){
        return trash;
    }
}
