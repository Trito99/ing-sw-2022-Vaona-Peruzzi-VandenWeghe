package it.polimi.ingsw.message;

import it.polimi.ingsw.model.table.Table;

/**
 * Extends server Message
 * Asks the Cloud card chosen by a player
 */
public class ChooseCloudCard extends ServerMessage {

    private Table table;

    public ChooseCloudCard(Table table) {
        super(MessageType.CHOOSE_CLOUD_CARD);
        this.table = table;
    }

    public Table getTable() {
        return table;
    }
}
