package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.school.School;

public class ShowPersonalSchool extends ServerMessage {
    private School school;
    private String nickname;
    AssistantCard trash;
    public ShowPersonalSchool(School school, String nickname, AssistantCard trash) {
        super(MessageType.SHOW_PERSONAL_SCHOOL);
        this.school = school;
        this.nickname = nickname;
        this.trash = trash;
    }

    public AssistantCard getTrash() { return trash; }

    public School getSchool() {
        return school;
    }

    public String getNickname(){ return nickname; }
}
