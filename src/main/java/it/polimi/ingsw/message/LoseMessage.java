package it.polimi.ingsw.message;

public class LoseMessage extends ServerMessage{

    private String nickname;
    public LoseMessage(String nickname) {
        super(MessageType.LOSE);
        this.nickname=nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
