package it.polimi.ingsw.message;

import it.polimi.ingsw.model.character.CardEffect;


public class ChooseColor extends ServerMessage {

    private CardEffect cardEffect;

    public ChooseColor(CardEffect effect) {
        super(MessageType.CHOOSE_COLOR_TO_BLOCK);
        this.cardEffect = effect;
    }

    public CardEffect getCardEffect() {
        return cardEffect;
    }
}
