package it.polimi.ingsw.message;

/**
 * Extends Client Message
 * Notifies the student to move
 */
public class IdChosen extends ClientMessage{
    private int id;
    private boolean choice, none;
    private int index;

    public IdChosen(String nickname, int id, boolean choice, int index, boolean none) {
        super(nickname, MessageType.ID_CHOSEN);
        this.id = id;
        this.choice = choice;
        this.index = index;
        this.none = none;
    }

    public int getId() {
        return id;
    }

    public boolean getChoice() { return  choice; }

    public int getIndex() {
        return index;
    }

    public boolean getNone(){ return none;}
}
