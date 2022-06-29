package it.polimi.ingsw.network;

import java.util.*;

/**
 * Allows multiple games to be played at the same time
 */
public class LobbyServer {
    public Map<String, Lobby> lobbyMap;

    /**
     * Default constructor
     */
    public LobbyServer() {
        lobbyMap = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * Gets the Lobby associated to a certain game id
     * @param idLobby the id of a specific lobby
     * @return the lobby associated with the idLobby
     */
    public Lobby getLobby(String idLobby){
        Lobby lobby = lobbyMap.get(idLobby);
        if(lobby == null) lobbyMap.put(idLobby, new Lobby(idLobby));
        return lobbyMap.get(idLobby);
    }

    /**
     * Removes a player from the lobby
     * @param idLobby the id of a specific lobby
     */
    public void leaveLobby(String idLobby) {
        Lobby lobby = lobbyMap.get(idLobby);
        if(lobby!=null) {
            if (lobby.currentPlayers() == 0) lobbyMap.remove(idLobby);
        }
    }

    /**
     * Creates a different new Lobby
     * @return the new lobby
     */
    public Map<String, LobbyForPrint> getNewLobbyMap() {
        Map<String, LobbyForPrint> newLobbyMap = new HashMap<>();
        for(String lobbyId : lobbyMap.keySet()){
            newLobbyMap.put(lobbyId, new LobbyForPrint(lobbyId,lobbyMap.get(lobbyId).getGameController().getGameSession().getDifficulty(),lobbyMap.get(lobbyId).getGameController().getGameSession().getGameMode(),String.valueOf(lobbyMap.get(lobbyId).currentPlayers())));
        }
        return newLobbyMap;
    }
}
