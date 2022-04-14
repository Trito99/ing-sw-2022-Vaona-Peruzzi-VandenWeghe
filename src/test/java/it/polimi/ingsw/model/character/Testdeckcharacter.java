package it.polimi.ingsw.model.character;

import it.polimi.ingsw.model.table.Table;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Testdeckcharacter {

    private Table table;

    @BeforeEach
    public void init(){
        table = new Table();
    }

    @Test
    public void DeckCharacter(){

        ArrayList <CharacterCard> characterCards = new ArrayList<>();
        DeckCharacter deckCharacter = new DeckCharacter(characterCards);

        int countCardOnTable = 0;

        //deckCharacter.drawCard(table);

        for(CharacterCard card : table.getCharacterCardsOnTable()){
            countCardOnTable++;
        }

        assertNotNull(deckCharacter.getCharacterCards());
        assertNotNull(countCardOnTable);
        assertEquals(3, countCardOnTable);

        /** controlla i costCharacter
       for( CharacterCard card : deckCharacter.getCharacterCards()){
           if( card.getCardEffect().equals(CardEffect.MBRIACONE)){
               assertEquals(1, card.getCostCharacter());
           }
       } */

    }





}
