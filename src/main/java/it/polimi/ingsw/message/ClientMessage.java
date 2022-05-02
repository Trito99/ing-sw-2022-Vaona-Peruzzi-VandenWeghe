package it.polimi.ingsw.message;


import it.polimi.ingsw.model.game.GameMode;

/** interfaccia fornita per tutti i messaggi inviati dal client */

public abstract class ClientMessage extends GeneralMessage {
    private String nickname;

    public ClientMessage(String nickname, MessageType message){
        super(message);
        this.nickname = nickname;
    }

    public ClientMessage(MessageType message) {
        super(message);
    }

    public String getNickname() {
        return nickname;
    }
}
