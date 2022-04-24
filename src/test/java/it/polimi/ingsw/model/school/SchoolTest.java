package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
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

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SchoolTest {

    Table table;
    @Test
    @BeforeEach
    public void init(){
        table= new Table();
        table.addFinalStudents();
        table.generateIslandCards();
        table.generateMotherEarth();
    }

    @ParameterizedTest
    @EnumSource(GameMode.class)
    void numberOfProfTest(GameMode gameMode) {
        Player player= new Player(TColor.BLACK, PlayerNumber.PLAYER1);
        player.generateSchool(table, gameMode);
        for (int i = 0; i < 5; i++) {
            player.getPersonalSchool().getProfOfPlayer().get(i).setInHall(true);
        }
        assertEquals(5, player.getPersonalSchool().numberOfProf());
        player.getPersonalSchool().getProfOfPlayer().get(0).setInHall(true);
        player.getPersonalSchool().getProfOfPlayer().get(1).setInHall(false);
        player.getPersonalSchool().getProfOfPlayer().get(2).setInHall(false);
        player.getPersonalSchool().getProfOfPlayer().get(3).setInHall(true);
        player.getPersonalSchool().getProfOfPlayer().get(4).setInHall(false);
        assertEquals(2,player.getPersonalSchool().numberOfProf());

    }

    @RepeatedTest(100)
    void numberOfStudentsTestInHall() {
        Random rn = new Random();
        int r;
        Game game = new Game();
        for (int index = 0; index < 2; index++) { /** For every GameMode (for now just the first two)*/
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
        Random rn = new Random();
        int r;
        Game game= new Game();
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
                game.getListOfPlayers().get(i).getPersonalSchool().winProf(game.getListOfPlayers(),game.getListOfPlayers().get(i), CardEffect.EASYMODE);
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
    void getProfInHallTest(){
        Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
        player.generateSchool(table, GameMode.TWOPLAYERS);

        player.getPersonalSchool().getProfOfPlayer().get(0).setInHall(true); //Green
        player.getPersonalSchool().getProfOfPlayer().get(1).setInHall(false); // Yellow
        player.getPersonalSchool().getProfOfPlayer().get(2).setInHall(false); // Red
        player.getPersonalSchool().getProfOfPlayer().get(3).setInHall(true); // Pink
        player.getPersonalSchool().getProfOfPlayer().get(4).setInHall(false); // Blue


        assertTrue(player.getPersonalSchool().getProfInHall(SColor.GREEN));
        assertFalse(player.getPersonalSchool().getProfInHall(SColor.RED));
        assertFalse(player.getPersonalSchool().getProfInHall(SColor.YELLOW));
        assertTrue(player.getPersonalSchool().getProfInHall(SColor.PINK));
        assertFalse(player.getPersonalSchool().getProfInHall(SColor.BLUE));

    }
}