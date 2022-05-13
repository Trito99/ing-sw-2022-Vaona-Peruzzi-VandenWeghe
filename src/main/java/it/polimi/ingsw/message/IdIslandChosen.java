package it.polimi.ingsw.message;


public class IdIslandChosen extends ClientMessage{
    private int id;

    public IdIslandChosen(String nickname, int id) {
        super(nickname, MessageType.ID_ISLAND_CHOSEN);
        this.id=id;
    }

    public int getId() {
        return id;
    }
}
