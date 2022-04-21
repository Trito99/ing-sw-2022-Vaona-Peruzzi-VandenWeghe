package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.school.*;
import it.polimi.ingsw.model.school.Prof;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.table.Table;

import java.util.GregorianCalendar;

/**
 * This class represents the player and it contains all his information.
 */
public class Player {

    private final String nickname;
    private GregorianCalendar playerDate;
    private int age;               // CONTROLLARE LIBRERIA DATA
    private final PlayerNumber playerNumber;
    private TurnState turnState;
    private School personalSchool;
    private DeckAssistant deckOfPlayer;
    private AssistantCard trash;          //= Ultima carta nella pila degli scarti
    private int coinScore;
    private TColor tColor;
    private int influenceOnIsland;
    private boolean hasAlreadyPlayed; //= True se il player ha già giocato la trashCard nel suo turno


    /**
     * Default constructor.
     */
    public Player(TColor tColor, PlayerNumber playerNumber) {   //+ nickname e data
        nickname = new String(); //= nickname
        playerDate = null;
        age = 0;                 //= data
        this.playerNumber = playerNumber;      /** DA RIFARE COSTRUTTORE */
        influenceOnIsland = 0;
        personalSchool = new School();
        this.tColor = tColor;
        trash = null;
        coinScore = 0;
        hasAlreadyPlayed = false;
    }

    public School generateSchool(Table table, GameMode gameMode) {
        int i = 7, t=0;
        TColor tcolor = TColor.WHITE;
        switch (gameMode) {
            case TWOPLAYERS:
                i = 7;
                t = 8;
                break;
            case THREEPLAYERS:
                i = 9;
                t = 6;
                break;
            case COOP:
                i = 7;
                t = 8;
                break;
        }
        for (int s = 0; s < i; s++) {
            personalSchool.getEntry().add(table.getBag().get(s));
            table.getBag().remove(table.getBag().get(s));
        }
        for (int f = 0; f < t; f++) {
            personalSchool.addTower(f, tColor);
        }

        personalSchool.getProfOfPlayer().add(new Prof(SColor.GREEN));
        personalSchool.getProfOfPlayer().add(new Prof(SColor.YELLOW));
        personalSchool.getProfOfPlayer().add(new Prof(SColor.RED));
        personalSchool.getProfOfPlayer().add(new Prof(SColor.BLUE));
        personalSchool.getProfOfPlayer().add(new Prof(SColor.PINK));

        return personalSchool;
    }

    public String getNickname() {
        return nickname;
    }

    public GregorianCalendar getPlayerDate() {
        return playerDate;
    }

    public int getAge() {
        return age;
    }

    public PlayerNumber getPlayerNumber() {
        return playerNumber;
    }

    public TurnState getTurnState() {
        return turnState;
    }

    public void setTurnState(TurnState turnState) {
        this.turnState = turnState;
    }

    public School getPersonalSchool() {
        return personalSchool;
    }

    public AssistantCard getTrash() {
        return trash;
    }

    public void setTColour(TColor tColor) {
        this.tColor = tColor;
    }

    public TColor getTColour() {
        return tColor;
    }

    public int getCoinScore() {
        return coinScore;
    }

    public DeckAssistant getDeckOfPlayer(){
        return deckOfPlayer;
    }

    public void setDeckOfPlayer(DeckAssistant deckOfPlayer) {
        this.deckOfPlayer = deckOfPlayer;
    }

    public void setCoinScore(int coinScore) {
        this.coinScore = coinScore;
    }

    public int getInfluenceOnIsland() {
        return influenceOnIsland;
    }

    public void setInfluenceOnIsland(int influenceOnIsland) {
        this.influenceOnIsland = influenceOnIsland;
    }

    public void setTrash(AssistantCard trash) {
        this.trash = trash;
    }

    public boolean HasAlreadyPlayed() {
        return hasAlreadyPlayed;
    }

    public void setHasAlreadyPlayed(boolean hasAlreadyPlayed) {
        this.hasAlreadyPlayed = hasAlreadyPlayed;
    }
}
