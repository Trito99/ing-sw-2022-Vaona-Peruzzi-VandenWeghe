package it.polimi.ingsw.message;


import it.polimi.ingsw.model.character.CharacterCard;

public class ChooseId extends ServerMessage {

    boolean choice;
    CharacterCard characterCard;

    public ChooseId(boolean choice, CharacterCard characterCard) {
        super(MessageType.CHOOSE_ID);
        this.choice = choice;
        this.characterCard = characterCard;
    }

    public CharacterCard getCharacterCard(){
        return characterCard;
    }

    public boolean getChoice(){
        return choice;
    }
}
