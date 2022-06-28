package it.polimi.ingsw.model.character;

/**
 * Enumeration which identifies the effect of a certain Character Card
 */
public enum CardEffect {
    STANDARDMODE,
    ABBOT,
    HOST,
    HERALD,
    BEARER,
    CURATOR,
    CENTAUR,
    ACROBAT,
    KNIGHT,
    HERBALIST,
    BARD,
    COURTESAN,
    JUNKDEALER;


    private boolean hostPlayed;
    private boolean knightPlayed;
    private boolean centaurPlayed;
    private boolean bearerPlayed;

    public boolean isKnightPlayed() { return knightPlayed; }

    public void setKnightPlayed(boolean knightPlayed) { this.knightPlayed = knightPlayed; }

    public boolean isHostPlayed() {
        return hostPlayed;
    }

    public void setHostPlayed(boolean hostPlayed) {
        this.hostPlayed = hostPlayed;
    }

    public boolean isCentaurPlayed() {
        return centaurPlayed;
    }

    public void setCentaurPlayed(boolean centaurPlayed) { this.centaurPlayed = centaurPlayed; }

    public boolean isBearerPlayed() { return bearerPlayed; }

    public void setBearerPlayed(boolean bearerPlayed) {
        this.bearerPlayed = bearerPlayed;
    }

    /**
     * Resets the boolean values
     */
    public void setAllFalse(){
        hostPlayed = false;
        knightPlayed = false;
        centaurPlayed = false;
        bearerPlayed = false;
    }

}





