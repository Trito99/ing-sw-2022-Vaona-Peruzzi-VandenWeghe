package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.DeckAssistant;

public class ShowCoin  extends ServerMessage{
    private int coins;

    public ShowCoin(int coins) {
        super(MessageType.SHOW_COIN);
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

}
