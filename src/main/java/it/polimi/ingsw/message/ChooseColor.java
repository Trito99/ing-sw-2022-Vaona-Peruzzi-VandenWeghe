package it.polimi.ingsw.message;

import it.polimi.ingsw.model.character.CardEffect;

/**
 * Extends Server Message
 * Asks the color to block chosen by the player
 */
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
