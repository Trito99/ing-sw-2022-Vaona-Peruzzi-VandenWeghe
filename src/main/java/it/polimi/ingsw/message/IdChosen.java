package it.polimi.ingsw.message;


public class IdChosen extends ClientMessage{
    private int id;
    private boolean choice;
    private int index;

    public IdChosen(String nickname, int id, boolean choice, int index) {
        super(nickname, MessageType.ID_CHOSEN);
        this.id = id;
        this.choice = choice;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public boolean getChoice() { return  choice; }

    public int getIndex() {
        return index;
    }
}
