package it.polimi.ingsw.model.character;

public enum CardEffect {
    EASYMODE,
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
    private boolean centauroPlayed;
    private boolean latorePlayed;


    public int getXCardOnCard() {
        return xCardOnCard;
    }

    public void setXCardOnCard(int xCardOnCard) {
        this.xCardOnCard = xCardOnCard;
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
        return centauroPlayed;
    }

    public void setCentauroPlayed(boolean centauroPlayed) {
        this.centauroPlayed = centauroPlayed;
    }

    public boolean isLatorePlayed() {
        return latorePlayed;
    }

    public void setLatorePlayed(boolean latorePlayed) {
        this.latorePlayed = latorePlayed;
    }

}





