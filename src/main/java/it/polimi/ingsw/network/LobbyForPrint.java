package it.polimi.ingsw.network;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;

import java.io.Serializable;

public class LobbyForPrint implements Serializable {
    private final Difficulty difficulty;
    private final GameMode gameMode;
    private String currentPlayers;
    private String gameId;


    public LobbyForPrint(String gameId, Difficulty difficulty, GameMode gameMode, String currentPlayers) {
        this.gameId = gameId;
        this.currentPlayers = currentPlayers;
        this.difficulty = difficulty;
        this.gameMode = gameMode;
    }


    public String getCurrentPlayers() {
        return currentPlayers;
    }

    public int getMaxPlayers(){
        return Game.initializePlayerNumber(gameMode);
    }


    public Difficulty getDifficulty() {
        return difficulty;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public String getGameId() {
        return gameId;
    }
}
