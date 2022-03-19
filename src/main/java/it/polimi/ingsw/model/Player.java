package src.main.java.it.polimi.ingsw.model;

import it.polimi.ingsw.model.AssistentCard;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Player {

    private String nickname;
    private String age;               // CONTROLLARE LIBRERIA DATA
    private PlayerNumber playerNumber;
    private TurnState turnState;
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
        return this.PlayerNumber;
    }

    public TurnState getTurnState() {
        return this.TurnState;
    }

    public void setTurnState(TurnState turnState) {
        this.TurnState = TurnState;
    }

    public School getPersonalSchool() {
        return this.PersonalSchool;
    }

    public AssistentCard getTrash(AssistentCard.AssistentName, AssistentCard.TurnValue) {
        return this.Trash;
    }

    public Team getTeam() {
        return this.Team;
    }

    public int getCoinScore() {
        return this.CoinScore;
    }

    public int decreaseCoinScore(){
        //...
        return this.CoinScore;
    }

}
