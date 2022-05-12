package it.polimi.ingsw.message;

public class CloudCardChosen extends ClientMessage {
    private int id;

    public CloudCardChosen(String nickname, int id) {
        super(nickname, MessageType.CLOUDCARD_CHOSEN);
        this.id = id;
    }

    public int getId() { return id; }
}
