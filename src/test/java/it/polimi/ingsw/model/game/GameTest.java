package it.polimi.ingsw.model.game;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.DeckCharacter;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
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

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    
    Game game;

    @Test
    @BeforeEach
    public void init(){
        game = new Game();
    }

    @Test
    void gameIsFinished() {
        Player player = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        player.setNickname("Gino");
        game.addPlayer(player);
        Table table = new Table();
        DeckAssistant deckAssistant = new DeckAssistant(AssistantDeckName.WIZARD1);
        table.generateIslandCards();

        game.setTable(table);
        game.getPlayer("Gino").setDeckOfPlayer(deckAssistant);
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

    /** Da Fare: Test di playCharacterCard */
    @RepeatedTest(100)
    void playCharacterCardTest(){
        
        Table table = new Table();
        Random rn = new Random();
        for (int index = 0; index < 2; index++) {
            int r;
            game.getListOfPlayers().clear();
            table.getBag().clear();
            table.getListOfIsland().clear();
            table.generateIslandCards();
            table.addFinalStudents();
            table.generateMotherEarth();
            game.setGameMode(GameMode.values()[index]);
            table.generateCloudNumber(GameMode.values()[index]);
            DeckCharacter deckCharacter = new DeckCharacter();
            deckCharacter.generateCharacterDeck();
            table.generateCharacterCardsOnTable(deckCharacter.getCharacterCards());
            for (int i = 0; i < index + 2; i++) {
                game.addPlayer(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                game.getListOfPlayers().get(i).generateSchool(table, GameMode.values()[index]);
                switch(i){
                    case(1):
                        game.getListOfPlayers().get(i).setNickname("Gino");
                        break;
                    case(2):
                        game.getListOfPlayers().get(i).setNickname("Pino");
                        break;
                    case(3):
                        game.getListOfPlayers().get(i).setNickname("Tino");
                        break;
                }
            }
            for (int i = 0; i < index + 2; i++) {      /** Students are positioned randomly in the different Tables */
                for (int s = 1; s < 6; s++) {
                    r = rn.nextInt(3) + 1;
                    switch (s) {
                        case 1:
                            for (int t = 1; t < r; t++) {
                                game.getListOfPlayers().get(i).getPersonalSchool().getGTable().add(table.getBag().get(0));
                                table.getBag().remove(0);
                            }
                            break;
                        case 2:
                            for (int t = 1; t < r; t++) {
                                game.getListOfPlayers().get(i).getPersonalSchool().getRTable().add(table.getBag().get(0));
                                table.getBag().remove(0);
                            }
                            break;
                        case 3:
                            for (int t = 1; t < r; t++) {
                                game.getListOfPlayers().get(i).getPersonalSchool().getYTable().add(table.getBag().get(0));
                                table.getBag().remove(0);
                            }
                            break;
                        case 4:
                            for (int t = 1; t < r; t++) {
                                game.getListOfPlayers().get(i).getPersonalSchool().getPTable().add(table.getBag().get(0));
                                table.getBag().remove(0);
                            }
                            break;
                        case 5:
                            for (int t = 1; t < r; t++) {
                                game.getListOfPlayers().get(i).getPersonalSchool().getBTable().add(table.getBag().get(0));
                                table.getBag().remove(0);
                            }
                            break;
                    }
                }
            }
            for (int i = 0; i < 12; i++) {  /** Moves random number of students on Islands. */
                r = rn.nextInt(3)+1;
                for (int n = 0; n < r; n++) {
                    table.getListOfIsland().get(i).getStudentOnIsland().add(table.getBag().get(0));
                    table.getBag().remove(0);
                }
            }
            for (int i = 0; i < index+2; i++) {
                game.getListOfPlayers().get(i).getPersonalSchool().winProf(game.getListOfPlayers(),game.getListOfPlayers().get(i), CardEffect.STANDARDMODE);
            }

           /** if(table.g null)
            game.playCharacterCard(CardEffect.CENTAUR, "Gino"); */
        }
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

                        switch (newStudent.getsColour()) {/** Controls if the id of the student added is the same of the id selected in MoveStudentInHall  */
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
}