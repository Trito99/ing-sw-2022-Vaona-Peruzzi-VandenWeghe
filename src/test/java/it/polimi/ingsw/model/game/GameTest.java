package it.polimi.ingsw.model.game;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.character.DeckCharacter;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.model.island.IslandTest.*;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    
    Game game;

    @Test
    @BeforeEach
    public void init(){
        game = new Game();
    }

    public void showPersonalSchool(School school, String nickname, AssistantCard trash) {

        out.print("\n****School of "+ nickname + "**** ");
        out.print("\nEntry: ");
        for(Student s : school.getEntry()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | " );
        }
        out.print(ANSI_GREEN +"\n\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.GREEN)+ ANSI_GREEN + " Green Table: " + ANSI_RESET);
        for(Student s : school.getGTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }

        out.print(ANSI_RED +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.RED)+ ANSI_RED + " Red Table: " + ANSI_RESET);
        for(Student s : school.getRTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }

        out.print(ANSI_YELLOW +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.YELLOW)+ ANSI_YELLOW + " Yellow Table: " + ANSI_RESET);
        for(Student s : school.getYTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }

        out.print(ANSI_PINK +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.PINK)+ ANSI_PINK + " Pink Table: " + ANSI_RESET);
        for(Student s : school.getPTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }

        out.print(ANSI_BLUE +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.BLUE)+ ANSI_BLUE + " Blue Table: " + ANSI_RESET);
        for(Student s : school.getBTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }

        out.print("\n\nTowers: ");
        for(Tower t : school.getTowers()){
            out.print(getTowerAnsiColor(t) + "T" + ANSI_RESET + " | ");
        }
        out.print("(" + school.getTowers().size() + " towers remained)");

        out.print("\n\nTrash Card: ");
        if (trash!=null)
            out.print(trash.getAssistantName() +" (TurnValue: "+ trash.getTurnValue() + ", StepME: " + trash.getStepMotherEarth()+")");

        out.println("\n----------------------------------------------");
    }

    private String getTowerAnsiColor(Tower tower) {
        switch (tower.getTColour()) {
            case BLACK:
                return ANSI_BLACK;
            case GREY:
                return ANSI_GREY;
            default:
                return ANSI_RESET;
        }
    }
    private String getStudentAnsiColor(Student student) {
        switch (student.getSColor()) {
            case GREEN:
                return ANSI_GREEN;
            case YELLOW:
                return ANSI_YELLOW;
            case RED:
                return ANSI_RED;
            case PINK:
                return ANSI_PINK;
            case BLUE:
                return ANSI_BLUE;
            default:
                return ANSI_RESET;
        }
    }

    @Test
    void gameIsFinished() {
        Player player1 = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        player1.setNickname("Gino");
        game.addPlayer(player1);
        Table table = new Table();
        DeckAssistant deckAssistant1 = new DeckAssistant(AssistantDeckName.WIZARD1);
        table.generateIslandCards();

        game.setTable(table);
        game.getPlayer("Gino").setDeckOfPlayer(deckAssistant1);
        game.getPlayer("Gino").getDeckOfPlayer().getCardsInHand().clear();

        game.getTable().getBag().add(new Student(0, SColor.GREEN));

        game.getPlayer("Gino").getPersonalSchool().getTowers().add(new Tower(TColor.WHITE));
        game.getPlayer("Gino").getPersonalSchool().getTowers().add(new Tower(TColor.WHITE));
        game.getPlayer("Gino").getDeckOfPlayer().getCardsInHand().add(new AssistantCard("lion",3,4));

        assertEquals(false, game.gameIsFinished("Gino"));


        game.playAssistantCard("lion", "Gino");
        assertNotNull(game.getPlayer("Gino").getTrash());
        assertEquals("lion", game.getPlayer("Gino").getTrash().getAssistantName());
        assertEquals(true, game.gameIsFinished("Gino"));  /** Control if return true when the player has no more card in his hand */

        game.getPlayer("Gino").getDeckOfPlayer().getCardsInHand().add(new AssistantCard("lion",3,4));

        game.getPlayer("Gino").getPersonalSchool().removeTower();
        assertEquals(false, game.gameIsFinished("Gino"));
        game.getPlayer("Gino").getPersonalSchool().removeTower();
        assertEquals(true, game.gameIsFinished("Gino")); /** Control if return true when a player finishes his tower */

        game.getPlayer("Gino").getPersonalSchool().addTower(TColor.WHITE);

        DeckAssistant deckAssistant2 = new DeckAssistant(AssistantDeckName.WIZARD2);
        DeckAssistant deckAssistant3 = new DeckAssistant(AssistantDeckName.WIZARD3);
        DeckAssistant deckAssistant4 = new DeckAssistant(AssistantDeckName.WIZARD4);

        player1.setTeamMate("Pino");
        player1.setTeamLeader(true);
        Player player2 = new Player(TColor.WHITE, PlayerNumber.PLAYER2);
        player2.setNickname("Pino");
        player2.setTeamMate("Gino");
        player2.setTeamLeader(false);
        Team team1 = new Team(player1, player2, TColor.WHITE);


        Player player3 = new Player(TColor.BLACK, PlayerNumber.PLAYER3);
        player3.setNickname("Albano");
        player3.setTeamMate("Romina");
        player3.setTeamLeader(true);

        Player player4 = new Player(TColor.BLACK, PlayerNumber.PLAYER4);
        player4.setNickname("Romina");
        player4.setTeamMate("Albano");
        player4.setTeamLeader(false);
        Team team2 = new Team(player3,player4,TColor.BLACK);

        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.getTeams().add(team1);
        game.getTeams().add(team2);
        game.setGameMode(GameMode.COOP);

        game.getPlayer("Pino").setDeckOfPlayer(deckAssistant2);
        game.getPlayer("Pino").getDeckOfPlayer().getCardsInHand().clear();
        game.getPlayer("Albano").setDeckOfPlayer(deckAssistant3);
        game.getPlayer("Albano").getDeckOfPlayer().getCardsInHand().clear();
        game.getPlayer("Romina").setDeckOfPlayer(deckAssistant4);
        game.getPlayer("Romina").getDeckOfPlayer().getCardsInHand().clear();

        game.getPlayer("Pino").getDeckOfPlayer().getCardsInHand().add(new AssistantCard("Cat",3,4));
        game.getPlayer("Albano").getDeckOfPlayer().getCardsInHand().add(new AssistantCard("Turtle",3,4));
        game.getPlayer("Romina").getDeckOfPlayer().getCardsInHand().add(new AssistantCard("Octopus",3,4));

        game.getPlayer("Albano").getPersonalSchool().addTower(TColor.BLACK);

        assertEquals(false, game.gameIsFinished(player1.getNickname()));

        game.playAssistantCard("lion", "Gino");
        for(AssistantCard a : player1.getDeckOfPlayer().getCardsInHand())
            System.out.println("Gino: " + a.getAssistantName());
        for(AssistantCard a : player2.getDeckOfPlayer().getCardsInHand())
            System.out.println("Pino: " + a.getAssistantName());
        for(AssistantCard a : player3.getDeckOfPlayer().getCardsInHand())
            System.out.println("Albano: " + a.getAssistantName());
        for(AssistantCard a : player4.getDeckOfPlayer().getCardsInHand())
            System.out.println("Romina: " + a.getAssistantName());

        assertEquals(true, game.gameIsFinished(player1.getNickname()));


        /** MANCA CONTROLLO BAG VUOTA E 3 GRUPPI DI ISOLE */
    }

    @Test
    public void playAssistantCardTest(){
        
        Player player = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        player.setNickname("Gino");
        game.addPlayer(player);
        DeckAssistant deckAssistant = new DeckAssistant(AssistantDeckName.WIZARD1);
        game.getPlayer("Gino").setDeckOfPlayer(deckAssistant);
        game.playAssistantCard("lion", "Gino");
        assertEquals("lion", player.getTrash().getAssistantName());
        assertEquals(9,player.getDeckOfPlayer().getCardsInHand().size());
        assertNotEquals("lion", player.getDeckOfPlayer().getCardsInHand().get(0).getAssistantName());
    }

    @Test
    void decreaseCoinScoreTest(){
        Player player = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        player.setNickname("Gino");
        player.setCoinScore(15);
        game.addPlayer(player);

        game.decreaseCoinScore("Gino", 5);
        assertEquals(10, player.getCoinScore());
    }

    @Test
    void getPlayerTest(){
        
        Player player1 = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        player1.setNickname("Gino");
        Player player2 = new Player(TColor.WHITE, PlayerNumber.PLAYER2);
        player2.setNickname("Federico");
        Player player3 = new Player(TColor.BLACK, PlayerNumber.PLAYER3);
        player3.setNickname("Tristan");
        Player player4 = new Player(TColor.BLACK, PlayerNumber.PLAYER4);
        player4.setNickname("Chiara");
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        assertEquals("Federico", game.getPlayer("Federico").getNickname());
        assertEquals(0, game.getPlayer("Tristan").getCoinScore());
        assertEquals(TColor.BLACK, game.getPlayer("Chiara").getTColor());

    }

    @RepeatedTest(100)
    void moveStudentFromListToIslandTest(){
        Random rn = new Random();
        for (int index = 0; index < 2; index++) { /** For the first two GameModes */
            game.getTable().getBag().clear();
            game.getTable().getListOfIsland().clear();
            game.getTable().getCloudNumber().clear();
            game.getTable().generateIslandCards();
            game.getTable().generateMotherEarth();
            game.getTable().generateBagInit();
            game.getTable().extractStudentsInit();
            game.getTable().addFinalStudents();
            game.getTable().generateCloudNumber(GameMode.values()[index]);
            game.getListOfPlayers().clear();
            game.setGameMode(GameMode.values()[index]);
            for (int i = 0; i < index + 2; i++) {
                game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                game.getListOfPlayers().get(i).generateSchool(game.getTable(), GameMode.values()[index]);
            }
            int r=rn.nextInt(game.getTable().getCloudNumber().get(0).getNumberOfSpaces());
            int pos, is;
            for (int i = 0; i < index + 2; i++){
                ArrayList<Integer> id = new ArrayList<>();
                for(int s=0;s<game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size();s++)
                    id.add(game.getListOfPlayers().get(i).getPersonalSchool().getEntry().get(s).getIdStudent());
                assertEquals(id.size(),game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size());  /** Controls if the size of the new array of Id is the same after every cicle */
                for(int s=0;s<r;s++) {
                    pos = rn.nextInt(game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size() - 1);
                    is = rn.nextInt(11) + 1;
                    game.moveStudentFromListToIsland(game.getTable().getListOfIsland().get(is), id.get(pos), game.getListOfPlayers().get(i).getPersonalSchool().getEntry());
                    assertEquals(id.size()-1,game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size());  /** Controls if the size of the Entry is reduced by one after every cicle */
                    assertEquals(id.get(pos),game.getTable().getListOfIsland().get(is).getStudentOnIsland().get(game.getTable().getListOfIsland().get(is).getStudentOnIsland().size()-1).getIdStudent());/** Controls if the id of the student added is the same of the id selected in MoveStudentOnIsland  */
                    for(Student student:game.getListOfPlayers().get(i).getPersonalSchool().getEntry()){
                        assertNotEquals(id.get(pos),student.getIdStudent());
                    }
                    id.remove(id.get(pos));
                }
            }

        }
    }

    @RepeatedTest(100)
    void moveStudentFromListToHallTest(){
        Random rn = new Random();
        for (int index = 0; index < 2; index++) { /** For the first two GameModes */
            game.getTable().getBag().clear();
            game.getTable().getListOfIsland().clear();
            game.getTable().getCloudNumber().clear();
            game.getTable().generateIslandCards();
            game.getTable().generateMotherEarth();
            game.getTable().generateBagInit();
            game.getTable().extractStudentsInit();
            game.getTable().addFinalStudents();
            game.getTable().generateCloudNumber(GameMode.values()[index]);
            game.getListOfPlayers().clear();
            game.setGameMode(GameMode.values()[index]);
            for (int i = 0; i < index + 2; i++) {
                game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                game.getListOfPlayers().get(i).generateSchool(game.getTable(), GameMode.values()[index]);
            }
            int r=rn.nextInt(game.getTable().getCloudNumber().get(0).getNumberOfSpaces());
            int pos;
            for(int indexDif=0;indexDif<2;indexDif++){
                game.setDifficulty(Difficulty.values()[indexDif]);
                for (int i = 0; i < index + 2; i++) {
                    ArrayList<Integer> id = new ArrayList<>();
                    for (int s = 0; s < game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size(); s++)
                        id.add(game.getListOfPlayers().get(i).getPersonalSchool().getEntry().get(s).getIdStudent());
                    assertEquals(id.size(), game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size());  /** Controls if the size of the new array of Id is the same after every cicle */
                    for (int s = 0; s < r; s++) {
                        pos = rn.nextInt(game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size() - 1);
                        Student newStudent = new Student(131, null);
                        for (Student student : game.getListOfPlayers().get(i).getPersonalSchool().getEntry()) {
                            if (id.get(pos) == student.getIdStudent())
                                newStudent = student;
                        }
                        game.moveStudentFromListToHall(game.getListOfPlayers().get(i), id.get(pos), game.getListOfPlayers().get(i).getPersonalSchool().getEntry());
                        assertEquals(id.size() - 1, game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size());  /** Controls if the size of the Entry is reduced by one after every cicle */

                        switch (newStudent.getSColor()) {/** Controls if the id of the student added is the same of the id selected in MoveStudentInHall  */
                            case GREEN:
                                assertEquals(id.get(pos), game.getListOfPlayers().get(i).getPersonalSchool().getGTable().get(game.getListOfPlayers().get(i).getPersonalSchool().getGTable().size() - 1).getIdStudent());
                                break;
                            case RED:
                                assertEquals(id.get(pos), game.getListOfPlayers().get(i).getPersonalSchool().getRTable().get(game.getListOfPlayers().get(i).getPersonalSchool().getRTable().size() - 1).getIdStudent());
                                break;
                            case YELLOW:
                                assertEquals(id.get(pos), game.getListOfPlayers().get(i).getPersonalSchool().getYTable().get(game.getListOfPlayers().get(i).getPersonalSchool().getYTable().size() - 1).getIdStudent());
                                break;
                            case PINK:
                                assertEquals(id.get(pos), game.getListOfPlayers().get(i).getPersonalSchool().getPTable().get(game.getListOfPlayers().get(i).getPersonalSchool().getPTable().size() - 1).getIdStudent());
                                break;
                            case BLUE:
                                assertEquals(id.get(pos), game.getListOfPlayers().get(i).getPersonalSchool().getBTable().get(game.getListOfPlayers().get(i).getPersonalSchool().getBTable().size() - 1).getIdStudent());
                                break;
                        }

                        for (Student student : game.getListOfPlayers().get(i).getPersonalSchool().getEntry()) {
                            assertNotEquals(id.get(pos), student.getIdStudent());
                        }
                        id.remove(id.get(pos));
                    }
                }
                if(Difficulty.values()[indexDif]==Difficulty.EXPERTMODE) { /** Controls if the coinscore does increase every 3 students in table   */
                    game.getTable().extractStudentOnCloud();
                    for (int i = 0; i < index + 2; i++) {
                        ArrayList<Integer> id = new ArrayList<>();
                        for (int s = 0; s < game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size(); s++)
                            id.add(game.getListOfPlayers().get(i).getPersonalSchool().getEntry().get(s).getIdStudent());
                        for (int s = 0; s < r; s++) {
                            pos = rn.nextInt(game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size() - 1);
                            game.moveStudentFromListToHall(game.getListOfPlayers().get(i), id.get(pos), game.getListOfPlayers().get(i).getPersonalSchool().getEntry());
                            id.remove(id.get(pos));
                        }
                        int count=0;
                        for(int iTable=0;iTable<5;iTable++){
                            switch(iTable){
                                case 0:
                                    for(int s3=0;s3<game.getListOfPlayers().get(i).getPersonalSchool().getGTable().size()/3;s3++)
                                        count++;
                                    break;
                                case 1:
                                    for(int s3=0;s3<game.getListOfPlayers().get(i).getPersonalSchool().getRTable().size()/3;s3++)
                                        count++;
                                    break;
                                case 2:
                                    for(int s3=0;s3<game.getListOfPlayers().get(i).getPersonalSchool().getYTable().size()/3;s3++)
                                        count++;
                                    break;
                                case 3:
                                    for(int s3=0;s3<game.getListOfPlayers().get(i).getPersonalSchool().getPTable().size()/3;s3++)
                                        count++;
                                    break;
                                case 4:
                                    for(int s3=0;s3<game.getListOfPlayers().get(i).getPersonalSchool().getBTable().size()/3;s3++)
                                        count++;
                                    break;
                            }
                        }
                        assertEquals(count,game.getListOfPlayers().get(i).getCoinScore());
                    }
                }
            }
        }
    }

    @Test
    void generateTowerColorsAndAssistant() {
        game.setGameMode(GameMode.TWOPLAYERS);
        int playersNumber = Game.initializePlayerNumber(game.getGameMode());
        assertEquals(2,playersNumber);
    game.generateTowerColorsAndAssistant();
    assertEquals(2,game.getTowerColors().size());
    assertEquals(TColor.WHITE,game.getTowerColors().get(0));
    assertEquals(TColor.BLACK,game.getTowerColors().get(1));
    assertEquals(4,game.getAssistantDeckNames().size());

    game.setGameMode(GameMode.COOP);
    game.getTowerColors().clear();
    game.getAssistantDeckNames().clear();
    game.generateTowerColorsAndAssistant();
    assertEquals(4,game.getTowerColors().size());
    }

    @Test
    void playCharacterBooleanTest() {
        DeckCharacter deckCharacter = new DeckCharacter();
        deckCharacter.generateCharacterDeck();
        Table table = new Table();
        table.generateBagInit();
        table.addFinalStudents();
        table.getCharacterCardsOnTable().addAll(deckCharacter.getCharacterCards());
        game.setTable(table);

        CharacterCard hostCard = deckCharacter.getCharacterCards().get(1);
        CharacterCard bearerCard = deckCharacter.getCharacterCards().get(3);
        CharacterCard centaurCard = deckCharacter.getCharacterCards().get(5);
        CharacterCard knightCard = deckCharacter.getCharacterCards().get(7);
        CharacterCard herbalistCard = deckCharacter.getCharacterCards().get(8);

        Player p1 = new Player(TColor.WHITE,PlayerNumber.PLAYER1);
        p1.setNickname("Federico");
        p1.setCoinScore(20);
        p1.generateSchool(table,GameMode.TWOPLAYERS);
        p1.getPersonalSchool().getEntry().clear();
        p1.getPersonalSchool().getEntry().add(new Student(1,SColor.GREEN));
        p1.getPersonalSchool().getEntry().add(new Student(2,SColor.GREEN));
        p1.getPersonalSchool().getEntry().add(new Student(3,SColor.PINK));
        p1.getPersonalSchool().getEntry().add(new Student(4,SColor.PINK));
        p1.getPersonalSchool().getEntry().add(new Student(5,SColor.PINK));
        p1.getPersonalSchool().getEntry().add(new Student(6,SColor.BLUE));
        p1.getPersonalSchool().getEntry().add(new Student(7,SColor.RED));

        Player p2 = new Player(TColor.BLACK,PlayerNumber.PLAYER2);
        p2.generateSchool(table,GameMode.TWOPLAYERS);

        game.getListOfPlayers().add(p1);
        game.getListOfPlayers().add(p2);

        game.playCharacterCard(bearerCard.getCardEffect(), "Federico", -1, -1, -1, null);
        assertEquals(true,bearerCard.getCardEffect().isBearerPlayed());

        game.playCharacterCard(centaurCard.getCardEffect(), "Federico", -1, -1, -1, null);
        assertEquals(true,centaurCard.getCardEffect().isCentaurPlayed());

        game.playCharacterCard(knightCard.getCardEffect(), "Federico", -1, -1, -1, null);
        assertEquals(true,knightCard.getCardEffect().isKnightPlayed());

        game.playCharacterCard(hostCard.getCardEffect(), "Federico", -1, -1, -1, null);
        assertEquals(true,hostCard.getCardEffect().isHostPlayed());

        game.playCharacterCard(herbalistCard.getCardEffect(), "Federico", -1, -1, -1, SColor.GREEN);
        assertEquals(true,SColor.GREEN.isColorBlocked());
        SColor.GREEN.unlockColor();
    }

    @Test
    void playCharacterCardTest(){
        DeckCharacter deckCharacter = new DeckCharacter();
        deckCharacter.generateCharacterDeck();
        Table table = new Table();
        table.generateBagInit();
        table.addFinalStudents();
        table.getCharacterCardsOnTable().addAll(deckCharacter.getCharacterCards());
        game.setTable(table);
        game.setDifficulty(Difficulty.EXPERTMODE);

        CharacterCard abbotCard = deckCharacter.getCharacterCards().get(0);
        CharacterCard curatorCard = deckCharacter.getCharacterCards().get(4);
        CharacterCard acrobatCard = deckCharacter.getCharacterCards().get(6);
        CharacterCard bardCard = deckCharacter.getCharacterCards().get(9);
        CharacterCard courtesanCard = deckCharacter.getCharacterCards().get(10);
        CharacterCard junkDealerCard = deckCharacter.getCharacterCards().get(11);


        Player p1 = new Player(TColor.WHITE,PlayerNumber.PLAYER1);
        p1.setNickname("Federico");
        p1.setCoinScore(20);
        p1.generateSchool(table,GameMode.TWOPLAYERS);
        p1.getPersonalSchool().getEntry().clear();
        p1.getPersonalSchool().getEntry().add(new Student(1,SColor.GREEN));
        p1.getPersonalSchool().getEntry().add(new Student(2,SColor.GREEN));
        p1.getPersonalSchool().getEntry().add(new Student(3,SColor.PINK));
        p1.getPersonalSchool().getEntry().add(new Student(4,SColor.PINK));
        p1.getPersonalSchool().getEntry().add(new Student(5,SColor.PINK));
        p1.getPersonalSchool().getEntry().add(new Student(6,SColor.BLUE));
        p1.getPersonalSchool().getEntry().add(new Student(7,SColor.RED));

        Player p2 = new Player(TColor.BLACK,PlayerNumber.PLAYER2);
        p2.generateSchool(table,GameMode.TWOPLAYERS);

        game.getListOfPlayers().add(p1);
        game.getListOfPlayers().add(p2);

        /**Abbot Test */
        abbotCard.getStudentsOnCard().add(new Student(1,SColor.GREEN));
        abbotCard.getStudentsOnCard().add(new Student(2,SColor.YELLOW));
        IslandCard island = new IslandCard(1);
        game.getTable().getListOfIsland().add(island);
        game.playCharacterCard(abbotCard.getCardEffect(), "Federico", 2, 1, -1, null);
        assertEquals(1,island.getStudentOnIsland().size());
        assertEquals(SColor.YELLOW, island.getStudentOnIsland().get(0).getSColor());
        assertEquals(2,abbotCard.getStudentsOnCard().size());

        /**Curator Test */
        curatorCard.setXCardOnCard(4);
        game.playCharacterCard(curatorCard.getCardEffect(), "Federico", -1, 1, -1, null);
        assertEquals(true,island.isXCardOnIsland());
        assertEquals(1,island.getXCardCounter());
        assertEquals(3,curatorCard.getXCardOnCard());

        /** Acrobat Test */
        acrobatCard.getStudentsOnCard().add(new Student(15,SColor.GREEN));
        acrobatCard.getStudentsOnCard().add(new Student(23,SColor.YELLOW));
        game.playCharacterCard(acrobatCard.getCardEffect(), "Federico", 23, -1, 1, null);
        assertEquals(7,p1.getPersonalSchool().getEntry().size());
        assertEquals(23,p1.getPersonalSchool().getEntry().get(6).getIdStudent());
        assertEquals(2,acrobatCard.getStudentsOnCard().size());
        assertEquals(SColor.GREEN, acrobatCard.getStudentsOnCard().get(0).getSColor());
        assertEquals(SColor.GREEN, acrobatCard.getStudentsOnCard().get(1).getSColor());

        /** Bard Test */
        p1.getPersonalSchool().getBTable().add(new Student(40,SColor.BLUE));
        game.playCharacterCard(bardCard.getCardEffect(), "Federico", 40, -1, 5, null); /** Pink in entry changed with blue in hall */
        assertEquals(1,p1.getPersonalSchool().numberOfStudentsInHall(SColor.PINK));
        assertEquals(7,p1.getPersonalSchool().getEntry().size());

        /** Courtesan Test */
        courtesanCard.getStudentsOnCard().add(new Student(22,SColor.PINK));
        assertEquals(1,courtesanCard.getStudentsOnCard().size());
        game.playCharacterCard(courtesanCard.getCardEffect(), "Federico", 22, -1, -1, null);
        assertEquals(2,p1.getPersonalSchool().numberOfStudentsInHall(SColor.PINK));
        assertEquals(1,courtesanCard.getStudentsOnCard().size());

        /** JunkDealer Test */
        p1.getPersonalSchool().getBTable().add(new Student(50,SColor.BLUE));
        p1.getPersonalSchool().getBTable().add(new Student(51,SColor.BLUE));
        p1.getPersonalSchool().getBTable().add(new Student(52,SColor.BLUE));
        p1.getPersonalSchool().getYTable().add(new Student(60,SColor.YELLOW));
        p1.getPersonalSchool().getRTable().add(new Student(50,SColor.RED));
        p1.getPersonalSchool().getRTable().add(new Student(50,SColor.RED));
        p1.getPersonalSchool().getRTable().add(new Student(50,SColor.RED));
        p1.getPersonalSchool().getRTable().add(new Student(50,SColor.RED));
        assertEquals(4,p1.getPersonalSchool().numberOfStudentsInHall(SColor.RED));
        assertEquals(2,p1.getPersonalSchool().numberOfStudentsInHall(SColor.PINK));
        assertEquals(3,p1.getPersonalSchool().numberOfStudentsInHall(SColor.BLUE));
        assertEquals(1,p1.getPersonalSchool().numberOfStudentsInHall(SColor.YELLOW));

        game.playCharacterCard(junkDealerCard.getCardEffect(), "Federico", -1, -1, -1, SColor.BLUE);
        assertEquals(0,p1.getPersonalSchool().numberOfStudentsInHall(SColor.BLUE));

        game.playCharacterCard(junkDealerCard.getCardEffect(), "Federico", -1, -1, -1, SColor.YELLOW);
        assertEquals(0,p1.getPersonalSchool().numberOfStudentsInHall(SColor.YELLOW));

        game.playCharacterCard(junkDealerCard.getCardEffect(), "Federico", -1, -1, -1, SColor.PINK);
        assertEquals(0,p1.getPersonalSchool().numberOfStudentsInHall(SColor.PINK));

        game.playCharacterCard(junkDealerCard.getCardEffect(), "Federico", -1, -1, -1, SColor.RED);
        assertEquals(1,p1.getPersonalSchool().numberOfStudentsInHall(SColor.RED));
    }
}