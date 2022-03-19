package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Player {

    private String nickname;
    private String age;               // CONTROLLARE LIBRERIA DATA
    private PlayerNumber playerNumber;
    private TurnState turnState;
    private School personalSchool;
    private AssistentCard trash;
    private Team team;
    private int coinScore;


    public String getNickname() {
        return this.nickname;
    }

    public String getAge() {
        return this.age;
    }

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

    public AssistentCard getTrash(String AssistentCard.assistentName, int AssistentCard.turnValue) {
        return this.trash;
    }

    public Team getTeam() {
        return this.team;
    }

    public int getCoinScore() {
        return this.coinScore;
    }

    public int increaseCoinScore(){
        coinScore = getCoinScore();
        coinScore = coinScore + 1;
        return this.coinScore;
    }

    public int decreaseCoinScore(){
        coinScore = getCoinScore();
        coinScore = coinScore - 1;
        return this.coinScore;
    }

}
