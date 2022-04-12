package it.polimi.ingsw.model.assistant;

import java.util.ArrayList;

public class DeckAssistant {

    private ArrayList<AssistantCard> cardsInHand;

    public DeckAssistant (ArrayList<AssistantCard> cardsInHand){
        this.cardsInHand = new ArrayList <>(10);
    }

    public ArrayList<AssistantCard> getCardsInHand() {
        return (ArrayList<AssistantCard>) cardsInHand.clone();
    }

}
