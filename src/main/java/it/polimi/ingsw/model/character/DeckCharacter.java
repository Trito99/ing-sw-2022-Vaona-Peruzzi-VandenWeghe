package it.polimi.ingsw.model.character;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
        characterCards.add(new CharacterCard(1, CardEffect.MBRIACONE));
        characterCards.add(new CharacterCard(1, CardEffect.CICCIOPANZA));
        characterCards.add(new CharacterCard(1, CardEffect.ALZABANDIERA));
        characterCards.add(new CharacterCard(1, CardEffect.CEPOSTAPERTE));
        characterCards.add(new CharacterCard(1, CardEffect.SCIURA));
        characterCards.add(new CharacterCard(1, CardEffect.TAURO));
        characterCards.add(new CharacterCard(1, CardEffect.JOKER));
        characterCards.add(new CharacterCard(1, CardEffect.SILVIO));
        characterCards.add(new CharacterCard(1, CardEffect.FUNGAIOLO));
        characterCards.add(new CharacterCard(1, CardEffect.MENESTRELLO));
        characterCards.add(new CharacterCard(1, CardEffect.DAMA));
        characterCards.add(new CharacterCard(1, CardEffect.TOSSICO));

    }


  /**  public void drawCard(Table table){    //Pesca tre carte personaggio dal mazzo e mettile al centro del tavolo

        shuffleCard(characterCards); //non sono sicuro di vaere passato l'array giusto

        for (int i = 0; i < 3; i++) {
            table.getCharacterCardsOnTable().add(i, characterCards.get(i));
        }

    } */


}
