package it.polimi.ingsw.model.character;

public enum CardEffect {
    BACCO,
    CICCIOPANZA,
    ALZABANDIERA,
    MAILMAN,
    OLDLADY,
    TAURO,
    JOKER,
    KNIGHT,
    FUNGAIOLO,
    MENESTRELLO,
    DAME,
    THIEF;

    private int xCardOnCard = 0;
    private boolean baccoPlayed;
    private boolean ciccioPanzaPlayed;
    private boolean knightPlayed;
    private boolean tauroPlayed;
    private boolean mailmanPlayed;
    private boolean alzabandieraPlayed;


    public int getXCardOnCard() {
        return xCardOnCard;
    }

    public void setXCardOnCard(int xCardOnSciura) {
        this.xCardOnCard = xCardOnSciura;
    }

    public boolean isBaccoPlayed() {
        return baccoPlayed;
    }

    public void setBaccoPlayed(boolean baccoPlayed) {
        this.baccoPlayed = baccoPlayed;
    }

    public boolean isKnightPlayed() {
        return knightPlayed;
    }

    public void setKnightPlayed(boolean knightPlayed) {
        this.knightPlayed = knightPlayed;
    }

    public boolean isCiccioPanzaPlayed() {
        return ciccioPanzaPlayed;
    }

    public void setCiccioPanzaPlayed(boolean ciccioPanzaPlayed) {
        this.ciccioPanzaPlayed = ciccioPanzaPlayed;
    }

    public boolean isTauroPlayed() {
        return tauroPlayed;
    }

    public void setTauroPlayed(boolean tauroPlayed) {
        this.tauroPlayed = tauroPlayed;
    }

    public boolean isMailmanPlayed() {
        return mailmanPlayed;
    }

    public void setMailmanPlayed(boolean mailmanPlayed) {
        this.mailmanPlayed = mailmanPlayed;
    }

    public boolean isAlzabandieraPlayed() {
        return alzabandieraPlayed;
    }

    public void setAlzabandieraPlayed(boolean alzabandierPlayed) {
        this.alzabandieraPlayed = alzabandierPlayed;
    }
}





