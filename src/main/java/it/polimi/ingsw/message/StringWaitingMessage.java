package it.polimi.ingsw.message;

/** questo messaggio contiene una stringa generica */
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