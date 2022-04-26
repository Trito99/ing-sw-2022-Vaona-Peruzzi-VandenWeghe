package it.polimi.ingsw.model.school;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.game.Difficulty;
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
    void moveStudentFromEntryToIslandTest(){
        for (int index = 0; index < 2; index++) { /** For the first two GameModes */
            table.getBag().clear();
            table.getListOfIsland().clear();
            table.getCloudNumber().clear();
            table.generateIslandCards();
            table.generateMotherEarth();
            table.generateBagInit();
            table.extractStudentsInit();
            table.addFinalStudents();
            table.generateCloudNumber(GameMode.values()[index]);
            game.getListOfPlayers().clear();
            game.setGameMode(GameMode.values()[index]);
            for (int i = 0; i < index + 2; i++) {
                game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                game.getListOfPlayers().get(i).generateSchool(table, GameMode.values()[index]);
            }
            int r=rn.nextInt(table.getCloudNumber().get(0).getNumberOfSpaces());
            int pos, is;
            for (int i = 0; i < index + 2; i++){
                ArrayList<Integer> id = new ArrayList<>();
                for(int s=0;s<game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size();s++)
                    id.add(game.getListOfPlayers().get(i).getPersonalSchool().getEntry().get(s).getIdStudent());
                assertEquals(id.size(),game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size());  /** Controls if the size of the new array of Id is the same after every cicle */
                for(int s=0;s<r;s++) {
                    pos = rn.nextInt(game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size() - 1);
                    is = rn.nextInt(11) + 1;
                    game.getListOfPlayers().get(i).getPersonalSchool().moveStudentFromEntryToIsland(table.getListOfIsland().get(is), id.get(pos));
                    assertEquals(id.size()-1,game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size());  /** Controls if the size of the Entry is reduced by one after every cicle */
                    assertEquals(id.get(pos),table.getListOfIsland().get(is).getStudentOnIsland().get(table.getListOfIsland().get(is).getStudentOnIsland().size()-1).getIdStudent());/** Controls if the id of the student added is the same of the id selected in MoveStudentOnIsland  */
                    for(Student student:game.getListOfPlayers().get(i).getPersonalSchool().getEntry()){
                        assertNotEquals(id.get(pos),student.getIdStudent());
                    }
                    id.remove(id.get(pos));
                }
            }

        }
    }

    @RepeatedTest(1000)
    void moveStudentFromEntryToHallTest(){
        for (int index = 0; index < 2; index++) { /** For the first two GameModes */
            table.getBag().clear();
            table.getCloudNumber().clear();
            table.generateMotherEarth();
            table.addFinalStudents();
            table.generateCloudNumber(GameMode.values()[index]);
            game.getListOfPlayers().clear();
            game.setGameMode(GameMode.values()[index]);
            for (int i = 0; i < index + 2; i++) {
                game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                game.getListOfPlayers().get(i).generateSchool(table, GameMode.values()[index]);
            }
            int r=rn.nextInt(table.getCloudNumber().get(0).getNumberOfSpaces());
            int pos;
            for(int indexDif=0;indexDif<2;indexDif++){
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
                        game.getListOfPlayers().get(i).getPersonalSchool().moveStudentFromEntryToHall(game.getListOfPlayers().get(i), id.get(pos), table, Difficulty.values()[indexDif]);
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
                    table.extractStudentOnCloud();
                    for (int i = 0; i < index + 2; i++) {
                        ArrayList<Integer> id = new ArrayList<>();
                        for (int s = 0; s < game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size(); s++)
                            id.add(game.getListOfPlayers().get(i).getPersonalSchool().getEntry().get(s).getIdStudent());
                        for (int s = 0; s < r; s++) {
                            pos = rn.nextInt(game.getListOfPlayers().get(i).getPersonalSchool().getEntry().size() - 1);
                            game.getListOfPlayers().get(i).getPersonalSchool().moveStudentFromEntryToHall(game.getListOfPlayers().get(i), id.get(pos), table, Difficulty.values()[indexDif]);
                            id.remove(id.get(pos));
                        }
                        int count=0;
                        for(int iTable=0;iTable<6;iTable++){
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

}