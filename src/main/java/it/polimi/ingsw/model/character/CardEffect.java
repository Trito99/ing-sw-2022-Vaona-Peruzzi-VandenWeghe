package it.polimi.ingsw.model.character;

public enum CardEffect {
    STANDARDMODE,
    ABBOT, //ABATE
    HOST, //OSTE
    HERALD, //ARALDO
    BEARER, //LATORE
    CURATOR, //CURATRICE
    CENTAUR, //CENTAURO
    ACROBAT, //SALTIMBANCO
    KNIGHT, //CAVALIERE
    HERBALIST, //ERBORISTA
    BARD, //BARDO
    COURTESAN, //CORTIGIANA,
    JUNKDEALER; //RIGATTIERE


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

    public void setAllFalse(){
        hostPlayed = false;
        knightPlayed = false;
        centaurPlayed = false;
        bearerPlayed = false;
    }

}





