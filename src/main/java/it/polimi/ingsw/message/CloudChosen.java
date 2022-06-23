package it.polimi.ingsw.message;

public class CloudChosen extends ClientMessage {
    private int id;
    private String string;

    public CloudChosen(String nickname, int id, String string) {
        super(nickname, MessageType.CLOUD_CHOSEN);
        this.id = id;
        this.string = string;
    }

    public int getId() { return id; }
    public String getString() {
        return string;
    }
}
