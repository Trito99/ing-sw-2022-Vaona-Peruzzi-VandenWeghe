package it.polimi.ingsw.model.game;


import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;
import java.util.Collections;

public class Game {

    //private int gameId;   servirebbe per partite multiple
    private GameMode gameMode;
    private final ArrayList<Player> listOfPlayers;
    private Difficulty difficulty;
    private Table table;
    private final ArrayList<Team> team;
    private final ArrayList<TColor> towerColors = new ArrayList<>();
    private final ArrayList<AssistantDeckName> assistantDeckNames = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Game() {
        this.gameMode = null;
        this.listOfPlayers = new ArrayList<>();
        this.difficulty = null;
        this.table = new Table();
        this.team = new ArrayList<>();
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public ArrayList<Player> getListOfPlayers(){
        return listOfPlayers;
    }

    public ArrayList<AssistantDeckName> getAssistantDeckNames(){
        return assistantDeckNames;
    }

    public ArrayList<TColor> getTowerColors(){
        return towerColors;
    }


    /** generates lists witch available tower colors and
     *
     */
    public void generateTowerColorsAndAssistantDeckName(){
        int playerNumber = initializePlayerNumber(gameMode);

        if(gameMode.equals(GameMode.COOP)){
            for(int twice=0; twice<2; twice++) {
                for (int i = 0; i < playerNumber - 2; i++)
                    towerColors.add(TColor.values()[i]);
            }
        }else {
            for (int i = 0; i < playerNumber; i++)
                towerColors.add(TColor.values()[i]);
        }
        for (int i = 0; i < playerNumber; i++)
            assistantDeckNames.add(AssistantDeckName.values()[i]);
    }

    public static int initializePlayerNumber(GameMode gameMode) {
        int playerNumber = 0;
        switch(gameMode){
            case TWOPLAYERS:
                playerNumber = 2;
                break;
            case THREEPLAYERS:
                playerNumber = 3;
                break;
            case COOP:
                playerNumber = 4;
                break;
        }
        return playerNumber;
    }

    /**
     *
     * @param nickname the nickname selected
     * @return Returns the Player from the list of players with the nickname selected
     */
    public Player getPlayer(String nickname){
        int indexPlayer = getPlayerListByNickname().indexOf(nickname);
        return listOfPlayers.get(indexPlayer);
    }

    /**
     *
     * @return Returns an array with the nicknames of the players of the match
     */
    public ArrayList<String> getPlayerListByNickname() {
        ArrayList<String> playerList = new ArrayList<>();
        for (Player player : listOfPlayers) {
            playerList.add(player.getNickname());
        }
        return playerList;
    }

    /**
     *
     * @param player Adds a player at the list of players of the game.
     */
    public void addPlayer(Player player) {
        if(listOfPlayers.size()<4){
            listOfPlayers.add(player);
        }
    }

    public void setGameMode(GameMode gameMode){
        this.gameMode = gameMode;
    }

    public Difficulty getDifficulty(){
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {this.difficulty = difficulty; }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ArrayList<Team> getTeam() {
        return team;
    }

    /**
     *
     * @param nickname is the nickname of the player that is playing his turn.
     * @return Return true if the game is finished. Otherwise, return false
     */
    public boolean gameIsFinished(String nickname) {
        Player activePlayer = getPlayer(nickname);
        Player teamLeader = null;                       /** The team leader is the player of the team that has the towers */
        if(gameMode!=GameMode.COOP) {
            return activePlayer.getDeckOfPlayer().getCardsInHand().isEmpty() || /** conditions to finish the game */
                    activePlayer.getPersonalSchool().getTowers().isEmpty() ||
                    table.getListOfIsland().size() <= 3;
        }
        else{
            for (Player p : listOfPlayers) {            /** Finds the team leader */
                if (activePlayer.getPersonalSchool().getTowers().size() == 0 && activePlayer.getTeamMate().equals(p.getNickname()))
                    teamLeader = p;
            }
            if (teamLeader==null)
                teamLeader = activePlayer;

            return teamLeader.getDeckOfPlayer().getCardsInHand().isEmpty() ||  /** conditions to finish the game */
                    teamLeader.getPersonalSchool().getTowers().isEmpty() ||
                    table.getListOfIsland().size() <= 3;
        }
    }

    /**
     *
     * @param nickname nickname of the player
     * @param decreaseValue number of coins to remove at the player
     */
    public void decreaseCoinScore(String nickname, int decreaseValue) {
        Player activePlayer = getPlayer(nickname);
        activePlayer.setCoinScore(activePlayer.getCoinScore() - decreaseValue);
    }

    /**
     *
     * @param assistantName  is the assistant card chosen by the Player
     * @param nickname nickname of the player that chooses the assistant card to play
     */
    public void playAssistantCard(String assistantName, String nickname){
        
        Player activePlayer = getPlayer(nickname);
        AssistantCard assistantCardPlayed = activePlayer.getAssistantCard(assistantName);
                
        activePlayer.setTrash(assistantCardPlayed);                                     /** update the TrashDeck of the player */
        activePlayer.getDeckOfPlayer().getCardsInHand().remove(assistantCardPlayed);    /** remove the assistant card played from the hand of the player */

    }

    /**
     *
     * @param islandCard the island where the student has to be moved
     * @param id id of the student to move
     * @param list list of students where there is the id selected (entry, character card ecc...)
     */
    public void moveStudentFromListToIsland(IslandCard islandCard, int id, ArrayList<Student> list){
        Student student = null;
        for (Student s : list) {
            if (id == s.getIdStudent())
                student = s;
        }
        islandCard.getStudentOnIsland().add(list.get(list.indexOf(student)));
        list.remove(list.get(list.indexOf(student)));
    }

    /**
     *
     * @param playerMoving player that select the student to move in the school
     * @param id id of the student selected
     * @param list list of students where there is the id selected (entry, character card ecc...)
     */
    public void moveStudentFromListToHall(Player playerMoving, int id, ArrayList<Student> list) {
        Student student = null;
        for (Student s : list) {
            if (id == s.getIdStudent())
                student = s;
        }
        switch(student.getsColour()) {
            case GREEN:
                playerMoving.getPersonalSchool().getGTable().add(student);
                getCoinFromStudentMove(playerMoving, playerMoving.getPersonalSchool().getGTable());
                list.remove(list.get(list.indexOf(student)));
                break;
            case RED:
                playerMoving.getPersonalSchool().getRTable().add(student);
                getCoinFromStudentMove(playerMoving, playerMoving.getPersonalSchool().getRTable());
                list.remove(list.get(list.indexOf(student)));
                break;
            case YELLOW:
                playerMoving.getPersonalSchool().getYTable().add(student);
                getCoinFromStudentMove(playerMoving, playerMoving.getPersonalSchool().getYTable());
                list.remove(list.get(list.indexOf(student)));
                break;
            case PINK:
                playerMoving.getPersonalSchool().getPTable().add(student);
                getCoinFromStudentMove(playerMoving, playerMoving.getPersonalSchool().getPTable());
                list.remove(list.get(list.indexOf(student)));
                break;
            case BLUE:
                playerMoving.getPersonalSchool().getBTable().add(student);
                getCoinFromStudentMove(playerMoving, playerMoving.getPersonalSchool().getBTable());
                list.remove(list.get(list.indexOf(student)));
                break;
        }
    }

    /**
     *
     * @param cardEffect the effect selected
     * @param nickname the player that plays the card
     * @param idS id of the student
     * @param idI id of the island
     * @param idSE id student from the entry
     * @param colorChosen if the effect selected needs a color
     */
    public void playCharacterCard(CardEffect cardEffect, String nickname, int idS, int idI, int idSE, SColor colorChosen) {

        Player activePlayer = getPlayer(nickname);
        CharacterCard characterCardPlayed = table.getCharacterCard(cardEffect);

        IslandCard islandCardChosen = null;
        for(IslandCard islandCard : getTable().getListOfIsland()){ /** Finds the island from the id */
            if(idI==islandCard.getIdIsland())
                islandCardChosen = islandCard;
        }


        switch (characterCardPlayed.getCardEffect()) {
            case ABBOT:
                moveStudentFromListToIsland(islandCardChosen,idS,characterCardPlayed.getStudentsOnCard());
                characterCardPlayed.getStudentsOnCard().add(getTable().getBag().get(0));
                getTable().getBag().remove(0);
                break;

            case HOST:
                characterCardPlayed.getCardEffect().setHostPlayed(true);
                activePlayer.getPersonalSchool().winProf(getListOfPlayers(), activePlayer, CardEffect.HOST);
                characterCardPlayed.getCardEffect().setHostPlayed(false);
                break;

            case HERALD:
                islandCardChosen.buildTowerOnIsland(getListOfPlayers(), characterCardPlayed.getCardEffect(), null, gameMode);
                getTable().joinIsland(getTable().getListOfIsland());
                break;

            case BEARER:
                characterCardPlayed.getCardEffect().setBearerPlayed(true);
                break;

            case CURATOR:
                islandCardChosen.setXCardCounter(islandCardChosen.getXCardCounter() + 1);
                islandCardChosen.setXCardOnIsland(true);
                characterCardPlayed.setXCardOnCard(characterCardPlayed.getXCardOnCard() - 1);
                break;

            case CENTAUR:
                characterCardPlayed.getCardEffect().setCentaurPlayed(true);
                break;

            case ACROBAT: /** exchange a student from the entry with a student on the Acrobat card */
                Student StudentChoice = null;
                Student toChange = null;
                for(Student student : characterCardPlayed.getStudentsOnCard()){
                    if(idS==student.getIdStudent())
                        StudentChoice = student;
                }
                for(Student student : activePlayer.getPersonalSchool().getEntry()){
                    if(idSE==student.getIdStudent())
                        toChange = student;
                }
                activePlayer.getPersonalSchool().getEntry().remove(toChange);
                activePlayer.getPersonalSchool().getEntry().add(StudentChoice);
                characterCardPlayed.getStudentsOnCard().remove(StudentChoice);
                characterCardPlayed.getStudentsOnCard().add(toChange);
                break;

            case KNIGHT:
                characterCardPlayed.getCardEffect().setKnightPlayed(true);
                break;

            case HERBALIST:
                colorChosen.lockColor();
                break;

            case BARD:
                Student studentEntry = null;
                Student studentHall = null;
                for(Student student : activePlayer.getPersonalSchool().getGTable()){
                    if(idS==student.getIdStudent()) {
                        studentHall = student;
                    }
                }
                for(Student student : activePlayer.getPersonalSchool().getRTable()){
                    if(idS==student.getIdStudent()) {
                        studentHall = student;
                    }
                }
                for(Student student : activePlayer.getPersonalSchool().getYTable()){
                    if(idS==student.getIdStudent()) {
                        studentHall = student;
                    }
                }
                for(Student student : activePlayer.getPersonalSchool().getPTable()){
                    if(idS==student.getIdStudent()) {
                        studentHall = student;
                    }
                }
                for(Student student : activePlayer.getPersonalSchool().getBTable()){
                    if(idS==student.getIdStudent()) {
                        studentHall = student;

                    }
                }
                for(Student student : activePlayer.getPersonalSchool().getEntry()){
                    if(idSE==student.getIdStudent()) {
                        studentEntry = student;
                    }
                }
                switch(studentHall.getsColour()){
                    case GREEN:
                        activePlayer.getPersonalSchool().getGTable().remove(studentHall);
                        break;
                    case RED:
                        activePlayer.getPersonalSchool().getRTable().remove(studentHall);
                        break;
                    case YELLOW:
                        activePlayer.getPersonalSchool().getYTable().remove(studentHall);
                        break;
                    case PINK:
                        activePlayer.getPersonalSchool().getPTable().remove(studentHall);
                        break;
                    case BLUE:
                        activePlayer.getPersonalSchool().getBTable().remove(studentHall);
                        break;
                }

                activePlayer.getPersonalSchool().getEntry().add(studentHall);
                moveStudentFromListToHall(activePlayer, idSE, activePlayer.getPersonalSchool().getEntry());
                activePlayer.getPersonalSchool().getEntry().remove(studentEntry);
                break;

            case COURTESAN:

                moveStudentFromListToHall(activePlayer,idS, characterCardPlayed.getStudentsOnCard());
                characterCardPlayed.getStudentsOnCard().add(table.getBag().get(0));
                table.getBag().remove(table.getBag().get(0));
                break;

            case JUNKDEALER:

                for (Player p : getListOfPlayers()) {
                    switch (colorChosen) {
                        case GREEN:
                            for (int j = 0; j < 3; j++) {
                                if (p.getPersonalSchool().getGTable().size() != 0) {
                                    table.getBag().add(p.getPersonalSchool().getGTable().get(p.getPersonalSchool().getGTable().size() - 1));
                                    p.getPersonalSchool().getGTable().remove(p.getPersonalSchool().getGTable().size() - 1);
                                }
                            }
                            break;
                        case RED:
                            for (int j = 0; j < 3; j++) {
                                if (p.getPersonalSchool().getRTable().size() != 0) {
                                    table.getBag().add(p.getPersonalSchool().getRTable().get(p.getPersonalSchool().getRTable().size() - 1));
                                    p.getPersonalSchool().getRTable().remove(p.getPersonalSchool().getRTable().size() - 1);
                                }
                            }
                            break;
                        case YELLOW:
                            for (int j = 0; j < 3; j++) {
                                if (p.getPersonalSchool().getYTable().size() != 0) {
                                    table.getBag().add(p.getPersonalSchool().getYTable().get(p.getPersonalSchool().getYTable().size() - 1));
                                    p.getPersonalSchool().getYTable().remove(p.getPersonalSchool().getYTable().size() - 1);
                                }
                            }
                            break;
                        case PINK:
                            for (int j = 0; j < 3; j++) {
                                if (p.getPersonalSchool().getPTable().size() != 0) {
                                    table.getBag().add(p.getPersonalSchool().getPTable().get(p.getPersonalSchool().getPTable().size() - 1));
                                    p.getPersonalSchool().getPTable().remove(p.getPersonalSchool().getPTable().size() - 1);
                                }
                            }
                            break;
                        case BLUE:
                            for (int j = 0; j < 3; j++) {
                                if (p.getPersonalSchool().getBTable().size() != 0) {
                                    table.getBag().add(p.getPersonalSchool().getBTable().get(p.getPersonalSchool().getBTable().size() - 1));
                                    p.getPersonalSchool().getBTable().remove(p.getPersonalSchool().getBTable().size() - 1);
                                }
                            }
                            break;
                    }
                }
                break;
        }
        Collections.shuffle(table.getBag());
    }

    /** If in expert mode, when you move a student in the third, sixth or ninth space of the hall you get a coin
     *
     * @param activePlayer player that moves the student
     * @param tableColor table where the player want to move the student
     */
    private void getCoinFromStudentMove(Player activePlayer, ArrayList<Student> tableColor) {
        
        if(getDifficulty().equals(Difficulty.EXPERTMODE) && (tableColor.size()==3)){
            activePlayer.setCoinScore(activePlayer.getCoinScore() + 1);
            getTable().setCoinsOnTable(getTable().getCoinsOnTable() - 1);
        }
        else if( getDifficulty().equals(Difficulty.EXPERTMODE) && (tableColor.size()==6)){
            activePlayer.setCoinScore(activePlayer.getCoinScore() + 1);
            getTable().setCoinsOnTable(getTable().getCoinsOnTable() - 1);
        }
        else if( getDifficulty().equals(Difficulty.EXPERTMODE) && (tableColor.size()==9)){
            activePlayer.setCoinScore(activePlayer.getCoinScore() + 1);
            getTable().setCoinsOnTable(getTable().getCoinsOnTable() - 1);
        }
    }



}
