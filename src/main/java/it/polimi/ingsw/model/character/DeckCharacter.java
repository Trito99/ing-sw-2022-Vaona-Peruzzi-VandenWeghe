package it.polimi.ingsw.model.character;

import java.util.ArrayList;

public class DeckCharacter {

    private ArrayList<CharacterCard> characterCards;

    public DeckCharacter(){
        this.characterCards = new ArrayList<>();
    }

    public ArrayList<CharacterCard> getCharacterCards(){
        return (ArrayList<CharacterCard>) characterCards.clone();
    }

    public void generateCharacterDeck(){
        characterCards.add(new CharacterCard(1, CardEffect.ABBOT));
        characterCards.add(new CharacterCard(2, CardEffect.HOST));
        characterCards.add(new CharacterCard(3, CardEffect.HERALD));
        characterCards.add(new CharacterCard(1, CardEffect.BEARER));
        characterCards.add(new CharacterCard(2, CardEffect.CURATOR));
        characterCards.add(new CharacterCard(3, CardEffect.CENTAUR));
        characterCards.add(new CharacterCard(1, CardEffect.ACROBAT));
        characterCards.add(new CharacterCard(2, CardEffect.KNIGHT));
        characterCards.add(new CharacterCard(3, CardEffect.HERBALIST));
        characterCards.add(new CharacterCard(1, CardEffect.BARD));
        characterCards.add(new CharacterCard(2, CardEffect.COURTESAN));
        characterCards.add(new CharacterCard(3, CardEffect.JUNKDEALER));
    }
}
