package it.polimi.ingsw.model.game;


import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;

public class Game {

    //private int gameId;   serve per partite multiple
    private GameMode gameMode;
    private ArrayList<Player> listOfPlayers;
    private Player activePlayer;
    private int numberOfPlayers;  // Non serve, basta la gameMode (Two/Three players, coop)
    private State state;
    private ArrayList<Player> order;
    private Difficulty difficulty;
    private Table table;
    private ArrayList<Team> team;

    /**
     * Default constructor.
     */
    public Game() {
        this.gameMode = null;
        this.listOfPlayers = new ArrayList<>();
        this.activePlayer = null;
        this.numberOfPlayers = 0;
        this.state = null;
        this.order = new ArrayList<>();
        this.difficulty = null;
        this.table = new Table();
        this.team = new ArrayList<>();

        /**table.generateIslandCards();
        table.generateMotherEarth();
        table.generateCloudNumber(gameMode);  */
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public ArrayList<Player> getListOfPlayer(){
        return (ArrayList<Player>) listOfPlayers.clone();
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
        return (ArrayList<Player>) order.clone();
    }

    /**
     * winnerIs deve rimanere all'interno di Game   (?)
     */
    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ArrayList<Team> getTeam() {
        return (ArrayList<Team>) team.clone();
    }

    public boolean gameIsFinished() {
        Player player = getActivePlayer();

        if (player.getDeckOfPlayer().getCardsInHand().isEmpty() ||   // Dobbiamo collegare ogni deck assistant al suo player
                player.getPersonalSchool().getTower().isEmpty() ||
                table.getBag().isEmpty() ||
                table.getListOfIsland().size() == 3 /** da verificare */) {
            return true;
        }

        return false;
    }
}
