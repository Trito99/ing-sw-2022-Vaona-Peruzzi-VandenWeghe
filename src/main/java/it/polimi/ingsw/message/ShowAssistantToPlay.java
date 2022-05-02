package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;

import java.util.ArrayList;

public class ShowAssistantToPlay extends ServerMessage {
    private ArrayList<AssistantCard> assistantDeck;

    public ShowAssistantToPlay(ArrayList<AssistantCard> assistantDeck) {
        super(MessageType.PLAY_ASSISTANT_CARD);
        this.assistantDeck = assistantDeck;
    }

    public ArrayList<AssistantCard> getAssistantCard() {
        return assistantDeck;
    }
}
