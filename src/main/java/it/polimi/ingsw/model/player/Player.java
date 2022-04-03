package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.bag.Bag;
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
    private int age;               // CONTROLLARE LIBRERIA DATA
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
    public Player(School personalSchool, AssistantCard trash, Team team, TColour tColour, int coinScore) {
        nickname = new String();
        age = 0;
        playerNumber = new PlayerNumber();
        personalSchool = new School();
        tColour = new TColour();
        trash = null;
        coinScore = 1;
        Game gameMode = Game.getGameMode();             //gameMode come parametro?(Vedi CloudCard)
        assert gameMode != null;
        if(gameMode.equals(GameMode.COOP)) this.team = team;
        else this.team = null;
    }

    public School generateSchool(Bag bag, GameMode gameMode, TColour tcolour){
        int i=7;
        int t=0;
        switch(gameMode){
            case TWOPLAYERS:
                i=7;
                t=8;
                break;
            case THREEPLAYERS:
                i=9;
                t=6;
                break;
            case COOP:
                i=7;
                t=0;
                break;
        }
        for(int s=0;s<i;s++) {
            personalSchool.getEntry().add(bag.getBag().get(s));
            bag.getBag().remove(bag.getBag().get(s));
        }
        for(int f=0;f<t;f++){
            personalSchool.addTower(f,tcolour);
        }
        return personalSchool;
    }


    public String getNickname() {
        return this.nickname;
    }

    public int getAge() {
        return age;
    }

    public PlayerNumber getPlayerNumber() {
        return playerNumber;
    }

    public TurnState getTurnState() {
        return turnState;
    }

    public void setTurnState(TurnState turnState) {
        this.turnState = turnState;
    }

    public School getPersonalSchool() {
        return personalSchool;
    }

    public AssistantCard getTrash() {
        return trash;
    }

    public Team getTeam() {
        return team;
    }


    public void setTColour(TColour tColour) {
        this.tColour = tColour;
    }

    public TColour getTColour() {
        return tColour;
    }

    public static int getCoinScore() {
        return coinScore;
    }   //Static?

    public static void setCoinScore(int coinScore) {
        this.coinScore = coinScore;
    }

    public void setTrash(AssistantCard trash) {
        this.trash = trash;
    }
}
