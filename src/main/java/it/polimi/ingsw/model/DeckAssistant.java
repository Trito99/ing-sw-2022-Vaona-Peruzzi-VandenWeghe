package it.polimi.ingsw.model;

public class DeckAssistant {

    private List<AssistantCard> assistentCard = new ArrayList<>(10);

    public List<AssistantCard> getAssistentCard() {
        return this.assistentCard;
    }

    public List<AssistantCard> updateDeck() {
        return this.assistentCard;
    }

    public boolean checkIsEmpty() {
        return this.assistentCard == null;
    }

}
