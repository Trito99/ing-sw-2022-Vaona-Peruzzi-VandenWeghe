package it.polimi.ingsw.model.character;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;
import java.util.Random;

public class DeckCharacter {

    private ArrayList<CharacterCard> characterCards;

    public DeckCharacter(ArrayList<CharacterCard> characterCards){
        this.characterCards = new ArrayList<>(12);
    }

    public ArrayList<CharacterCard> getCharacterCards(){
        return this.characterCards;
    }

    public ArrayList<CharacterCard> shuffleCard(){
    return null;
    }


    public void drawCard(Table table){    //Pesca tre carte personaggio dal mazzo e mettile al centro del tavolo

        /**  Random x;
         x.nextInt(12);
         //Da verificare il Random x */
        characterCards.shuffleCard();

        for (int i = 0; i < 3; i++) {
            table.getCharacterCardsOnTable().add(i, characterCards.get(i));
        }

    }


}
