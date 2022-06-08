package it.polimi.ingsw.message;

import it.polimi.ingsw.network.LobbyForPrint;

import java.util.Map;

public class LobbyServerInfo extends ServerMessage {

    private Map<String, LobbyForPrint> lobbyMap;

    public LobbyServerInfo(Map<String, LobbyForPrint> lobbyMap) {
        super(MessageType.LOBBY_SERVER_INFO);
        this.lobbyMap = lobbyMap;
    }

    public Map<String, LobbyForPrint> getLobbyMap() {
        return lobbyMap;
    }
}
