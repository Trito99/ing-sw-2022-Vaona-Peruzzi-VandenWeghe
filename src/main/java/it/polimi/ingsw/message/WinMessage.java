package it.polimi.ingsw.message;

/**
 * Extends ServerMessage
 * Notifies a player that he won the match
 */
public class WinMessage extends ServerMessage{

    public WinMessage() {
        super(MessageType.WIN);
    }

}
