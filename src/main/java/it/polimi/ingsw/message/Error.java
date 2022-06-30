package it.polimi.ingsw.message;

/**
 * Extends Server Message
 * This message contains a generic error String
 */
public class Error extends ServerMessage{
    private String message;

    public Error(String message) {
        super(MessageType.ERROR);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
