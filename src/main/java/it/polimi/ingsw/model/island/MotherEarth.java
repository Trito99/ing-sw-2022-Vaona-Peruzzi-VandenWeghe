package it.polimi.ingsw.model.island;

import java.util.ArrayList;

public class MotherEarth {

    private int position = 0;

    public int getPosition() {
        return this.position;
    }

    public int movePosition() {
        position = getPosition();

        // ! MADRE NATURA PUò FARE UN NUMERO DIVERSO DI PASSI, SCELTI DAL GIOCATORE !
        //  DA IMPLEMENTARE

        return position;
    }
}
