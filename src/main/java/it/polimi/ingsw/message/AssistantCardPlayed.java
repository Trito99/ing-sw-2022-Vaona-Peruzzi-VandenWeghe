package it.polimi.ingsw.message;

/**
 * Extends Client Message
 * Notifies to play an Assistant Card
 */
public class AssistantCardPlayed extends ClientMessage {
    private String cardNickname;

    public AssistantCardPlayed(String nickname, String cardNickname) {
        super(nickname, MessageType.ASSISTANT_CARD_PLAYED);
        this.cardNickname=cardNickname;
    }

    public String getCardNickname() {
        return cardNickname;
    }
}
