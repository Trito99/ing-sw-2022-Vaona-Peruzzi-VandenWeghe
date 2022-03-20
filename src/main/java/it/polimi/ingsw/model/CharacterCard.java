package it.polimi.ingsw.model;

public class CharacterCard {

    private int idCharacter;
    private int costCharacter;
    private CardEffect cardEffect;
    private boolean coinOnCard;

    public int getIdCharacter() {
        return this.idCharacter;
    }

    public int getCostCharacter() {
        return this.costCharacter;
    }

    public CardEffect getCardEffect() {
        return cardEffect;
    }

    public boolean getCoinOnCard() {    //o isCoinOnCard() ?
        return this.coinOnCard;
    }

    public void setCoinOnCard(boolean coinOnCard) {
        this.coinOnCard = coinOnCard;
    }

    public int increaseCostCharachter(){
            costCharacter = getCostCharacter();
            costCharacter = costCharacter + 1;
            return this.costCharacter;
    }
}
