package it.polimi.ingsw.model;

import java.util.List;

public class DeckAssistent {
    private List<DeckAssistent> assistentCard;

    public List<DeckAssistent> getAssistentCard() {
        return this.AssistentCard;
    }

    public List<DeckAssistent> updateDeck() {
        return this.AssistentCard;
    }

    public boolean checkIsEmpty() {
        if (this.AssistentCard != null)
            return false;
        else
            return true;
        }
    }
}