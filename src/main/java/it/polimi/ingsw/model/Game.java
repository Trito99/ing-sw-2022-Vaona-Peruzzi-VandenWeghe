package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int gameId;
    private GameMode gameMode;
    private ArrayList<Player> listOfPlayer = new ArrayList<>(2);
    private int activePlayer;
    private int numberOfPlayers;
    private State state;
    private ArrayList<Player> round = new ArrayList<>();

    public void addPlayer(){
        listOfPlayer  = listOfPlayer+1;
        //modo per scannerizzare nuovo Player
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public ArrayList<Player> getListOfPlayer{
            //da fare
    }

    public int getGameId(){
        return this.gameId;
    }

    public int getNumberOfPlayers(){
        return this.numberOfPlayers;
    }

    public Player getActivePlayer(){
        return this.activePlayer;
    }

    public void setActivePlayer(){
        this.activePlayer = activePlayer;
    }

    public State getState(){
        return this.state;
    }

    public void setState(){
        this.state = state;
    }

    public ArrayList<Player> getRound(){
        return this.round;
    }

    public ArrayList<Player> setRound(){
        this.round = round;
    }

    public Player winnerIs(){
        //da fare
    }

    public void initialize(){
        //da fare
    }

}
