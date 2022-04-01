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


    public Table(ArrayList<CharacterCard> characterCardsOnTable, int coinsOnTable) {
        this.cloudNumber = new ArrayList<>(2);
        this.listOfIsland = new ArrayList<>(12);
        this.characterCardsOnTable = new ArrayList<>(3);

        Game gameMode = Game.getGameMode();     //gameMode come parametro (vedi CloudCard)
        assert gameMode != null;
        if(gameMode.equals(GameMode.TWOPLAYERS)) this.coinsOnTable = 18;
        if(gameMode.equals(GameMode.THREEPLAYERS)) this.coinsOnTable = 17;
        else this.coinsOnTable = 16;
    }

    public ArrayList<IslandCard> generateIslandCards(){
        TColour black = TColour.BLACK;
        TColour grey = TColour.GREY;
        TColour white = TColour.WHITE;
        Table t= new Table();  // sistemare costruttore!!!


        for(int s=1;s<13;s++) {                                      //id da 1 a 12
            t.getListOfIsland().add(new IslandCard(s));
        }
        return t;
    }

    public ArrayList<CloudCard> getCloudNumber() {
        return this.cloudNumber;
    }

    public ArrayList<IslandCard> getListOfIsland() {
        return this.listOfIsland;
    }

    public int getCoinsOnTable() {
        return this.coinsOnTable;
    }

    public ArrayList<CharacterCard> getCharacterCardsOnTable() {
        return characterCardsOnTable;
    }

    public ArrayList<IslandCard> joinIsland(){
        //da fare
        //va chiamata in moveMotherNature() dopo la buildTowerOnIsland()
        //va chiamata in changeTower()
    }

    public boolean checkListOfIsland(){  // Se ListOfIsland=3, finisce la partita?
        if(listOfIsland.size()==3)
            return true;
    }

    public PlayerNumber playerIsWinning(){  //calcola influenza torri sul tavolo e restituisce quello con più influenza
        // DA FARE
    }

}
