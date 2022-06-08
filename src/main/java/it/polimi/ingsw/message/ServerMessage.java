package it.polimi.ingsw.message;

import java.io.Serializable;

/** mostra tutti i messaggi forniti dal server */

public class ServerMessage extends GeneralMessage implements Serializable {

    public ServerMessage(MessageType msg){
        super(msg);
    }

}
