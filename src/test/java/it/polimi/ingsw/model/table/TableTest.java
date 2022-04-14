package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.character.DeckCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    private Table table;


    @BeforeEach
    public void init(){
        table=new Table();
        DeckCharacter characterDeck = new DeckCharacter();
        characterDeck.generateCharacterDeck();
        generateCharacterCardsOnTable();

    }

    @Test
    void generateCharacterCardsOnTable() {
        int cardsOnTable=0;

        assertNotNull(table.getCharacterCardsOnTable());
        for(CharacterCard card : table.getCharacterCardsOnTable()){
            cardsOnTable++;
            assertNotNull(card);
        }
        assertEquals(3,cardsOnTable);


        /** controlla i costCharacter
         for( CharacterCard card : deckCharacter.getCharacterCards()){
         if( card.getCardEffect().equals(CardEffect.MBRIACONE)){
         assertEquals(1, card.getCostCharacter());
         }
         } */

    }
}