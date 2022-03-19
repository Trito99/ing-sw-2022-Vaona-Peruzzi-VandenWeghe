package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int gameId;
    private GameMode gameMode;
    private ArrayList<Player> listOfPlayer;
    private int activePlayer;
    private int numberOfPlayers;
    private State state;
    private ArrayList<Player> round;

    public void addPlayer(){
        ListOfPlayer  = ListOfPlayer+1;
        //modo per scannerizzare nuovo Player
    }

    public GameMode getGameMode() {
        return this.GameMode;
    }

    public ArrayList<Player> getListOfPlayer{

    }

    public int getGameId(){
        return this.GameId;
    }

    public int getNumberOfPlayers(){
        return this.NumberOfPlayers;
    }

    public Player getActivePlayer(){
        return this.ActivePlayer;
    }

    public void setActivePlayer(){
        this.ActivePlayer = ActivePlayer;
    }

    public State getState(){
        return this.State;
    }

    public void setState(){
        this.State = State;
    }

    public ArrayList<Player> getRound(){
        return this.Round;
    }

    public ArrayList<Player> setRound(){
        this.Round = Round;
    }

    public Player winnerIs(){
        //da fare
    }

    public void initialize(){
        //da fare
    }

}
