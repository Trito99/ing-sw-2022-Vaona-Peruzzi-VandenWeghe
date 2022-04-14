package it.polimi.ingsw.model.assistant;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class TestDeckAssistant {

    @Test
    public void generateAssistantDeck(){

        ArrayList <AssistantCard> cardsInHand = new ArrayList<>();
        DeckAssistant deckAssistant = new DeckAssistant(cardsInHand);

        int countCard = 0;
        int countStepMotherEarth = 0;
        int countTurnValue = 0;

        deckAssistant.generateAssistantDeck();

        assertNotNull(deckAssistant.getCardsInHand());
        assertEquals(10, deckAssistant.getCardsInHand().size());

        for(AssistantCard card : deckAssistant.getCardsInHand()){
            countStepMotherEarth = countStepMotherEarth + card.getStepMotherEarth();
            countTurnValue = countTurnValue + card.getTurnValue();
            countCard ++;
        }

        assertEquals(10, countCard);
        assertEquals(30, countStepMotherEarth);
        assertEquals(55, countTurnValue);

    }

}
