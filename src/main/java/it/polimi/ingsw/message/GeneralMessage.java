package it.polimi.ingsw.message;

import java.io.Serializable;

/** classe usata per tutti i messaggi per permettere la serializzazione */
public abstract class GeneralMessage implements Serializable {

    private final MessageType msg;
    public GeneralMessage(MessageType msg){

        this.msg=msg;
    }
    public MessageType getMessageType(){
        return msg;
    }

}
