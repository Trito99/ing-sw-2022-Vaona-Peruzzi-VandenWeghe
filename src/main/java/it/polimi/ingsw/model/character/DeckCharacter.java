package it.polimi.ingsw.model.character;

import java.util.ArrayList;

/**
 * Implements the Character Deck composed of the 12 Character cards
 */
public class DeckCharacter {

    private ArrayList<CharacterCard> characterCards;

    public DeckCharacter(){
        this.characterCards = new ArrayList<>();
    }

    public ArrayList<CharacterCard> getCharacterCards(){
        return (ArrayList<CharacterCard>) characterCards.clone();
    }

    /**
     * creates the cards of the deck
     */
    public void generateCharacterDeck(){
        characterCards.add(new CharacterCard(1, CardEffect.ABBOT, "Take one of the students on this card and move them on an island of your choice."));
        characterCards.add(new CharacterCard(2, CardEffect.HOST,"During this round, you take the control of the professors even if you have the same number of students of others players."));
        characterCards.add(new CharacterCard(3, CardEffect.HERALD,"Choose an Island and act like if Mother Nature lands on that island."));
        characterCards.add(new CharacterCard(1, CardEffect.BEARER, "You can move Mother Nature up to 2 more steps of your maximum."));
        characterCards.add(new CharacterCard(2, CardEffect.CURATOR, "Place a 'Forbidden Card' on an Island. A 'Forbidden card' blocks the function of Mother Nature when she lands on that Island."));
        characterCards.add(new CharacterCard(3, CardEffect.CENTAUR, "During this round, Towers aren't considered in influence."));
        characterCards.add(new CharacterCard(1, CardEffect.ACROBAT, "You can choose up to 3 students on this card and switch them with the students of your Entry."));
        characterCards.add(new CharacterCard(2, CardEffect.KNIGHT, "During this round, you have 2 more influence points."));
        characterCards.add(new CharacterCard(3, CardEffect.HERBALIST, "Choose a color. During this round, the students of that color aren't considered in the calculate of influence."));
        characterCards.add(new CharacterCard(1, CardEffect.BARD,"You can switch up to 2 students between your Hall and your Entry"));
        characterCards.add(new CharacterCard(2, CardEffect.COURTESAN,"Choose 1 student from this card and place it in the Hall"));
        characterCards.add(new CharacterCard(3, CardEffect.JUNKDEALER,"Choose a color; Every player has to remove 3 students from his table of that color in the bag. "));
    }
}
