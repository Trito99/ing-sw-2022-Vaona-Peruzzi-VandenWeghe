package it.polimi.ingsw.message;

import it.polimi.ingsw.model.character.CharacterCard;

import java.util.ArrayList;

/**
 * Extends ServerMessage
 * Notifies the Character Card played
 */
public class PlayCharacterCard extends ServerMessage {

    private boolean choice;
    private int coins;
    private ArrayList<CharacterCard> list;

    public PlayCharacterCard(boolean choice, ArrayList<CharacterCard> list, int coins) {
        super(MessageType.PLAY_CHARACTER_CARD);
        this.choice = choice;
        this.list = list;
        this.coins = coins;
    }
     public boolean getChoice(){
        return  choice;
     }
    public ArrayList<CharacterCard> getList() { return list; }
    public int getCoins(){ return coins; }
}
