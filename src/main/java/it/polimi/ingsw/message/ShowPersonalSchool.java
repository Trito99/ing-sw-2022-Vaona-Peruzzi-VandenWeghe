package it.polimi.ingsw.message;

import it.polimi.ingsw.model.school.School;

public class ShowPersonalSchool extends ServerMessage {
    private School school;
    private String nickname;
    public ShowPersonalSchool(School school, String nickname) {
        super(MessageType.SHOW_PERSONAL_SCHOOL);
        this.school = school;
        this.nickname = nickname;
    }

    public School getSchool() {
        return school;
    }

    public String getNickname(){ return nickname; }
}
