package it.polimi.ingsw.model.assistant;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class DeckAssistantTest {

    @Test
    public void generateAssistantDeckTest(){

        DeckAssistant deckAssistant = new DeckAssistant();
        deckAssistant.generateAssistantDeck();
        int countCard = 0,countStepMotherEarth = 0,countTurnValue = 0,n=1;
        assertNotNull(deckAssistant.getCardsInHand());
        assertEquals(10, deckAssistant.getCardsInHand().size());
        for(AssistantCard card : deckAssistant.getCardsInHand()){
            countStepMotherEarth = countStepMotherEarth + card.getStepMotherEarth();
            countTurnValue = countTurnValue + card.getTurnValue();
            countCard ++;
            for(int i=n;i<deckAssistant.getCardsInHand().size();i++)
                assertNotEquals(card,deckAssistant.getCardsInHand().get(i));/**Checks that all the cards created are different */
            n++;
        }
        assertEquals(10, countCard);  /**Checks if are generated 10 cards and with the correct values */
        assertEquals(30, countStepMotherEarth);
        assertEquals(55, countTurnValue);

    }

}
