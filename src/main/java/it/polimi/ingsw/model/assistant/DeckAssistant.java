package it.polimi.ingsw.model.assistant;

import java.util.ArrayList;

public class DeckAssistant {

    private ArrayList<AssistantCard> cardsInHand;

    public DeckAssistant (ArrayList<AssistantCard> cardsInHand){
        this.cardsInHand = new ArrayList <>(10);
    }

    public ArrayList<AssistantCard> getCardsInHand() {
        return this.cardsInHand;
    }

   /* public ArrayList<AssistantCard> updateDeck() {
        return this.cardsInHand;
    }  //ritorna il mazzo di carte senza la carta selezionata     */

    public boolean checkIsEmpty() {
        return cardsInHand.toArray().length != 0;
    }

}
