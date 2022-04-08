package it.polimi.ingsw.model.character;

public class CharacterCard {

    private final int idCharacter;
    private int costCharacter;
    private final CardEffect cardEffect;
    private boolean coinOnCard;
    private boolean neverUsed;      //se è stato già usato un personaggio, aumenta il costo
    private boolean playedMbriacone;
    private boolean playedCiccioPanza;
    private boolean playedAlzabandiera;

    public CharacterCard (int idCharacter, int costCharacter, CardEffect cardEffect){
        this.idCharacter = idCharacter;
        this.costCharacter = costCharacter;
        this.cardEffect = cardEffect;
        this.coinOnCard = false;
        this.neverUsed = true;

        this.playedCiccioPanza = false;

    }

    public int getIdCharacter() {
        return idCharacter;
    }

    public boolean isNeverUsed() {
        return neverUsed;
    }

    public void setNeverUsed(boolean neverUsed) {
        this.neverUsed = neverUsed;
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

    public void setPlayedCiccioPanza(boolean playedCiccioPanza) {
        this.playedCiccioPanza = playedCiccioPanza;
    }


}
