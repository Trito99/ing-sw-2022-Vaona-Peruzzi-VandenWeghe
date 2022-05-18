package it.polimi.ingsw.message;

public class PlayCharacterCard extends ServerMessage {

    private boolean choice;

    public PlayCharacterCard(boolean choice) {
        super(MessageType.PLAY_CHARACTER_CARD);
        this.choice = choice;
    }
     public boolean getChoice(){
        return  choice;
     }
}
