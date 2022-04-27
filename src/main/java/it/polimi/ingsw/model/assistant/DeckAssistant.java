package it.polimi.ingsw.model.assistant;

import java.util.ArrayList;

public class DeckAssistant {

    private ArrayList<AssistantCard> cardsInHand;
    private AssistantDeckName assistantDeckName;

    public DeckAssistant(AssistantDeckName assistantDeckName){
        this.assistantDeckName = assistantDeckName;
        this.cardsInHand = new ArrayList<>();
    }

    public ArrayList<AssistantCard> getCardsInHand() {
        return cardsInHand;
    }

    public void generateAssistantDeck(){
        cardsInHand.add(new AssistantCard("lion", 1, 1));
        cardsInHand.add(new AssistantCard("ostrich", 1, 2));
        cardsInHand.add(new AssistantCard("cat", 2, 3));
        cardsInHand.add(new AssistantCard("falcon", 2, 4));
        cardsInHand.add(new AssistantCard("fox", 3, 5));
        cardsInHand.add(new AssistantCard("lizard", 3, 6));
        cardsInHand.add(new AssistantCard("octopus", 4, 7));
        cardsInHand.add(new AssistantCard("dog", 4, 8));
        cardsInHand.add(new AssistantCard("elephant", 5, 9));
        cardsInHand.add(new AssistantCard("turtle", 5, 10));
    }

    public AssistantDeckName getDeckName(){
        return assistantDeckName;
    }

    public void setName(AssistantDeckName assistantDeckName){
        this.assistantDeckName = assistantDeckName;
    }

}
