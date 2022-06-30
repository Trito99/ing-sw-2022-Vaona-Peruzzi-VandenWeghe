package it.polimi.ingsw.message;

/**
 * Extends Server Message
 * Notifies Assistant Card to play
 */

public class PlayAssistantCard extends ServerMessage {

    public PlayAssistantCard() {
        super(MessageType.PLAY_ASSISTANT_CARD);
    }

}
