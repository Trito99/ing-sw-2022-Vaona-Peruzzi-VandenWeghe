package src.main.java.it.polimi.ingsw.model;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Player {

    private String nickname;
    private String age;               // CONTROLLARE LIBRERIA DATA
    private PlayerNumber PlayerNumber;
    private TurnState TurnState;
    private School PersonalSchool;
    private AssistentCard Trash;
    private Team Team;
    private int CoinScore;


    public String getNickname() {
        return this.nickname;
    }

    public String getAge() {
        return this.age;
    }

    public PlayerNumber getPlayerNumber() {
        return PlayerNumber;
    }

    public TurnState getTurnState() {
        return TurnState;
    }

    public void setTurnState(TurnState turnState) {
        this.TurnState = turnState;
    }

    public School getPersonalSchool() {
        return PersonalSchool;
    }

    public AssistentCard getTrash(AssistentCard.AssistentName, AssistentCard.TurnValue) {
        return Trash;
    }

    public Team getTeam() {
        return Team;
    }

    public int getCoinScore() {
        return CoinScore;
    }

    public int decreaseCoinScore(){
        //...
        return this.CoinScore;
    }

}
