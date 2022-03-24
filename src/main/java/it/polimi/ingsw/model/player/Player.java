package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Team;
import it.polimi.ingsw.model.game.TurnState;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColour;

public class Player {

    private final String nickname;
    private final String age;               // CONTROLLARE LIBRERIA DATA
    private PlayerNumber playerNumber;
    private TurnState turnState;
    private School personalSchool;
    private AssistantCard trash;
    private Team team;
    private int coinScore;
    private TColour tColour;

    public Player(String nickname, String age, PlayerNumber playerNumber, School personalSchool, AssistantCard trash, Team team, TColour tColour, int coinScore) {
        this.nickname = nickname;
        this.age = age;
        this.playerNumber = playerNumber;
        this.personalSchool = personalSchool;
        this.tColour = tColour;
        this.trash = null;
        this.coinScore = 1;

        Game gameMode = Game.getGameMode();
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
