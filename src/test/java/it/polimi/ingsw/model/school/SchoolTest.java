package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
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
    void numberOfStudentsTest() {
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
                            assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getGTable().size(),game.getListOfPlayers().get(i).getPersonalSchool().numberOfStudentsInTable(SColor.GREEN));
                            break;
                        case 2:
                            assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getRTable().size(),game.getListOfPlayers().get(i).getPersonalSchool().numberOfStudentsInTable(SColor.RED));
                            break;
                        case 3:
                            assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getYTable().size(),game.getListOfPlayers().get(i).getPersonalSchool().numberOfStudentsInTable(SColor.YELLOW));
                            break;
                        case 4:
                            assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getPTable().size(),game.getListOfPlayers().get(i).getPersonalSchool().numberOfStudentsInTable(SColor.PINK));
                            break;
                        case 5:
                            assertEquals(game.getListOfPlayers().get(i).getPersonalSchool().getBTable().size(),game.getListOfPlayers().get(i).getPersonalSchool().numberOfStudentsInTable(SColor.BLUE));
                            break;
                    }
                }
            }
        }
    }

    @RepeatedTest(1)
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

            for (int i = 0; i < index+2; i++) {
                game.getListOfPlayers().get(i).getPersonalSchool().winProf(game.getListOfPlayers(),game.getListOfPlayers().get(i), CardEffect.EASYMODE);
            }
            for (int i = 0; i < index+2; i++) {
                for(int g=0;g<game.getListOfPlayers().get(i).getPersonalSchool().getGTable().size();g++)
                    System.out.print(game.getListOfPlayers().get(i).getPersonalSchool().getGTable().get(g).getIdStudent()+" ");
                System.out.print(game.getListOfPlayers().get(i).getPersonalSchool().getProfInHall(SColor.GREEN)+" "+game.getListOfPlayers().get(i).getPersonalSchool().getGTable().size()+"\n");

                for(int g=0;g<game.getListOfPlayers().get(i).getPersonalSchool().getRTable().size();g++)
                    System.out.print(game.getListOfPlayers().get(i).getPersonalSchool().getRTable().get(g).getIdStudent()+" ");
                System.out.print(game.getListOfPlayers().get(i).getPersonalSchool().getProfInHall(SColor.RED)+" "+game.getListOfPlayers().get(i).getPersonalSchool().getRTable().size()+"\n");

                for(int g=0;g<game.getListOfPlayers().get(i).getPersonalSchool().getYTable().size();g++)
                    System.out.print(game.getListOfPlayers().get(i).getPersonalSchool().getYTable().get(g).getIdStudent()+" ");
                System.out.print(game.getListOfPlayers().get(i).getPersonalSchool().getProfInHall(SColor.YELLOW)+" "+game.getListOfPlayers().get(i).getPersonalSchool().getYTable().size()+"\n");

                for(int g=0;g<game.getListOfPlayers().get(i).getPersonalSchool().getPTable().size();g++)
                    System.out.print(game.getListOfPlayers().get(i).getPersonalSchool().getPTable().get(g).getIdStudent()+" ");
                System.out.print(game.getListOfPlayers().get(i).getPersonalSchool().getProfInHall(SColor.PINK)+" "+game.getListOfPlayers().get(i).getPersonalSchool().getPTable().size()+"\n");

                for(int g=0;g<game.getListOfPlayers().get(i).getPersonalSchool().getBTable().size();g++)
                    System.out.print(game.getListOfPlayers().get(i).getPersonalSchool().getBTable().get(g).getIdStudent()+" ");
                System.out.print(game.getListOfPlayers().get(i).getPersonalSchool().getProfInHall(SColor.BLUE)+" "+game.getListOfPlayers().get(i).getPersonalSchool().getBTable().size()+"\n");
                System.out.println("---");
            }
            System.out.println("----------");
        }

    }

    @ParameterizedTest
    @EnumSource(GameMode.class)
    void numberOfStudentsTest(GameMode gameMode){

        Player p = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        p.generateSchool(table, gameMode);
        p.getPersonalSchool().getEntry().add(new Student(0,SColor.GREEN));
        p.getPersonalSchool().getEntry().add(new Student(7, SColor.GREEN));
        p.getPersonalSchool().getEntry().add(new Student(12,SColor.RED));
        p.getPersonalSchool().getEntry().add(new Student(16,SColor.YELLOW));
        p.getPersonalSchool().getEntry().add(new Student(52,SColor.PINK));
        p.getPersonalSchool().getEntry().add(new Student(77,SColor.GREEN));
        p.getPersonalSchool().getEntry().add(new Student(2,SColor.YELLOW));

        /** assertEquals(3, p.getPersonalSchool().numberOfStudents(SColor.GREEN));
        assertEquals(0, p.getPersonalSchool().numberOfStudents(SColor.BLUE));
         DA CAMBIARE numberOfStudents(Player player SColor sColor) in numberOfStudents(SColor sColor)                                      */

    }
}