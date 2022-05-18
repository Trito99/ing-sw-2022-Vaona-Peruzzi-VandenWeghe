package it.polimi.ingsw.message;


public class IdChosen extends ClientMessage{
    private int id;
    private boolean choice;

    public IdChosen(String nickname, int id, boolean choice) {
        super(nickname, MessageType.ID_CHOSEN);
        this.id = id;
        this.choice = choice;
    }

    public int getId() {
        return id;
    }

    public boolean getChoice() { return  choice; }
}
