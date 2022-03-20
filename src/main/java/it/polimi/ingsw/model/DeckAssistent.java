package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class DeckAssistent {
    private List<AssistentCard> assistentCard = new ArrayList<>(10);

    public List<AssistentCard> getAssistentCard() {
        return this.assistentCard;
    }

    public List<AssistentCard> updateDeck() {
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