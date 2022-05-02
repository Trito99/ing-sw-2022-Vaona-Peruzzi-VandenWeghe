package it.polimi.ingsw.message;

public enum MessageType {
    //messaggi del gioco
    LOGIN,
    LOGIN_RESULT,
    PLAYER_NUMBER,

    //messaggi di connessione al server
    SUCCESSFUL_HOST,
    STRING_MESSAGE,

    //messaggi del turno
    START_TURN,
    END_TURN,

    //setup

    //richiesta di azioni es. tutte le show
    ASK_PLAYER,
    SHOW_PLAYER,
    SHOW_PLAYER_INFLUENCE,

    //azioni

    //messaggi intermediari

    //altri
    OTHER_PLAYER_TURN,
    ERROR,
    WIN,
    LOSE;

}
