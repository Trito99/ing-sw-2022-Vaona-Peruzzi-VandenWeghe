package it.polimi.ingsw.message;

/**
 * Extends Server Message
 * This message contains a generic waiting String
 */
public class StringWaitingMessage extends ServerMessage{
    private String message;

    public StringWaitingMessage(String message){
        super(MessageType.WAITING_MESSAGE);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}