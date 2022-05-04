package it.polimi.ingsw.message;

public enum MessageType {
    //messaggi del gioco
    LOGIN,
    LOGIN_RESULT,
    PLAYERS_NUMBER_AND_DIFFICULTY,

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
    PLAY_CHARACTER_CARD,
    PLAY_ASSISTANT_CARD,
    MOVE_STUDENTS,
    MOVE_MOTHER_EARTH,

    //messaggi intermediari
    WIN_PROF,
    WIN_TOWER,

    //altri
    OTHER_PLAYER_TURN,
    ERROR,
    WIN,
    LOSE;

}
