package it.polimi.ingsw.message;

import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class PlayCharacterCard extends ServerMessage {

    private boolean choice;
    private Player player;
    private ArrayList<CharacterCard> list;

    public PlayCharacterCard(boolean choice, ArrayList<CharacterCard> list, Player player) {
        super(MessageType.PLAY_CHARACTER_CARD);
        this.choice = choice;
        this.list = list;
        this.player = player;
    }
     public boolean getChoice(){
        return  choice;
     }
    public ArrayList getList() { return list; }
    public Player getPlayer(){ return  player; }
}
