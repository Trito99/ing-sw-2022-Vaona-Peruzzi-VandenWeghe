package it.polimi.ingsw.message;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.table.Table;

public class ShowListOfIslands extends ServerMessage{
    private Table table;
    private Difficulty difficulty;
    public ShowListOfIslands(Table table,Difficulty difficulty) {
        super(MessageType.SHOW_LIST_OF_ISLANDS);
        this.table = table;
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty(){
        return difficulty;
    }
    public Table getTable() {
        return table;
    }

}
