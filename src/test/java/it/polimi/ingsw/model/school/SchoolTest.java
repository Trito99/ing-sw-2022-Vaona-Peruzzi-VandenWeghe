package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.cloud.CloudCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SchoolTest {

    Table table;
    Game game;
    Random rn;

    @Test
    @BeforeEach
    public void init(){
        table= new Table();
        rn = new Random();
        game = new Game();
        table.addFinalStudents();
        table.generateIslandCards();
        table.generateMotherEarth();
    }


    @RepeatedTest(100)
    void  getProfInHallTest() {
        for (int index = 0; index < 2; index++) { /** For the first two GameModes */
            table.getBag().clear();
            table.getListOfIsland().clear();
            table.generateIslandCards();
            table.generateMotherEarth();
            table.addFinalStudents();
            game.getListOfPlayers().clear();
            game.setGameMode(GameMode.values()[index]);
            for (int i = 0; i < index + 2; i++) {
                game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                game.getListOfPlayers().get(i).generateSchool(table, GameMode.values()[index]);
            }
            for (int i = 0; i < 5; i++) {       /** The different Prof are positioned randomly*/
                int s = rn.nextInt(index + 2);
                if (!game.getListOfPlayers().get(s).getPersonalSchool().getProfOfPlayer().get(i).getIsInHall())
                    game.getListOfPlayers().get(s).getPersonalSchool().getProfOfPlayer().get(i).setInHall(true);
                else
                    i = i - 1;
            }
            for (int i = 0; i < index + 2; i++) {
                for (int s = 0; s < 5; s++)
                    assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getProfOfPlayer().get(s).getIsInHall(), game.getListOfPlayers().get(i).getPersonalSchool().getProfInHall(SColor.values()[s]));
            }
        }
    }

    @RepeatedTest(100)
    void numberOfProfTest() {
        for (int index = 0; index < 2; index++) { /** For the first two GameModes */
            table.getBag().clear();
            table.getListOfIsland().clear();
            table.generateIslandCards();
            table.generateMotherEarth();
            table.addFinalStudents();
            game.getListOfPlayers().clear();
            game.setGameMode(GameMode.values()[index]);
            for (int i = 0; i < index + 2; i++) {
                game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                game.getListOfPlayers().get(i).generateSchool(table, GameMode.values()[index]);
            }
            for (int i = 0; i < 5; i++) {       /** The different Prof are positioned randomly*/
                int s = rn.nextInt(index + 2);
                if (!game.getListOfPlayers().get(s).getPersonalSchool().getProfOfPlayer().get(i).getIsInHall())
                    game.getListOfPlayers().get(s).getPersonalSchool().getProfOfPlayer().get(i).setInHall(true);
                else
                    i = i - 1;
            }
            for (int i = 0; i < index + 2; i++) {
                ArrayList<Integer> count = new ArrayList<>();
                for (int s = 0; s < 5; s++) {
                    if (game.getListOfPlayers().get(i).getPersonalSchool().getProfOfPlayer().get(s).getIsInHall())
                        count.add(1);
                }
                assertEquals(count.size(), game.getListOfPlayers().get(i).getPersonalSchool().numberOfProf());
            }
        }
    }


    @RepeatedTest(100)
    void numberOfStudentsTestInHall() {
        int r;
        for (int index = 0; index < 2; index++) { /** For the first two GameModes */
            table.getBag().clear();
            table.getListOfIsland().clear();
            table.generateIslandCards();
            table.generateMotherEarth();
            table.addFinalStudents();
            game.getListOfPlayers().clear();
            game.setGameMode(GameMode.values()[index]);
            for (int i = 0; i < index + 2; i++) {
                game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                game.getListOfPlayers().get(i).generateSchool(table, GameMode.values()[index]);
            }

            for (int i = 0; i < index + 2; i++) {      /** Students are positioned randomly in the different Tables */
                for (int s = 1; s < 6; s++) {
                    r = rn.nextInt(7) + 1;
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
            for (int i = 0; i < index + 2; i++) {      /** Checks if numberOfStudentsInHall functions */
                for (int s = 1; s < 6; s++) {
                    switch (s) {
                        case 1:
                            assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getGTable().size(),game.getListOfPlayers().get(i).getPersonalSchool().numberOfStudentsInHall(SColor.GREEN));
                            break;
                        case 2:
                            assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getRTable().size(),game.getListOfPlayers().get(i).getPersonalSchool().numberOfStudentsInHall(SColor.RED));
                            break;
                        case 3:
                            assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getYTable().size(),game.getListOfPlayers().get(i).getPersonalSchool().numberOfStudentsInHall(SColor.YELLOW));
                            break;
                        case 4:
                            assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getPTable().size(),game.getListOfPlayers().get(i).getPersonalSchool().numberOfStudentsInHall(SColor.PINK));
                            break;
                        case 5:
                            assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getBTable().size(),game.getListOfPlayers().get(i).getPersonalSchool().numberOfStudentsInHall(SColor.BLUE));
                            break;
                    }
                }
            }
        }
    }

    @RepeatedTest(100)
    void WinProfTest(){
        int r;
        for(int index=0;index<2;index++) { /** For every GameMode (for now just the first two)*/
            table.getBag().clear();
            table.getListOfIsland().clear();
            table.generateIslandCards();
            table.generateMotherEarth();
            table.addFinalStudents();
            game.getListOfPlayers().clear();
            game.setGameMode(GameMode.values()[index]);
            for (int i = 0; i < index + 2; i++) {
                game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                game.getListOfPlayers().get(i).generateSchool(table, GameMode.values()[index]);
            }

            for (int i = 0; i < index+2; i++) {      /** Students are positioned randomly in the different Tables */
                for(int s=1;s<6;s++) {
                    r = rn.nextInt(7) + 1;
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
                            for(int t = 1; t < r; t++){
                                game.getListOfPlayers().get(i).getPersonalSchool().getBTable().add(table.getBag().get(0));
                                table.getBag().remove(0);
                            }
                            break;
                    }
                }
            }
                            /** Sets at true the InHall parameter of Prof if the player has the most students in hall*/
            for (int i = 0; i < index+2; i++) {
                game.getListOfPlayers().get(i).getPersonalSchool().winProf(game.getListOfPlayers(),game.getListOfPlayers().get(i), CardEffect.STANDARDMODE);
            }
            int[] count = new int[]{0, 0, 0, 0, 0};

            for (int i = 0; i < 5; i++) {  /** Creates an array of the maximum students in Hall for every color with this order "GRYPB" */
                int n=0;
                for(int j = 0; j < index+2; j++) {
                    if (game.getListOfPlayers().get(j).getPersonalSchool().numberOfStudentsInHall(SColor.values()[i]) > count[i]) {
                        count[i] = game.getListOfPlayers().get(j).getPersonalSchool().numberOfStudentsInHall(SColor.values()[i]);
                    }
                }
                for(int j = 0; j < index+2; j++) {
                    if (count[i] == game.getListOfPlayers().get(j).getPersonalSchool().numberOfStudentsInHall(SColor.values()[i]))
                        n++;
                }
                if(n>1)
                    count[i]=-1;  /** If more players have the most number of students for a color count[i] becomes -1 */
            }

            for (int i = 0; i < 5; i++) { /** Asserts if winProf has changed the different parameters using the array created before  */
                for (int j = 0; j < index + 2; j++) {
                    if (game.getListOfPlayers().get(j).getPersonalSchool().numberOfStudentsInHall(SColor.values()[i]) == count[i])
                        assertEquals(true, game.getListOfPlayers().get(j).getPersonalSchool().getProfInHall(SColor.values()[i]));
                    else
                        assertEquals(false, game.getListOfPlayers().get(j).getPersonalSchool().getProfInHall(SColor.values()[i]));
                }
            }
        }

    }
    @Test
    public void moveStudentFromCloudToEntry(){
        CloudCard cloudCard = new CloudCard(0, 3);
        cloudCard.getStudentOnCloud().add(new Student(1,SColor.GREEN));
        cloudCard.getStudentOnCloud().add(new Student(2,SColor.RED));
        cloudCard.getStudentOnCloud().add(new Student(3,SColor.YELLOW));

        Player p1 = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        p1.generateSchool(table, GameMode.TWOPLAYERS);
        assertEquals(7,p1.getPersonalSchool().getEntry().size());

        p1.getPersonalSchool().getEntry().clear();
        p1.getPersonalSchool().moveStudentFromCloudToEntry(cloudCard);

        assertEquals(3,p1.getPersonalSchool().getEntry().size());
        assertEquals(0,cloudCard.getStudentOnCloud().size());
    }

    @Test
    public void winProfHostTest(){
        Player p1 = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        Player p2 = new Player(TColor.BLACK, PlayerNumber.PLAYER2);
        p1.getPersonalSchool().getProfOfPlayer().add(new Prof(SColor.GREEN));
        p1.getPersonalSchool().getProfOfPlayer().add(new Prof(SColor.RED));
        p1.getPersonalSchool().getProfOfPlayer().add(new Prof(SColor.YELLOW));
        p1.getPersonalSchool().getProfOfPlayer().add(new Prof(SColor.YELLOW));
        p1.getPersonalSchool().getProfOfPlayer().add(new Prof(SColor.PINK));
        p2.getPersonalSchool().getProfOfPlayer().add(new Prof(SColor.GREEN));
        p2.getPersonalSchool().getProfOfPlayer().add(new Prof(SColor.RED));
        p2.getPersonalSchool().getProfOfPlayer().add(new Prof(SColor.YELLOW));
        p2.getPersonalSchool().getProfOfPlayer().add(new Prof(SColor.YELLOW));
        p2.getPersonalSchool().getProfOfPlayer().add(new Prof(SColor.PINK));

        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        p1.getPersonalSchool().getGTable().add(new Student(2,SColor.GREEN));
        p2.getPersonalSchool().getGTable().add(new Student(4,SColor.GREEN));

        CharacterCard hostCard = new CharacterCard(0, CardEffect.HOST, "");
        hostCard.getCardEffect().setHostPlayed(true);

        assertEquals(false,p1.getPersonalSchool().getProfInHall(SColor.GREEN));
        p1.getPersonalSchool().winProf(players,p1,hostCard.getCardEffect());
        assertEquals(true,p1.getPersonalSchool().getProfInHall(SColor.GREEN));
        assertEquals(5,p1.getPersonalSchool().numberOfProf());
        p2.getPersonalSchool().getGTable().add(new Student(8,SColor.YELLOW));
        p2.getPersonalSchool().winProf(players,p1,hostCard.getCardEffect());
        assertEquals(4,p1.getPersonalSchool().numberOfProf());
    }

}