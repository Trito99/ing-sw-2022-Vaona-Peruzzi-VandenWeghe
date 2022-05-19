package it.polimi.ingsw.message;

import it.polimi.ingsw.model.character.CharacterCard;

import java.util.ArrayList;

public class PlayCharacterCard extends ServerMessage {

    private boolean choice;
    private ArrayList<CharacterCard> list;


    public PlayCharacterCard(boolean choice, ArrayList<CharacterCard> list) {
        super(MessageType.PLAY_CHARACTER_CARD);
        this.choice = choice;
        this.list = list;
    }
     public boolean getChoice(){
        return  choice;
     }
     public ArrayList getList() { return list; }
}
