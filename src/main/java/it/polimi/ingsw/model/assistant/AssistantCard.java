package it.polimi.ingsw.model.assistant;

public class AssistantCard {

    private String assistantName;
    private int stepMotherEarth;
    private int turnValue;

    //Constructs an AssistantCard.

    public AssistantCard(String assistantName,int stepMotherEarth, int turnValue){
        this.assistantName=assistantName;
        this.stepMotherEarth=stepMotherEarth;
        this.turnValue=turnValue;
    }

    public String getAssistantName() {
        return this.assistantName;
    }

    public int getStepMotherEarth() {
        return this.stepMotherEarth;
    }

    public int getTurnValue() {
        return this.turnValue;
    }
}
