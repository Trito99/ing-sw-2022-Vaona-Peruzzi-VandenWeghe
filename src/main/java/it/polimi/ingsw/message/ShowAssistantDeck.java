package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.DeckAssistant;


public class ShowAssistantDeck extends ServerMessage{
    private DeckAssistant deckAssistant;
    private String nickname;
    public ShowAssistantDeck(DeckAssistant deckAssistant,String nickname) {
        super(MessageType.SHOW_ASSISTANT_DECK);
        this.deckAssistant = deckAssistant;
        this.nickname=nickname;
    }

    public DeckAssistant getDeckAssistant() {
        return deckAssistant;
    }

    public String getNickname(){ return nickname; }
}
