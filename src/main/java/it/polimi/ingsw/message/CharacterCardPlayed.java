package it.polimi.ingsw.message;

/**
 * Extends Server Message
 * Asks to play a Character Card
 */
public class CharacterCardPlayed extends ClientMessage {
    private String cardNickname;
    private boolean choice;

    public CharacterCardPlayed(String nickname, String cardNickname, boolean choice) {
        super(nickname, MessageType.CHARACTER_CARD_PLAYED);
        this.cardNickname = cardNickname;
        this.choice = choice;
    }

    public String getCardNickname() {
        return cardNickname;
    }
    public boolean getChoice (){
        return  choice;
    }
}
