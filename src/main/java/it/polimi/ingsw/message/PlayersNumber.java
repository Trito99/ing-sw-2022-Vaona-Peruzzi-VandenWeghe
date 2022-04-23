package it.polimi.ingsw.message;

public class PlayersNumber extends ClientMessage {
    private int playersNumber;

    public PlayersNumber(String username, int playersNumber) {
        super(username,MessageType.PLAYER_NUMBER);
        this.playersNumber=playersNumber;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }
}
