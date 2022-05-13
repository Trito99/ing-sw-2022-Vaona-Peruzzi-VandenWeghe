package it.polimi.ingsw.message;

public class CloudChosen extends ClientMessage {
    private int id;

    public CloudChosen(String nickname, int id) {
        super(nickname, MessageType.CLOUD_CHOSEN);
        this.id = id;
    }

    public int getId() { return id; }
}
