package it.polimi.ingsw.model.game;


import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.bag.Bag;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;
import java.util.Date;

public class Game {

    //private int gameId;
    private GameMode gameMode;
    private ArrayList<Player> listOfPlayers;
    private Player activePlayer;
    private int numberOfPlayers;
    private State state;
    private ArrayList<Player> order;
    private Difficulty difficulty;
    private Table table;
    private Bag bag;

    /**
     * Default constructor.
     */
    public Game() {
        listOfPlayers = new ArrayList<Player>();
        table = new Table();
        bag = new Bag();
    }

    public void addPlayer(Player player) {
        if(listOfPlayers.size()<4){
            listOfPlayers.add(player);
        }
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public ArrayList<Player> getListOfPlayer(){
        return listOfPlayers;
    }

    /**     SECONDO ME SAREBBE PIù CORRETTO - chiara
     * public ArrayList<Player> getListOfPlayer(){
     *      ArrayList<Player> listOfPlayers = new ArrayList<>()
     *      for (int i = 0; i < getListOfPlayer().size(); i++) {
     *          listOfPlayers.get(i).add(getListOfPlayer();
     *          }
     *      return listOfPlayers;
     * }
     */

    /**
    public int getGameId(){
        return this.gameId;
    }
    */

    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public Player getActivePlayer(){   // tipo Player o Int???
        return activePlayer;
    }  //Player o int?

    public void setActivePlayer(Player activePlayer){
        this.activePlayer = activePlayer;
    }  // Player o int?

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
    }

    public void setGameMode(GameMode gameMode){
        this.gameMode = gameMode;
    }

    public Difficulty getDifficulty(){
        return difficulty;
    }

    public ArrayList<Player> getOrder(){
        return order;
    }

    /**
     * winnerIs deve rimanere all'interno di Game
     */
    public boolean gameIsFinished(Bag bag, Table table) {
        Player player = getActivePlayer();

        if (player.getDeckOfPlayer().getCardsInHand().size() == 0 ||   // Dobbiamo collegare ogni deck assistant al suo player
                player.getPersonalSchool().getTower().size() == 0 ||
                bag.getBag().size() == 0 ||
                table.getListOfIsland().size() == 3 /** da verificare */) {
            return true;
        }

        return false;
    }



}
