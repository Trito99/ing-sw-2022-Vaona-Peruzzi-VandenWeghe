package it.polimi.ingsw.message;

import it.polimi.ingsw.model.character.CharacterCard;

import java.util.ArrayList;

/**
 * Extends Server Message
 * Shows the player the coins and the Character cards on table
 */
public class ShowCoinAndCharacterCards extends ServerMessage{
    private int coins;
    private ArrayList<CharacterCard> characterCardsOnTable;

    public ShowCoinAndCharacterCards(int coins, ArrayList<CharacterCard> characterCardsOnTable) {
        super(MessageType.SHOW_COIN_AND_CHARACTER_CARDS);
        this.coins = coins;
        this.characterCardsOnTable = characterCardsOnTable;
    }

    public int getCoins() {
        return coins;
    }

    public ArrayList<CharacterCard> getCardsOnTable() {return characterCardsOnTable;}

}
