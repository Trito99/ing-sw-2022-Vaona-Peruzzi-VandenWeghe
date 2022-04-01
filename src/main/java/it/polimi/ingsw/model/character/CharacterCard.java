package it.polimi.ingsw.model.character;

public class CharacterCard {

    private final int idCharacter;
    private int costCharacter;
    private final CardEffect cardEffect;
    private boolean coinOnCard;

    public CharacterCard (int idCharacter, int costCharacter, CardEffect cardEffect){
        this.idCharacter = idCharacter;
        this.costCharacter = costCharacter;
        this.cardEffect = cardEffect;
        this.coinOnCard = false;
    }

    public int getIdCharacter() {
        return idCharacter;
    }

    public int getCostCharacter() {
        return costCharacter;
    }

    public void setCostCharacter(int costCharacter) {
        this.costCharacter = costCharacter;
    }

    public CardEffect getCardEffect() {
        return cardEffect;
    }

    public boolean getCoinOnCard() {    //o isCoinOnCard() ?
        return coinOnCard;
    }

    public void setCoinOnCard(boolean coinOnCard) {
        this.coinOnCard = coinOnCard;
    }

    public void increaseCostCharacter(CharacterCard characterCard){
        characterCard.setCostCharacter(getCostCharacter() + 1);
    }
}
