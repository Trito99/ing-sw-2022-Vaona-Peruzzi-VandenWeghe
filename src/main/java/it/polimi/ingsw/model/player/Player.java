package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.school.*;
import it.polimi.ingsw.model.school.Prof;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.table.Table;

import java.util.GregorianCalendar;

import static java.util.Calendar.*;

/**
 * This class represents the player and contains all his information.
 */
public class Player {

    private String nickname;
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

    public void generateSchool(Table table, GameMode gameMode) {
        int i = 0, t = 0;
        /** tColor non può essere sempre lo stesso per tutti */
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
        personalSchool.getProfOfPlayer().add(new Prof(SColor.RED));
        personalSchool.getProfOfPlayer().add(new Prof(SColor.YELLOW));
        personalSchool.getProfOfPlayer().add(new Prof(SColor.PINK));
        personalSchool.getProfOfPlayer().add(new Prof(SColor.BLUE));

    }

    public School getPersonalSchool() {
        return personalSchool;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public GregorianCalendar getPlayerDate() {
        return playerDate;
    }

    public void setPlayerDate(GregorianCalendar playerDate) {
        this.playerDate = playerDate;
    }

    public int getAge() {
        return age;
    }

    public int setAge(GregorianCalendar playerDate){
        /** da modificare il calcolo della data */
        int currentYear = 2022;
        this.age = currentYear - playerDate.get(YEAR);
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

    public AssistantCard getTrash() {
        return trash;
    }

    public void setTrash(AssistantCard trash) {
        this.trash = trash;
    }

    public TColor getTColor() {
        return tColor;
    }

    public void setTColor(TColor tColor) {
        this.tColor = tColor;
    }

    public int getCoinScore() {
        return coinScore;
    }

    public void setCoinScore(int coinScore) {
        this.coinScore = coinScore;
    }

    public DeckAssistant getDeckOfPlayer(){
        return deckOfPlayer;
    }

    public void setDeckOfPlayer(DeckAssistant deckOfPlayer) {
        deckOfPlayer.generateAssistantDeck();
        this.deckOfPlayer = deckOfPlayer;
    }

    public int getInfluenceOnIsland() {
        return influenceOnIsland;
    }

    public void setInfluenceOnIsland(int influenceOnIsland) {
        this.influenceOnIsland = influenceOnIsland;
    }

    public boolean hasAlreadyPlayed() {
        return hasAlreadyPlayed;
    }

    public void setHasAlreadyPlayed(boolean hasAlreadyPlayed) {
        this.hasAlreadyPlayed = hasAlreadyPlayed;
    }
}
