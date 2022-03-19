package it.polimi.ingsw.model;

import java.util.List;

public class DeckAssistent {
    private List<DeckAssistent> assistentCard;

    public List<DeckAssistent> getAssistentCard() {
        return this.assistentCard;
    }

    public List<DeckAssistent> updateDeck() {
        return this.assistentCard;
    }

    public boolean checkIsEmpty() {
        if (this.assistentCard != null)
            return false;
        else
            return true;
        }
    }
}