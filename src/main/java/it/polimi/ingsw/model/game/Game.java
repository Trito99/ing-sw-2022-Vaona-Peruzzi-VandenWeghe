package it.polimi.ingsw.model.game;


import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;

import java.util.ArrayList;

public class Game {

    //private int gameId;   servirebbe per partite multiple
    private GameMode gameMode;
    private ArrayList<Player> listOfPlayers;
    private State state;
    private Difficulty difficulty;
    private Table table;
    private ArrayList<Team> team;

    /**
     * Default constructor.
     */
    public Game() {
        this.gameMode = null;
        this.listOfPlayers = new ArrayList<>();
        this.state = null;
        this.difficulty = null;
        this.table = new Table();
        this.team = new ArrayList<>();

        /**table.generateIslandCards();
        table.generateMotherEarth();
        table.generateCloudNumber(gameMode);  */
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public ArrayList<Player> getListOfPlayers(){
        return listOfPlayers;
    }

    public Player getPlayer(String nickname){
        int indexPlayer = getPlayerListByNickname().indexOf(nickname);
        return listOfPlayers.get(indexPlayer);
    }

    public ArrayList<String> getPlayerListByNickname() {
        ArrayList<String> playerList = new ArrayList<>();
        for (int i = 0; i < listOfPlayers.size(); i++) {
            playerList.add(listOfPlayers.get(i).getNickname());
        }
        return playerList;
    }

    /** aggiunge giocatore alla lista giocatori */
    public void addPlayer(Player player) {
        if(listOfPlayers.size()<4){
            listOfPlayers.add(player);
        }
    }

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
    }

    public void setGameMode(GameMode gameMode){
        this.gameMode = gameMode;
    }

    public Difficulty getDifficulty(){
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {this.difficulty = difficulty; }

    /**
     * winnerIs deve rimanere all'interno di Game   (?)
     */
    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ArrayList<Team> getTeam() {
        return (ArrayList<Team>) team;
    }

    public boolean gameIsFinished(String nickname) {
        Player player = getPlayer(nickname);

        return player.getDeckOfPlayer().getCardsInHand().isEmpty() ||
                player.getPersonalSchool().getTower().isEmpty() ||
                table.getBag().isEmpty() ||
                table.getListOfIsland().size() == 3;
    }

    public void decreaseCoinScore(String nickname, int decreaseValue) {
        Player activePlayer = getPlayer(nickname);
        activePlayer.setCoinScore(activePlayer.getCoinScore() - decreaseValue);
    }

    /** AssistantName is the card chosen by the Player, nickname is the player that chooses the assistant card to play */
    public AssistantCard playAssistantCard(String assistantName, String nickname){
        
        Player activePlayer = getPlayer(nickname);
        AssistantCard assistantCardPlayed = activePlayer.getAssistantCard(assistantName);
                
        activePlayer.setTrash(assistantCardPlayed);
        activePlayer.getDeckOfPlayer().getCardsInHand().remove(assistantCardPlayed);

        return assistantCardPlayed;
    }

    public void moveStudentFromListToIsland(IslandCard islandCard, int id, ArrayList<Student> list){
        Student student = null;
        for (Student s : list) {
            if (id == s.getIdStudent())
                student = s;
        }
        islandCard.getStudentOnIsland().add(list.get(list.indexOf(student)));
        list.remove(list.get(list.indexOf(student)));
    }

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

    public void playCharacterCard(CardEffect cardEffect, String nickname, int idS, int idI, int idSE) {

        Player activePlayer = getPlayer(nickname);
        CharacterCard characterCardPlayed = table.getCharacterCard(cardEffect);

        IslandCard islandCardChosen = null;
        for(IslandCard islandCard : getTable().getListOfIsland()){
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
                islandCardChosen.buildTowerOnIsland(getListOfPlayers(), characterCardPlayed.getCardEffect(), null);
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

            case ACROBAT:
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
                SColor colorChosen = null;
                colorChosen.lockColor();
                break;

            case BARD:

                /** togli da entry e metti in hall */
                for (int i = 0; i < 3; i++) {
                    Student choice = null;
                    if (choice.getsColour().equals(SColor.GREEN)) {
                        activePlayer.getPersonalSchool().getGTable().add(choice);
                        getCoinFromStudentMove(activePlayer, activePlayer.getPersonalSchool().getGTable());
                        activePlayer.getPersonalSchool().getEntry().remove(choice);
                    } else if (choice.getsColour().equals(SColor.RED)) {
                        activePlayer.getPersonalSchool().getRTable().add(choice);
                        getCoinFromStudentMove(activePlayer, activePlayer.getPersonalSchool().getRTable());
                        activePlayer.getPersonalSchool().getEntry().remove(choice);
                    } else if (choice.getsColour().equals(SColor.YELLOW)) {
                        activePlayer.getPersonalSchool().getYTable().add(choice);
                        getCoinFromStudentMove(activePlayer, activePlayer.getPersonalSchool().getYTable());
                        activePlayer.getPersonalSchool().getEntry().remove(choice);
                    } else if (choice.getsColour().equals(SColor.PINK)) {
                        activePlayer.getPersonalSchool().getPTable().add(choice);
                        getCoinFromStudentMove(activePlayer, activePlayer.getPersonalSchool().getPTable());
                        activePlayer.getPersonalSchool().getEntry().remove(choice);
                    } else if (choice.getsColour().equals(SColor.BLUE)) {
                        activePlayer.getPersonalSchool().getBTable().add(choice);
                        getCoinFromStudentMove(activePlayer, activePlayer.getPersonalSchool().getBTable());
                        activePlayer.getPersonalSchool().getEntry().remove(choice);
                    }
                }
                /** togli da hall e metti in entry */
                for (int i = 0; i < 3; i++) {
                    Student choice = null;
                    //notify (observer)---->scelta 2 studenti
                    if (choice.getsColour().equals(SColor.GREEN)) {
                        activePlayer.getPersonalSchool().getGTable().remove(choice);
                        activePlayer.getPersonalSchool().getEntry().add(choice);
                    } else if (choice.getsColour().equals(SColor.RED)) {
                        activePlayer.getPersonalSchool().getRTable().remove(choice);
                        activePlayer.getPersonalSchool().getEntry().add(choice);
                    } else if (choice.getsColour().equals(SColor.YELLOW)) {
                        activePlayer.getPersonalSchool().getYTable().remove(choice);
                        activePlayer.getPersonalSchool().getEntry().add(choice);
                    } else if (choice.getsColour().equals(SColor.PINK)) {
                        activePlayer.getPersonalSchool().getPTable().remove(choice);
                        activePlayer.getPersonalSchool().getEntry().add(choice);
                    } else if (choice.getsColour().equals(SColor.BLUE)) {
                        activePlayer.getPersonalSchool().getBTable().remove(choice);
                        activePlayer.getPersonalSchool().getEntry().add(choice);
                    }
                }
                break;

            case COURTESAN:

                moveStudentFromListToHall(activePlayer,idS, characterCardPlayed.getStudentsOnCard());
                //pesco pedina da mettere sulla carta
                characterCardPlayed.getStudentsOnCard().add(table.getBag().get(0));
                table.getBag().remove(table.getBag().get(0));
                break;

            case JUNKDEALER:
                SColor colorChoice = null;
                //notify (observer)---->scelgo un colore
                for (Player p : getListOfPlayers()) {
                    if (colorChoice.equals(SColor.GREEN)) {
                        for (int j = 0; j < 3; j++) {
                            if (activePlayer.getPersonalSchool().getGTable().size() != 0)
                                activePlayer.getPersonalSchool().getGTable().remove(activePlayer.getPersonalSchool().getGTable().size() - 1);
                        }
                    } else if (colorChoice.equals(SColor.RED)) {
                        for (int j = 0; j < 3; j++) {
                            if (activePlayer.getPersonalSchool().getRTable().size() != 0)
                                activePlayer.getPersonalSchool().getRTable().remove(activePlayer.getPersonalSchool().getRTable().size() - 1);
                        }
                    } else if (colorChoice.equals(SColor.YELLOW)) {
                        for (int j = 0; j < 3; j++) {
                            if (activePlayer.getPersonalSchool().getYTable().size() != 0)
                                activePlayer.getPersonalSchool().getYTable().remove(activePlayer.getPersonalSchool().getYTable().size() - 1);
                        }
                    } else if (colorChoice.equals(SColor.PINK)) {
                        for (int j = 0; j < 3; j++) {
                            if (activePlayer.getPersonalSchool().getPTable().size() != 0)
                                activePlayer.getPersonalSchool().getPTable().remove(activePlayer.getPersonalSchool().getPTable().size() - 1);
                        }
                    } else if (colorChoice.equals(SColor.BLUE)) {
                        for (int j = 0; j < 3; j++) {
                            if (activePlayer.getPersonalSchool().getBTable().size() != 0)
                                activePlayer.getPersonalSchool().getBTable().remove(activePlayer.getPersonalSchool().getBTable().size() - 1);
                        }
                    }
                }
                break;
        }
    }

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
