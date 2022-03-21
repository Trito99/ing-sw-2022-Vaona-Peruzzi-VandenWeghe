package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int gameId; //?
    private GameMode gameMode; //?
    private ArrayList<Player> listOfPlayers;
    private int activePlayer;
    private int numberOfPlayers;
    private State state;
    private ArrayList<int> round; // int meglio? con id del player (1,2,3,4)

    public Game() {
        this.listOfPlayers = new ArrayList<>();
        this.round = new ArrayList<>();
    }

    public void addPlayer(){
        this.listOfPlayer + 1;
        // ci sarà una notify observer
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

    public void setActivePlayer(int activePlayer){
        this.activePlayer = activePlayer;
    }

    public State getState(){
        return this.state;
    }

    public void setState(state State){
        this.state = state;
    }

    public ArrayList<Player> getRound(){
        return this.round;
    }

    public ArrayList<Player> setRound(){
        for (Player player : this.round = round) {

        }

    }

    public Player winnerIs(){
        //da fare
    }

    public void initialize(){
        //da fare
    }

}
