package it.polimi.ingsw.message;

/** mostra se il login è avvenuto correttamente*/

public class LoginResult extends ServerMessage{
    private boolean joined;
    private String nickname;
    private String gameId;

    public LoginResult(String nickname, String gameId, boolean joined) {
        super(MessageType.LOGIN_RESULT);
        this.joined = joined;
        this.nickname = nickname;
        this.gameId = gameId;
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
}
