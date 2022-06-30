package it.polimi.ingsw.message;

/**
 * Enumeration which identifies every kind of message between the client and the server
 */
public enum MessageType {
    /** messages of the game */
    LOGIN,
    LOGIN_RESULT,
    PLAYERS_NUMBER_AND_DIFFICULTY_CHOSEN,
    ASSISTANT_CARD_PLAYED,
    CHARACTER_CARD_PLAYED,
    CLOUD_CHOSEN,
    COLOR_CHOSEN,
    PLACE_AND_STUDENT_FOR_MOVE_CHOSEN,
    STEP_MOTHER_EARTH_CHOSEN,
    ID_CHOSEN,
    TOWER_COLOR_AND_DECK_CHOSEN,

    /** messagges of connection with the server */
    SUCCESSFUL_HOST,
    STRING_MESSAGE,
    WAITING_MESSAGE,
    LOBBY_SERVER_REQUEST,
    LOBBY_SERVER_INFO,

    /** Action request */
    SHOW_TABLE,
    SHOW_PERSONAL_SCHOOL,
    SHOW_ASSISTANT_DECK,
    SHOW_LIST_OF_ISLANDS,
    SHOW_COIN_AND_CHARACTER_CARDS,

    /** Action */
    PLAY_CHARACTER_CARD,
    PLAY_ASSISTANT_CARD,
    CHOOSE_CLOUD_CARD,
    CHOOSE_PLACE_AND_STUDENT_FOR_MOVE,
    CHOOSE_MOTHER_EARTH_STEPS,
    CHOOSE_ID,
    CHOOSE_COLOR_TO_BLOCK,
    CHOOSE_TOWER_COLOR_AND_DECK,

    /** others */
    ERROR,
    WIN,
    LOSE

}
