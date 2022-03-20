package it.polimi.ingsw.model;

import java.util.ArrayList;

public class DeckCharacter {

    private ArrayList<CharacterCard> characterCards = new ArrayList<>(12); //10 o 12??

    public ArrayList<CharacterCard> getAssistantCard(){
        return this.characterCards;
    }

    public ArrayList<CharacterCard> updateDeck(){
        return this.characterCards;
    }

    public boolean checkIsEmpty(){
        if (this.characterCards != null)
            return false;
        else
            return true;
    }

}
