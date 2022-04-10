package it.polimi.ingsw.model.character;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;

public enum CardEffect {
    MBRIACONE,
    CICCIOPANZA,
    ALZABANDIERA,
    CEPOSTAPERTE,
    SCIURA,
    TAURO,
    JOKER,
    SILVIO,
    FUNGAIOLO,
    MENESTRELLO,
    DAMA,
    TOSSICO;

    private int xCardOnCard = 0;
    private boolean mbriaconePlayed;
    private boolean ciccioPanzaPlayed;
    private boolean silvioPlayed;
    private boolean tauroPlayed;
    private boolean cePostaPerTePlayed;
    private boolean alzabandieraPlayed;


    public int getXCardOnCard() {
        return xCardOnCard;
    }

    public void setXCardOnCard(int xCardOnSciura) {
        this.xCardOnCard = xCardOnSciura;
    }

    public boolean isMbriaconePlayed() {
        return mbriaconePlayed;
    }

    public void setMbriaconePlayed(boolean mbriaconePlayed) {
        this.mbriaconePlayed = mbriaconePlayed;
    }

    public boolean isSilvioPlayed() {
        return silvioPlayed;
    }

    public void setSilvioPlayed(boolean silvioPlayed) {
        this.silvioPlayed = silvioPlayed;
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

    public boolean isCePostaPerTePlayed() {
        return cePostaPerTePlayed;
    }

    public void setCePostaPerTePlayed(boolean cePostaPerTePlayed) {
        this.cePostaPerTePlayed = cePostaPerTePlayed;
    }

    public boolean isAlzabandieraPlayed() {
        return alzabandieraPlayed;
    }

    public void setAlzabandieraPlayed(boolean alzabandierPlayed) {
        this.alzabandieraPlayed = alzabandierPlayed;
    }
}





