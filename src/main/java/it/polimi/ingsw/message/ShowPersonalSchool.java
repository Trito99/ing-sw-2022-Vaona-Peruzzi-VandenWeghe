package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.school.School;

public class ShowPersonalSchool extends ServerMessage {
    private School school;
    private String nickname;
    private AssistantCard trash;
    private Difficulty difficulty;
    private int coins;
    private GameMode gameMode;
    private String teamMate;

    public ShowPersonalSchool(School school, String nickname, AssistantCard trash, Difficulty difficulty, int coins, GameMode gameMode, String teamMate) {
        super(MessageType.SHOW_PERSONAL_SCHOOL);
        this.school = school;
        this.nickname = nickname;
        this.trash = trash;
        this.difficulty = difficulty;
        this.coins = coins;
        this.gameMode = gameMode;
        this.teamMate = teamMate;

    }

    public AssistantCard getTrash() { return trash; }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getCoins(){
        return coins;
    }

    public School getSchool() {
        return school;
    }

    public String getNickname(){ return nickname; }

    public GameMode getGameMode() {
        return gameMode;
    }

    public String getTeamMate() {
        return teamMate;
    }
}
