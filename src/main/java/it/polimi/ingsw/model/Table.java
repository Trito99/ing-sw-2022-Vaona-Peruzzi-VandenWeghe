package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Table {

    private ArrayList<CloudCard> cloudNumber = new ArrayList<>(2);
    private ArrayList<IslandCard> idIsland = new ArrayList<>(12);
    private int coinsOnTable;

    public ArrayList<CloudCard> getCloudNumber() {
        return this.cloudNumber;
    }

    public ArrayList<IslandCard> getIdIsland() {
        return this.idIsland;
    }

    public int getCoinsOnTable() {
        return this.coinsOnTable;
    }

    public ArrayList<idIsland> joinIsland(){
        //da fare
    }

    public boolean checkListOfIsland(){

    }

}
