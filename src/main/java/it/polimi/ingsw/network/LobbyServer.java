package it.polimi.ingsw.network;

import java.util.*;

/** classe che tiene traccia di tutti i giochi in corso contemporaneamente */

public class LobbyServer {
    public Map<String, Lobby> lobbyMap;

    /** default constructor */
    public LobbyServer() {
        lobbyMap = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     *
     * @param idLobby
     * @return the lobby associated with the idLobby
     */
    public Lobby getLobby(String idLobby){
        Lobby lobby = lobbyMap.get(idLobby);
        if(lobby == null) lobbyMap.put(idLobby, new Lobby(idLobby));
        return lobbyMap.get(idLobby);
    }

    /** remove a player from the lobby
     *
     * @param idLobby
     */
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
            newLobbyMap.put(lobbyId, new LobbyForPrint(lobbyId,lobbyMap.get(lobbyId).getGameController().getGameSession().getDifficulty(),lobbyMap.get(lobbyId).getGameController().getGameSession().getGameMode(),String.valueOf(lobbyMap.get(lobbyId).currentPlayers())));
        }
        return newLobbyMap;
    }
}
