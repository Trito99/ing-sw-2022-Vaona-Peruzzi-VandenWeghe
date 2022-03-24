package it.polimi.ingsw.model.assistant;

import java.util.ArrayList;

public class DeckAssistant {

    private ArrayList<AssistantCard> assistantCard;

    public DeckAssistant (ArrayList<AssistantCard> assistantCard){
        this.assistantCard = new ArrayList <>(10);
    }

    public ArrayList<AssistantCard> getAssistantCard() {
        return this.assistantCard;
    }

  /**  public ArrayList<AssistantCard> updateDeck() {
        return this.assistantCard;
    }  //ritorna il mazzo di carte senza la carta selezionata     */

    public boolean checkIsEmpty() {
        return assistantCard.toArray().length != 0;
    }

}
