package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.GameMode;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;

public class LobbyForPrint implements Serializable {
    private Difficulty difficulty;
    private GameMode gameMode;
    private int currentPlayers;
    private String gameId;


    public LobbyForPrint(String gameId, Difficulty difficulty, GameMode gameMode, int currentPlayers) {
        this.gameId = gameId;
        this.currentPlayers = currentPlayers;
        this.difficulty = difficulty;
        this.gameMode = gameMode;
    }


    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public int getMaxPlayers(){
        int maxPlayers = 0;
        switch(gameMode){
            case TWOPLAYERS:
                maxPlayers = 2;
                break;
            case THREEPLAYERS:
                maxPlayers = 3;
                break;
            case COOP:
                maxPlayers = 4;
                break;
        }
        return  maxPlayers;
    }


    public Difficulty getDifficulty() {
        return difficulty;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

}
