package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.DeckAssistant;

/**
 * Extends Server Message
 * Shows the player his Deck Assistant
 */
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
