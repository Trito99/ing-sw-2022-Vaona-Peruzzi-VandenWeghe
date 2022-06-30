package it.polimi.ingsw.message;

/**
 * Extends Server Message
 * This message contains a generic String
 */
public class StringMessage extends ServerMessage{
    private String message;

    public StringMessage(String message){
        super(MessageType.STRING_MESSAGE);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}