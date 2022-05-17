package it.polimi.ingsw.message;

import it.polimi.ingsw.model.character.CardEffect;

import javax.smartcardio.Card;

public class CharacterCardPlayed extends ClientMessage {
    private String cardNickname;

    public CharacterCardPlayed(String nickname, String cardNickname) {
        super(nickname, MessageType.CHARACTER_CARD_PLAYED);
        this.cardNickname=cardNickname;
    }

    public String getCardNickname() {
        return cardNickname;
    }
}
