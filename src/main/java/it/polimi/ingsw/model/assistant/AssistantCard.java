package it.polimi.ingsw.model.assistant;

import java.io.Serializable;

/**
 * Represents the Assistant Card
 */
public class AssistantCard implements Serializable {
    private final String assistantName;
    private int stepMotherEarth;
    private int turnValue;


    /**
     * Default constructor
     * @param assistantName string which identifies a certain card of the Assistant Deck
     * @param stepMotherEarth number of steps that Mother Earth can do when a certain assistant card is played
     * @param turnValue number to establish the order of each turn of the game when a certain assistant card is played
     */
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

}
