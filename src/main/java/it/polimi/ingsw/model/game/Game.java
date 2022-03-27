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

public class Game {

    private int gameId; //?
    private GameMode gameMode; //?
    private ArrayList<Player> listOfPlayers;
    private Player activePlayer;     // tipo Player o Int???
    private int numberOfPlayers;
    private State state;
    private ArrayList<Player> round;
    private Difficulty difficulty;

    public Game(int gameId, GameMode gameMode, int numberOfPlayers, Difficulty difficulty) {
        this.gameId = gameId;
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.activePlayer = null;
        this.numberOfPlayers = numberOfPlayers;
        this.listOfPlayers = new ArrayList<>();
        this.round = new ArrayList<>();
    }

    public Difficulty getDifficulty(){
        return this.difficulty;
    }

    public void addPlayer(String nickname, String age, PlayerNumber playerNumber, School personalSchool, AssistantCard trash, Team team, TColour tColour, int coinScore){
        listOfPlayers.add(new Player(nickname, age, playerNumber, personalSchool, null, team, tColour, 1));
                                                                                            //se EasyMode coinscore=0!!!
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public ArrayList<Player> getListOfPlayer(){
            return this.listOfPlayers;
    }

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

    public ArrayList<Player> getRound(){
        return this.round;
    }

    public ArrayList<Player> setRound(ArrayList<Player> round){   //setta ordine dei giocatori nel round
        for(Player i : round) {
            round.set(i, Player.getTrash().getTurnValue()); //assegna ad ogni player il turnvalue della sua ultima carta giocata

        //DA CONTROLLARE !!!
        }
        round.sort(Player.getTrash().getTurnValue());
    return round;
    } //?

    public Player winnerIs() {
        if (DeckAssistant.checkIsEmpty() ||
                School.checkTowerIsEmpty() ||
                Bag.checkIsEmpty() ||
                Table.checkListOfIsland())

            return Table.playerIsWinning();

    } //?

    public void initialize(){
        Game gameMode = Game.getGameMode();   //Mettere Scelta della Difficulty e della gameMode dal Player che crea la partita???
        assert gameMode != null;
        if (gameMode.equals(GameMode.TWOPLAYERS))
           // DA Implementare


    }

}
