package it.polimi.ingsw.model.character;

public enum CardEffect {
    ABATE,
    OSTE,
    ARALDO,
    LATORE,
    CURATRICE,
    CENTAURO,
    SALTIMBANCO,
    CAVALIERE,
    ERBORISTA,
    BARDO,
    CORTIGIANA,
    RIGATTIERE;

    private int xCardOnCard = 0;
    private boolean ostePlayed;
    private boolean cavalierePlayed;
    private boolean tauroPlayed;
    private boolean latorePlayed;


    public int getXCardOnCard() {
        return xCardOnCard;
    }

    public void setXCardOnCard(int xCardOnSciura) {
        this.xCardOnCard = xCardOnSciura;
    }

    public boolean isCavalierePlayed() {
        return cavalierePlayed;
    }

    public void setCavalierePlayed(boolean knightPlayed) {
        this.cavalierePlayed = knightPlayed;
    }

    public boolean isOstePlayed() {
        return ostePlayed;
    }

    public void setOstePlayed(boolean ostePlayed) {
        this.ostePlayed = ostePlayed;
    }

    public boolean isCentauroPlayed() {
        return tauroPlayed;
    }

    public void setCentauroPlayed(boolean tauroPlayed) {
        this.tauroPlayed = tauroPlayed;
    }

    public boolean isLatorePlayed() {
        return latorePlayed;
    }

    public void setLatorePlayed(boolean latorePlayed) {
        this.latorePlayed = latorePlayed;
    }

}





