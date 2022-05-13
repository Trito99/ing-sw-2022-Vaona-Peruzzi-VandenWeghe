package it.polimi.ingsw.message;

import it.polimi.ingsw.model.table.Table;

public class ShowTable extends ServerMessage{
    private Table table;
    public ShowTable(Table table) {
        super(MessageType.SHOW_TABLE);
        this.table = table;
    }

    public Table getTable() {
        return table;
    }
}
