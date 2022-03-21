package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;

import java.util.ArrayList;

public class Table {

    private ArrayList<CloudCard> cloudNumber;
    private ArrayList<IslandCard> idIsland;
    private ArrayList<CharacterCard> characterCardsOnTable;
    private int coinsOnTable;

    public Table(ArrayList<CharacterCard> characterCardsOnTable, int coinsOnTable) {
        this.cloudNumber = new ArrayList<>(2);
        this.idIsland = new ArrayList<>(12);
        this.characterCardsOnTable = new ArrayList<>(3);

        Game gameMode = Game.getGameMode();
        assert gameMode != null;
        if(gameMode.equals(GameMode.TWOPLAYERS)) this.coinsOnTable = 18;
        if(gameMode.equals(GameMode.THREEPLAYERS)) this.coinsOnTable = 17;
        else this.coinsOnTable = 16;
    }

    public ArrayList<CloudCard> getCloudNumber() {
        return this.cloudNumber;
    }

    public ArrayList<IslandCard> getIdIsland() {
        return this.idIsland;
    }

    public int getCoinsOnTable() {
        return this.coinsOnTable;
    }

    public ArrayList<CharacterCard> getCharacterCardsOnTable() {
        return characterCardsOnTable;
    }

    public ArrayList<IslandCard> joinIsland(){
        //da fare
    }

    public boolean checkListOfIsland(){

    }

}
