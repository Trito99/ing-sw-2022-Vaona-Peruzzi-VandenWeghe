package it.polimi.ingsw.model.character;

import it.polimi.ingsw.model.character.CharacterCard;

import java.util.ArrayList;

public class DeckCharacter {

    private ArrayList<CharacterCard> characterCards;

    public DeckCharacter(ArrayList<CharacterCard> characterCards){
        this.characterCards = new ArrayList<>(12);
    }

    public ArrayList<CharacterCard> getAssistantCard(){
        return this.characterCards;
    }

    public ArrayList<CharacterCard> updateDeck(){
        return this.characterCards;
    }

    public boolean checkIsEmpty(){
        return characterCards.toArray() != 0;
    }

}
