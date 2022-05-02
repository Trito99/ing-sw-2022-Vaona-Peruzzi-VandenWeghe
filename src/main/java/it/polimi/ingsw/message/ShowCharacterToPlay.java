package it.polimi.ingsw.message;

import it.polimi.ingsw.model.character.CharacterCard;

import java.util.ArrayList;

public class ShowCharacterToPlay extends ServerMessage{

    private ArrayList<CharacterCard> characterCard;
    public ShowCharacterToPlay(ArrayList<CharacterCard> characterCard) {
        super(MessageType.PLAY_CHARACTER_CARD);
        this.characterCard = characterCard;
    }

    public ArrayList<CharacterCard> getCharacterCard() {
        return characterCard;
    }
}
