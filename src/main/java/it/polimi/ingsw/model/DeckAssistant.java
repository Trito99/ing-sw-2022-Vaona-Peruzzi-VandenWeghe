package it.polimi.ingsw.model;

import java.util.ArrayList;

public class DeckAssistant {

    private ArrayList<AssistantCard> assistantCard;

    public DeckAssistant (ArrayList<AssistantCard> assistantCard){
        this.assistantCard = new ArrayList <>(10);
    }

    public ArrayList<AssistantCard> getAssistantCard() {
        return this.assistantCard;
    }

    public ArrayList<AssistantCard> updateDeck() {
        return this.assistantCard;
    }

    public boolean checkIsEmpty() {
        return this.assistantCard == null;
    }

}
