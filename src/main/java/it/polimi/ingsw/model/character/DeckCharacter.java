package it.polimi.ingsw.model.character;

import java.util.ArrayList;
import java.util.Collections;

public class DeckCharacter {

    private ArrayList<CharacterCard> characterCards;

    public DeckCharacter(){
        this.characterCards = new ArrayList<>();
    }

    public ArrayList<CharacterCard> getCharacterCards(){
        return (ArrayList<CharacterCard>) characterCards.clone();
    }

    public void shuffleCard(ArrayList<CharacterCard> characterCards){
        Collections.shuffle(characterCards);
    }

    public void generateCharacterDeck(){
        characterCards.add(new CharacterCard(1, CardEffect.BACCO));
        characterCards.add(new CharacterCard(2, CardEffect.CICCIOPANZA));
        characterCards.add(new CharacterCard(3, CardEffect.ALZABANDIERA));
        characterCards.add(new CharacterCard(1, CardEffect.MAILMAN));
        characterCards.add(new CharacterCard(2, CardEffect.OLDLADY));
        characterCards.add(new CharacterCard(3, CardEffect.TAURO));
        characterCards.add(new CharacterCard(1, CardEffect.JOKER));
        characterCards.add(new CharacterCard(2, CardEffect.KNIGHT));
        characterCards.add(new CharacterCard(3, CardEffect.FUNGAIOLO));
        characterCards.add(new CharacterCard(1, CardEffect.MENESTRELLO));
        characterCards.add(new CharacterCard(2, CardEffect.DAME));
        characterCards.add(new CharacterCard(3, CardEffect.THIEF));
    }
}
