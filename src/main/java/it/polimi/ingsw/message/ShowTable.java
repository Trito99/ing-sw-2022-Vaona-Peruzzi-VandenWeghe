package it.polimi.ingsw.message;

import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.table.Table;

public class ShowTable extends ServerMessage{
    private Table table;
    private Difficulty difficulty;
    public ShowTable(Table table,Difficulty difficulty) {
        super(MessageType.SHOW_TABLE);
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
