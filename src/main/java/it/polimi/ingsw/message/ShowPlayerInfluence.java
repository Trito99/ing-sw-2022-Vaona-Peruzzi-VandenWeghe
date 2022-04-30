package it.polimi.ingsw.message;

import java.util.ArrayList;

public class ShowPlayerInfluence extends ServerMessage{
    private int influence;

    public ShowPlayerInfluence(int influence) {
        super(MessageType.SHOW_PLAYER_INFLUENCE);
        this.influence = influence;
    }

    public int getInfluence() {
        return influence;
    }
}
