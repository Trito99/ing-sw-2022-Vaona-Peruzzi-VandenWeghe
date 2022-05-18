package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.school.School;

public class ShowPersonalSchool extends ServerMessage {
    private School school;
    private String nickname;
    private AssistantCard trash;
    private Difficulty difficulty;
    private int coins;

    public ShowPersonalSchool(School school, String nickname, AssistantCard trash, Difficulty difficulty, int coins) {
        super(MessageType.SHOW_PERSONAL_SCHOOL);
        this.school = school;
        this.nickname = nickname;
        this.trash = trash;
        this.difficulty = difficulty;
        this.coins = coins;
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
}
