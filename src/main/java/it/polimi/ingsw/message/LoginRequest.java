package it.polimi.ingsw.message;

import java.util.GregorianCalendar;

/**
 * Extends Client Message
 * Notifies the login from a certain user
 */
public class LoginRequest extends ClientMessage {
    private String gameId;
    private GregorianCalendar playerDate;

    public LoginRequest(String nickname, String gameId, GregorianCalendar playerDate) {
        super(nickname, MessageType.LOGIN);
        this.gameId = gameId;
        this.playerDate = playerDate;
    }
    public String getGameId() {
        return gameId;
    }

    public GregorianCalendar getPlayerDate() {
        return playerDate;
    }
}
