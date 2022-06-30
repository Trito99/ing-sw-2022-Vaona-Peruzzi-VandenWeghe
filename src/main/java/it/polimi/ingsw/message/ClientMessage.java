package it.polimi.ingsw.message;

/**
 * Interface used for all the messages sent from the client
 */

public abstract class ClientMessage extends GeneralMessage {
    private String nickname;

    public ClientMessage(String nickname, MessageType message){
        super(message);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
