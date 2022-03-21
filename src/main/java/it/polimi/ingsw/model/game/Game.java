package it.polimi.ingsw.model.game;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {

    private int gameId; //?
    private GameMode gameMode; //?
    private ArrayList<Player> listOfPlayers;
    private int activePlayer;
    private int numberOfPlayers;
    private State state;
    private ArrayList<Player> round;

    public Game(int gameId, GameMode gameMode, int numberOfPlayers) {
        this.gameId = gameId;
        this.gameMode = gameMode;
        this.activePlayer = activePlayer;
        this.numberOfPlayers = numberOfPlayers;
        this.listOfPlayers = new ArrayList<>();
        this.round = new ArrayList<>();
    }

    public void addPlayer(){
        listOfPlayers.addAll(listOfPlayers);
        // ci sarà una notify observer
        //modo per scannerizzare nuovo Player
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public ArrayList<Player> getListOfPlayer{
            return this.listOfPlayers;
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
        for(int i : Player ){
            if( //bella domanda )
        }

    }

    public Player winnerIs(){
        //da fare
    }

    public void initialize(){
        //da fare
    }

}
