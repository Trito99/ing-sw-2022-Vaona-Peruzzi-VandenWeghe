package it.polimi.ingsw.model.character;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DeckCharacter {

    private ArrayList<CharacterCard> characterCards;

    public DeckCharacter(ArrayList<CharacterCard> characterCards){
        this.characterCards = new ArrayList<>(12);
    }

    public ArrayList<CharacterCard> getCharacterCards(){
        return characterCards;
    }

    public void shuffleCard(ArrayList<CharacterCard> characterCards){
        Collections.shuffle(characterCards);
    }


    public void drawCard(Table table){    //Pesca tre carte personaggio dal mazzo e mettile al centro del tavolo

        shuffleCard(characterCards); //non sono sicuro di vaere passato l'array giusto

        for (int i = 0; i < 3; i++) {
            table.getCharacterCardsOnTable().add(i, characterCards.get(i));
        }

    }


}
