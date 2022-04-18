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
        characterCards.add(new CharacterCard(1, CardEffect.ABATE));
        characterCards.add(new CharacterCard(2, CardEffect.OSTE));
        characterCards.add(new CharacterCard(3, CardEffect.ARALDO));
        characterCards.add(new CharacterCard(1, CardEffect.LATORE));
        characterCards.add(new CharacterCard(2, CardEffect.CURATRICE));
        characterCards.add(new CharacterCard(3, CardEffect.CENTAURO));
        characterCards.add(new CharacterCard(1, CardEffect.SALTIMBANCO));
        characterCards.add(new CharacterCard(2, CardEffect.CAVALIERE));
        characterCards.add(new CharacterCard(3, CardEffect.ERBORISTA));
        characterCards.add(new CharacterCard(1, CardEffect.BARDO));
        characterCards.add(new CharacterCard(2, CardEffect.CORTIGIANA));
        characterCards.add(new CharacterCard(3, CardEffect.RIGATTIERE));
    }
}
