package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;


public class ShowAssistantDeck extends ServerMessage{
    private DeckAssistant deckAssistant;
    private String nickname;
    private AssistantCard trash;
    public ShowAssistantDeck(DeckAssistant deckAssistant) {
        super(MessageType.SHOW_ASSISTANT_DECK);
        this.deckAssistant = deckAssistant;
        this.nickname=nickname;
        this.trash = trash;
    }

    public DeckAssistant getDeckAssistant() {
        return deckAssistant;
    }

    public String getNickname(){ return nickname; }
}
