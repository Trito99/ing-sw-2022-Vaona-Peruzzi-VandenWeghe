package it.polimi.ingsw.message;

import it.polimi.ingsw.model.character.CardEffect;

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
