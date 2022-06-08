package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.DeckAssistant;

public class ShowAssistantDeck extends ServerMessage{
    private DeckAssistant deckAssistant;

    public ShowAssistantDeck(DeckAssistant deckAssistant) {
        super(MessageType.SHOW_ASSISTANT_DECK);
        this.deckAssistant = deckAssistant;
    }

    public DeckAssistant getDeckAssistant() {
        return deckAssistant;
    }

}
