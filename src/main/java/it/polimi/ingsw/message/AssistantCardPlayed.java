package it.polimi.ingsw.message;

public class AssistantCardPlayed extends ClientMessage {
    private String cardNickname;

    public AssistantCardPlayed(String nickname, String cardNickname) {
        super(nickname, MessageType.ASSISTANTCARD_PLAYED);
        this.cardNickname=cardNickname;
    }

    public String getCardNickname() {
        return cardNickname;
    }
}
