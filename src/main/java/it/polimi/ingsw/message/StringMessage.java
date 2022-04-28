package it.polimi.ingsw.message;

/** questo messaggio contiene una stringa generica */
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