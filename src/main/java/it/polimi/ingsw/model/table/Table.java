package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.bag.Bag;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.TColour;
import it.polimi.ingsw.model.student.SColour;
import it.polimi.ingsw.model.student.Student;

import java.util.ArrayList;
import java.util.Collections;

public class Table {

    private ArrayList<CloudCard> cloudNumber;
    private ArrayList<IslandCard> listOfIsland;
    private ArrayList<CharacterCard> characterCardsOnTable;
    private int coinsOnTable;


    public void generateCloudNumber(Table table,GameMode gm){
        int x;
        int maxNumberOfStudents;

        if(gm.equals(GameMode.TWOPLAYERS)){
            x=2;
            maxNumberOfStudents = 3;
        }else if(gm.equals(GameMode.THREEPLAYERS)){
            x=3;
            maxNumberOfStudents = 4;
        }else{
            x=4;
            maxNumberOfStudents = 3;
        }
        for(int i=0;i<x;i++){
            table.getCloudNumber().add(new CloudCard(i, maxNumberOfStudents));
        }
    }


    public void generateIslandCards(Table table){
        for(int s=1;s<13;s++) {
            table.getListOfIsland().add(new IslandCard(s));
        }
    }
    public ArrayList<CloudCard> getCloudNumber() {
        return cloudNumber;
    }

    public ArrayList<IslandCard> getListOfIsland() {
        return listOfIsland;
    }

    public int getCoinsOnTable() {
        return coinsOnTable;
    }

    public ArrayList<CharacterCard> getCharacterCardsOnTable() {
        return characterCardsOnTable;
    }

    public ArrayList<IslandCard> joinIsland(){
        //da fare
        //va chiamata in moveMotherNature() dopo la buildTowerOnIsland()
        //va chiamata in changeTower()
    }

    /* public boolean checkListOfIsland(){  // Se ListOfIsland=3, finisce la partita?
        if(listOfIsland.size()==3)
            return true;
    } */

    public PlayerNumber playerIsWinning(){  //calcola influenza torri sul tavolo e restituisce quello con più influenza
        // DA FARE
    }

}
