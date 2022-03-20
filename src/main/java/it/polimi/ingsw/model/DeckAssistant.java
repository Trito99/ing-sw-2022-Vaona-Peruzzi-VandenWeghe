package it.polimi.ingsw.model;

import java.util.ArrayList;

public class DeckAssistant {

    private ArrayList<AssistantCard> assistentCard = new ArrayList<>(10);

    public ArrayList<AssistantCard> getAssistentCard() {
        return this.assistentCard;
    }

    public ArrayList<AssistantCard> updateDeck() {
        return this.assistentCard;
    }

    public boolean checkIsEmpty() {
        return this.assistentCard == null;
    }

}
