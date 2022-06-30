package it.polimi.ingsw.message;

import java.io.Serializable;

/**
 * Shows all the messages from the server
 */
public class ServerMessage extends GeneralMessage implements Serializable {

    public ServerMessage(MessageType message){
        super(message);
    }

}
