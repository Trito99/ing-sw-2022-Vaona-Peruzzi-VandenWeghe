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
        characterCards.add(new CharacterCard(1, CardEffect.ABBOT, "Take one of the students on this card and move them on the an island of your choice."));
        characterCards.add(new CharacterCard(2, CardEffect.HOST,"During this round take control of the professors even if you have the same number of students."));
        characterCards.add(new CharacterCard(3, CardEffect.HERALD,"Choose an Island and act like if Mother Nature lands on that island."));
        characterCards.add(new CharacterCard(1, CardEffect.BEARER, "You can move Mother Nature up to 2 more steps of your maximum."));
        characterCards.add(new CharacterCard(2, CardEffect.CURATOR, "Place a 'Forbidden Card' on an Island. A 'Forbidden card' blocks the function of Mother Nature when she lands on that Island."));
        characterCards.add(new CharacterCard(3, CardEffect.CENTAUR, "Towers aren't considered in influence."));
        characterCards.add(new CharacterCard(1, CardEffect.ACROBAT, "You can choose up to 3 students of this card and switch them with the students of your Hall."));
        characterCards.add(new CharacterCard(2, CardEffect.KNIGHT, "In this round you have 2 more influence points."));
        characterCards.add(new CharacterCard(3, CardEffect.HERBALIST, "Choose a color. The students of that color aren't considered in the influence."));
        characterCards.add(new CharacterCard(1, CardEffect.BARD,"You can switch up to 2 students between your Hall and the Tables"));
        characterCards.add(new CharacterCard(2, CardEffect.COURTESAN,"Choose 1 student from this card and place him on his table"));
        characterCards.add(new CharacterCard(3, CardEffect.JUNKDEALER,"Choose a color; Every player has to replace 3 students from his table of that color in the bag. "));
    }
}
