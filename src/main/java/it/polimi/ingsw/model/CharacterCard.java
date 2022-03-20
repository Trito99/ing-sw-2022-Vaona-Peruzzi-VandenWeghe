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
        if (this.coinOnCard == false) {
            this.coinOnCard = true;
        }
        else return;
    }

    public int increaseCostCharachter(){
        this.costCharacter = getCostCharacter();
        this.costCharacter = costCharacter + 1;
        return this.costCharacter;
    }
}
