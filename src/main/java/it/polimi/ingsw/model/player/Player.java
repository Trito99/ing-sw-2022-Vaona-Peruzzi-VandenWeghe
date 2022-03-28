package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColour;

import java.util.Date;

/**
 * This class represents the player and it contains all his information.
 */
public class Player {

    private final String nickname;
    private final Date age;               // CONTROLLARE LIBRERIA DATA
    private final PlayerNumber playerNumber;
    private TurnState turnState;
    private final School personalSchool;
    private AssistantCard trash;          //= Ultima carta nella pila degli scarti
    private final Team team;
    private int coinScore;
    private TColour tColour;

    /**
     * Default constructor.
     */
    public Player(String nickname, Date age, PlayerNumber playerNumber, School personalSchool, AssistantCard trash, Team team, TColour tColour, int coinScore) {
        this.nickname = nickname;
        this.age = age;
        this.playerNumber = playerNumber;
        this.personalSchool = personalSchool;
        this.tColour = tColour;
        this.trash = null;
        this.coinScore = 1;
        Game gameMode = Game.getGameMode();             //gameMode come parametro?(Vedi CloudCard)
        assert gameMode != null;
        if(gameMode.equals(GameMode.COOP)) this.team = team;
        else this.team = null;
    }

















    public String getNickname() {
        return this.nickname;
    }

    public String getAge() {
        return this.age;
    }

    public void setPlayerNumber(PlayerNumber playerNumber) {
        this.playerNumber = playerNumber;
    }  //Se è final non può esserci metodo set (da guardare)

    public PlayerNumber getPlayerNumber() {
        return this.playerNumber;
    }

    public TurnState getTurnState() {
        return this.turnState;
    }

    public void setTurnState(TurnState turnState) {
        this.turnState = turnState;
    }

    public School getPersonalSchool() {
        return this.personalSchool;
    }

    public AssistantCard getTrash() {
        return this.trash;
    }

    public Team getTeam() {
        return this.team;
    }

    public static int getCoinScore() {
        return this.coinScore;
    }   //Static?

    public void setCoinScore(int coinScore) {
        this.coinScore = coinScore;
    }

    public void increaseCoinScore(int coinScore){
        Player.setCoinScore(getCoinScore() + 1);
    }

    public void decreaseCoinScore(int coinScore){
        Player.setCoinScore(getCoinScore() -1);
    }

    public void initializeTower(GameMode gameMode, Player player, TColour tColour){  //Da Finire!!!
        if (gameMode.equals(GameMode.TWOPLAYERS)) {
            if(player.getPlayerNumber()==PlayerNumber.PLAYER1) tColour = TColour.WHITE;
            else if(player.getPlayerNumber()==PlayerNumber.PLAYER2) tColour = TColour.BLACK;
            //mettere 8 torri in TowerZone
        }
        if (gameMode.equals(GameMode.TWOPLAYERS)) {
            if(player.getPlayerNumber()==PlayerNumber.PLAYER1) tColour = TColour.WHITE;
            else if(player.getPlayerNumber()==PlayerNumber.PLAYER2) tColour = TColour.GREY;
            else if(player.getPlayerNumber()==PlayerNumber.PLAYER3) tColour = TColour.BLACK;
        }
        if (gameMode.equals(GameMode.COOP)) {
            int i = 0;
            if(player.team.getTeam().get(i) == /**...*/)) tColour = TColour.WHITE;
        }
    }
}

        }
