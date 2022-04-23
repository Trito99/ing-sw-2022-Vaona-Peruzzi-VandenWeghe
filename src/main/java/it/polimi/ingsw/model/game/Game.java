package it.polimi.ingsw.model.game;


import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;

public class Game {

    //private int gameId;   servirebbe per partite multiple
    private GameMode gameMode;
    private ArrayList<Player> listOfPlayers;
    private ArrayList<String> nameOfPlayers;
    private Player activePlayer;
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
        this.nameOfPlayers = new ArrayList<>();
        this.activePlayer = null;
        this.state = null;
        this.order = new ArrayList<>();
        this.difficulty = null;
        this.table = new Table();
        this.team = new ArrayList<>();

        /**table.generateIslandCards();
        table.generateMotherEarth();
        table.generateCloudNumber(gameMode);  */
    }

    private ArrayList<String> getNameOfPlayers() {

        for(Player p : listOfPlayers) {
            nameOfPlayers.add(p.getNickname());
        }
        return nameOfPlayers;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public ArrayList<Player> getListOfPlayers(){
        return listOfPlayers;
    }

    public Player getActivePlayer(){   // tipo Player o Int???
        return activePlayer;
    }

    public Player getPlayer(String nickname){
        int indexPlayer = getNameOfPlayers().indexOf(nickname);
        return getListOfPlayers().get(indexPlayer);
    }

    /** aggiunge giocatore alla lista giocatori */
    public void addPlayer(Player player) {
        if(listOfPlayers.size()<4){
            listOfPlayers.add(player);
        }
    }

    public void setActivePlayer(Player activePlayer){
        this.activePlayer = activePlayer;
    }

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

        /** da verificare */
        return player.getDeckOfPlayer().getCardsInHand().isEmpty() ||   // Dobbiamo collegare ogni deck assistant al suo player
                player.getPersonalSchool().getTower().isEmpty() ||
                table.getBag().isEmpty() ||
                table.getListOfIsland().size() == 3;
    }
}
