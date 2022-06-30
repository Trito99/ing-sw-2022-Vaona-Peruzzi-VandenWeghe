package it.polimi.ingsw.message;

import java.io.Serializable;

/**
 * Used to allow the serialization of all the messages
 */
public abstract class GeneralMessage implements Serializable {
    private final MessageType message;

    public GeneralMessage(MessageType message){
        this.message = message;
    }

    public MessageType getMessageType(){
        return message;
    }

}
