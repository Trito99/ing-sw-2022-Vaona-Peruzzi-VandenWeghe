package it.polimi.ingsw.message;

/**
 * Extends Server Message
 * Notifies when a player chose players number and difficulty
 */
public class HostGameReply extends ServerMessage {

    public HostGameReply(){
        super(MessageType.SUCCESSFUL_HOST);
    }

}
