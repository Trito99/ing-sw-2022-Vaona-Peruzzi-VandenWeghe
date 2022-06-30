package it.polimi.ingsw.model.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardEffectTest {

    @Test
    void setAllFalse() {
        DeckCharacter deckCharacter = new DeckCharacter();
        deckCharacter.generateCharacterDeck();
        CharacterCard characterCard = deckCharacter.getCharacterCards().get(11); //JUNKDEALER (cost 3)
        CardEffect cardEffect = characterCard.getCardEffect();
        assertEquals("JUNKDEALER", cardEffect.name());
        assertEquals(3, characterCard.getCostCharacter());

        cardEffect.setCentaurPlayed(true);
        cardEffect.setKnightPlayed(true);
        cardEffect.setBearerPlayed(true);
        cardEffect.setHostPlayed(true);

        assertEquals(true, cardEffect.isCentaurPlayed());
        assertEquals(true, cardEffect.isKnightPlayed());
        assertEquals(true, cardEffect.isBearerPlayed());
        assertEquals(true, cardEffect.isHostPlayed());
        cardEffect.setAllFalse();
        assertEquals(false, cardEffect.isCentaurPlayed());
        assertEquals(false, cardEffect.isKnightPlayed());
        assertEquals(false, cardEffect.isBearerPlayed());
        assertEquals(false, cardEffect.isHostPlayed());

    }
}