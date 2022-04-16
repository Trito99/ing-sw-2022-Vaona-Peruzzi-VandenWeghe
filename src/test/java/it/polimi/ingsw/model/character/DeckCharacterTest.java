package it.polimi.ingsw.model.character;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class DeckCharacterTest {

    @Test
    public void generateDeckCharacter(){
        DeckCharacter deckCharacter = new DeckCharacter();
        deckCharacter.generateCharacterDeck();
        int count=0,n=1;
        assertNotNull(deckCharacter);
        for(CharacterCard card : deckCharacter.getCharacterCards()){
            count++;
            assertNotNull(card);
            for(int i=n;i<deckCharacter.getCharacterCards().size();i++) /** Checks if all cards are different */
                assertNotEquals(card, deckCharacter.getCharacterCards().get(i));
            n++;
        }

        assertEquals(12, count); /**Checks that 12 characterCards are created */

    }





}
