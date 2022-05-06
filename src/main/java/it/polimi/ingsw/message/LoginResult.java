package it.polimi.ingsw.message;

import java.util.GregorianCalendar;

/** mostra se il login è avvenuto correttamente*/

public class LoginResult extends ServerMessage{
    private boolean joined;
    private String nickname;
    private String gameId;
    private GregorianCalendar playerDate;

    public LoginResult(String nickname, String gameId, GregorianCalendar playerDate, boolean joined) {
        super(MessageType.LOGIN_RESULT);
        this.joined = joined;
        this.nickname = nickname;
        this.gameId = gameId;
        this.playerDate = playerDate;
    }

    public boolean wasJoined(){
        return joined;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGameId() {
        return gameId;
    }

    public GregorianCalendar getPlayerDate(){
        return playerDate;
    }
}
