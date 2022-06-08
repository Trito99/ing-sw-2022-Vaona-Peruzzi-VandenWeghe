package it.polimi.ingsw.network;

import java.util.*;

/** classe che tiene traccia di tutti i giochi in corso contemporaneamente */

public class LobbyServer {
    public Map<String, Lobby> lobbyMap;

    /** costruttore di default */
    public LobbyServer() {
        lobbyMap = Collections.synchronizedMap(new HashMap<>());
    }

    /** return la Lobby associata ad un determinato gameId */
    public Lobby getLobby(String idLobby){
        Lobby lobby = lobbyMap.get(idLobby);
        if(lobby == null) lobbyMap.put(idLobby, new Lobby(idLobby));
        return lobbyMap.get(idLobby);
    }

    /** consente ad un giocatore di uscire dalla Lobby */
    public void leaveLobby(String idLobby) {
        Lobby lobby = lobbyMap.get(idLobby);
        if(lobby!=null) {
            if (lobby.currentPlayers() == 0)
                lobbyMap.remove(idLobby);
        }
    }

    public Map<String, LobbyForPrint> getNewLobbyMap() {
        Map<String, LobbyForPrint> newLobbyMap = new HashMap<>();
        for(String lobbyId : lobbyMap.keySet()){
            newLobbyMap.put(lobbyId, new LobbyForPrint(lobbyId,lobbyMap.get(lobbyId).getGameController().getGameSession().getDifficulty(),lobbyMap.get(lobbyId).getGameController().getGameSession().getGameMode(),lobbyMap.get(lobbyId).currentPlayers()));
        }
        return newLobbyMap;
    }
}
