package it.polimi.ingsw.message;

public class LoginRequest extends ClientMessage {
    private String gameId;

    public LoginRequest(String nickname, String gameId) {
        super(nickname, MessageType.LOGIN);
        this.gameId = gameId;
    }
    public String getGameId() {
        return gameId;
    }
}
