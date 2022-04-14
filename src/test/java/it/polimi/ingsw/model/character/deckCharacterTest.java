package it.polimi.ingsw.model.character;

import it.polimi.ingsw.model.table.Table;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class deckCharacterTest {

    @Test
    public void generateDeckCharacter(){

        DeckCharacter deckCharacter = new DeckCharacter();
        deckCharacter.generateCharacterDeck();
        int count=0;

        assertNotNull(deckCharacter);

        for(CharacterCard card : deckCharacter.getCharacterCards()){
            count++;
            CharacterCard actualCard=card;
            assertNotNull(card);
        }

        assertEquals(12, count);

    }





}
