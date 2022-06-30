package it.polimi.ingsw.message;

/**
 * Extends Client Message
 * Notifies lobby's infos
 */
public class LobbyServerRequest extends ClientMessage {

    public LobbyServerRequest() {
        super(null,MessageType.LOBBY_SERVER_REQUEST);
    }

}
