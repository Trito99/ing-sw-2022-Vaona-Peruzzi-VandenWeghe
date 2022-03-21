package it.polimi.ingsw.model.game;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.bag.Bag;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;

public class Game {

    private int gameId; //?
    private GameMode gameMode; //?
    private ArrayList<Player> listOfPlayers;
    private int activePlayer;
    private int numberOfPlayers;
    private State state;
    private ArrayList<Player> round;
    private Difficulty difficulty;

    public Game(int gameId, GameMode gameMode, int numberOfPlayers, Difficulty difficulty) {
        this.gameId = gameId;
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.activePlayer = activePlayer;
        this.numberOfPlayers = numberOfPlayers;
        this.listOfPlayers = new ArrayList<>();
        this.round = new ArrayList<>();
    }

    public Difficulty getDifficulty(){
        return this.difficulty;
    }

    public void addPlayer(String nickname, String age, PlayerNumber playerNumber, School personalSchool, AssistantCard trash, Team team){
        listOfPlayers.add(new Player(nickname, age, playerNumber, personalSchool, trash, team, tColour));
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

    public ArrayList<Player> setRound(){   //setta ordine dei giocatori nel round

        int TurnValue = AssistantCard.getTurnValue();
        AssistantCard lastCard = Player.getTrash();

        //DA FINIRE
        }

    }

    public Player winnerIs() {
        if (DeckAssistant.checkIsEmpty() == true ||
                School.checkTowerIsEmpty() == true ||
                Bag.checkIsEmpty() == true ||
                Table.checkListOfIsland() == true)

            return Table.playerIsWinning();

    }

    public void initialize(){
        Game gameMode = Game.getGameMode();
        assert gameMode != null;
        if (gameMode.equals(GameMode.TWOPLAYERS))
           // DA Implementare


    }

}
