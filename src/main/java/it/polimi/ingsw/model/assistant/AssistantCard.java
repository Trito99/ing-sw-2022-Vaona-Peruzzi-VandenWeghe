package it.polimi.ingsw.model.assistant;

import java.io.Serializable;

public class AssistantCard implements Serializable {

    private String assistantName;
    private int stepMotherEarth;
    private int turnValue;

    //Constructs an AssistantCard.

    public AssistantCard(String assistantName,int stepMotherEarth, int turnValue){
        this.assistantName = assistantName;
        this.stepMotherEarth = stepMotherEarth;
        this.turnValue = turnValue;
    }

    public String getAssistantName() {
        return assistantName;
    }

    public int getStepMotherEarth() {
        return stepMotherEarth;
    }

    public int getTurnValue() {
        return turnValue;
    }

    public void setTurnValue(int turnValue) {
        this.turnValue = turnValue;
    }
}
