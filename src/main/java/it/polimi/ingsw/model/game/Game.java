package it.polimi.ingsw.model.game;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.bag.Bag;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColour;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;
import java.util.Date;

public class Game {

    private int gameId; //?
    private GameMode gameMode; //?
    private ArrayList<Player> listOfPlayers;
    private Player activePlayer;     // tipo Player o Int???
    private int numberOfPlayers;
    private State state;
    private ArrayList<Player> order;
    private Difficulty difficulty;

    /**
     * Default constructor.
     */
    public Game() {
        gameId = 0;
        gameMode = new GameMode;
        difficulty = new Difficulty;
        activePlayer = new Player;
        numberOfPlayers = 0;
        listOfPlayers = new ArrayList<>();
        order = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if(listOfPlayers.size()<4){
            listOfPlayers.add(player);
        }
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public ArrayList<Player> getListOfPlayer(){
        return this.listOfPlayers;
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


    public int getGameId(){
        return this.gameId;
    }

    public int getNumberOfPlayers(){
        return this.numberOfPlayers;
    }

    public Player getActivePlayer(){   // tipo Player o Int???
        return this.activePlayer;
    }  //Player o int?

    public void setActivePlayer(Player activePlayer){
        this.activePlayer = activePlayer;
    }  // Player o int?

    public State getState(){
        return this.state;
    }

    public void setState(State state){
        this.state = state;
    }

    public void setGameMode(GameMode gameMode){
        this.gameMode = gameMode;
    }

    public Difficulty getDifficulty(){
        return this.difficulty;
    }

    public ArrayList<Player> getOrder(){
        return this.order;
    }

    /**
     * winnerIs deve rimanere all'interno di Game
     */
    public Player winnerIs() {
        if (DeckAssistant.checkIsEmpty() ||
                School.checkTowerIsEmpty() ||
                Bag.checkIsEmpty() ||
                Table.checkListOfIsland())

            return Table.playerIsWinning();

    } //?



}
