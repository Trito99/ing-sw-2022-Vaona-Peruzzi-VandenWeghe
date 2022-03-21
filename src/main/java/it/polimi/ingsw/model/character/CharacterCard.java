package it.polimi.ingsw.model.character;

public class CharacterCard {

    private final int idCharacter;
    private final int costCharacter;
    private final CardEffect cardEffect;
    private boolean coinOnCard;

    public CharacterCard (int idCharacter, int costCharacter, CardEffect cardEffect, boolean coinOnCard){
        this.idCharacter = idCharacter;
        this.costCharacter = costCharacter;
        this.cardEffect = cardEffect;
        this.coinOnCard = coinOnCard;
    }

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
        if (!this.coinOnCard) {
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
