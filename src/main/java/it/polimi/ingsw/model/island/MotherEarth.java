package it.polimi.ingsw.model.island;

import java.util.ArrayList;

public class MotherEarth {

    private int position = 0;

    public int getPosition() {
        return position;
    }

    public void movePosition() {    //Le scelte brooo,
        position = getPosition();

        // ! MADRE NATURA PUò FARE UN NUMERO DIVERSO DI PASSI, SCELTI DAL GIOCATORE !
        // deve chiamare anche buildTowerOnIsland() da IslandCard
        //  DA IMPLEMENTARE e spostare in CONTROLLER

    }
}
