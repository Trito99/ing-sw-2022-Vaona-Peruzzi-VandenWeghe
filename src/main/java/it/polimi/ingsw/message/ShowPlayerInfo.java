package it.polimi.ingsw.message;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColor;

import java.util.ArrayList;
import java.util.HashMap;

/** messaggio che contiene i dati del giocatore */

public class ShowPlayerInfo extends ServerMessage{
    private String nickname;
    private PlayerNumber playerNumber;
    private TColor tColor;
    private int influenceOnIsland;
    private School personalSchool;
    private DeckAssistant deckOfPlayer;
    private AssistantCard trash;
    private int coinScore;

    public ShowPlayerInfo(String nickname, PlayerNumber playerNumber, TColor tColor, int influenceOnIsland,
                          School personalSchool, DeckAssistant deckOfPlayer, AssistantCard trash, int coinScore) {
        super(MessageType.SHOW_PLAYER);
        this.nickname = nickname;
        this.playerNumber = playerNumber;
        this.tColor = tColor;
        this.influenceOnIsland = influenceOnIsland;
        this.personalSchool = personalSchool;
        this.deckOfPlayer = deckOfPlayer;
        this.trash = trash;
        this.coinScore = coinScore;
    }

    public String getNickname(){
        return nickname;
    }

    public PlayerNumber getPlayerNumber() {
        return playerNumber;
    }

    public TColor gettColor() {
        return tColor;
    }

    public int getInfluenceOnIsland() {
        return influenceOnIsland;
    }

    public School getPersonalSchool() {
        return personalSchool;
    }

    public DeckAssistant getDeckOfPlayer() {
        return deckOfPlayer;
    }

    public AssistantCard getTrash() {
        return trash;
    }

    public int getCoinScore() {
        return coinScore;
    }
}
