package it.polimi.ingsw.message;

import java.io.Serializable;

/** classe usata per tutti i messaggi per permettere la serializzazione */
public abstract class GeneralMessage implements Serializable {
    private final MessageType message;

    public GeneralMessage(MessageType message){
        this.message = message;
    }

    public MessageType getMessageType(){
        return message;
    }

}
