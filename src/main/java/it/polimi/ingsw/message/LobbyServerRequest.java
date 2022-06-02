package it.polimi.ingsw.message;

public class LobbyServerRequest extends ClientMessage {

    public LobbyServerRequest() {
        super(null,MessageType.LOBBY_SERVER_REQUEST);
    }

}
