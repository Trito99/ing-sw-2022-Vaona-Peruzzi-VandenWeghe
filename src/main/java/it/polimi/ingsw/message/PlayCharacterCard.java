package it.polimi.ingsw.message;

import it.polimi.ingsw.model.player.Player;

public class PlayCharacterCard extends ServerMessage {

    private boolean choice;
    private Player player;

    public PlayCharacterCard(boolean choice, Player player) {
        super(MessageType.PLAY_CHARACTER_CARD);
        this.choice = choice;
        this.player = player;
    }
     public boolean getChoice(){
        return  choice;
     }
     public Player getPlayer(){ return  player; }
}
