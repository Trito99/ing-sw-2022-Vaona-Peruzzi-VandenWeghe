package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.assistant.DeckName;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.character.DeckCharacter;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


public class TableTest{

    private Table table;

    @BeforeEach
    public void setup(){
        table = new Table();
        table.generateIslandCards();
        table.generateMotherEarth();
        table.addFinalStudents();
    }

    @Test
    public void generateBagInitTest() {
        int countgreen=0;
        int countyellow=0;
        int countred=0;
        int countblue=0;
        int countpink=0;
        table.getBag().clear();
        table.generateBagInit();
        assertNotNull(table.getBag());
        assertNotNull(table.getBag().get(9));
        assertEquals(10, table.getBag().size());   /**Tests if there are 10 students in the bag*/

        for(Student s : table.getBag()){    /**Tests if there are 2 students for each color*/
            switch (s.getsColour()){
                case GREEN:
                    countgreen++;
                    break;
                case YELLOW:
                    countyellow++;
                    break;
                case RED:
                    countred++;
                    break;
                case BLUE:
                    countblue++;
                    break;
                case PINK:
                    countpink++;
                    break;
            }
        }
        assertEquals(2, countgreen);
        assertEquals(2, countyellow);
        assertEquals(2, countred);
        assertEquals(2, countblue);
        assertEquals(2, countpink);

    }

    @Test
    public void generateIslandCardsTest(){ /** Tests if generateIslandCards creates always 12 islands with correct id*/
        assertNotNull(table.getListOfIsland().get(0));
        assertEquals(1, table.getListOfIsland().get(0).getIdIsland());
        assertNotNull(table.getListOfIsland().get(11));
        assertEquals(12, table.getListOfIsland().get(11).getIdIsland());
        assertEquals(12,table.getListOfIsland().size());
    }

    @RepeatedTest(100)
    public void generateMotherEarthTest(){
        int n=0;
        for(IslandCard islandCard : table.getListOfIsland()){
            if(islandCard.getMotherEarthOnIsland()){  /**Tests if MotherEarth is only and only in one island */
                assertEquals(table.getPosMotherEarth(), islandCard.getIdIsland());
                n++;
            }
        }
        assertEquals(1,n);
    }

    @ParameterizedTest
    @EnumSource(GameMode.class)
    public void generateCloudNumberTest(GameMode gameMode){
        table.generateCloudNumber(gameMode);
        for(CloudCard cloud : table.getCloudNumber())
        assertNotNull(cloud);
    }

    @Test
    public void addFinalStudentsTest(){ /** Tests if at the beginning of the game there are 24 students of each color */
        int countgreen=0;
        int countyellow=0;
        int countred=0;
        int countblue=0;
        int countpink=0;
        assertNotNull(table.getBag());
        assertNotNull(table.getBag().get(119));
        for(Student s : table.getBag()){
            switch (s.getsColour()){
                case GREEN:
                    countgreen++;
                    break;
                case YELLOW:
                    countyellow++;
                    break;
                case RED:
                    countred++;
                    break;
                case BLUE:
                    countblue++;
                    break;
                case PINK:
                    countpink++;
                    break;
            }
        }
        assertEquals(24, countgreen);
        assertEquals(24, countyellow);
        assertEquals(24, countred);
        assertEquals(24, countblue);
        assertEquals(24, countpink);

    }


    @RepeatedTest(50)
    public void extractStudentsInitTest() { /**checks the students on the islands at the beginning of the game */
        table.getBag().clear();
        table.generateBagInit();
        table.extractStudentsInit();
        for (IslandCard is : table.getListOfIsland()) {
            if (table.getPosMotherEarth() + 6 <= table.getListOfIsland().size()) {
                if (!is.getMotherEarthOnIsland() && (is.getIdIsland() != (table.getPosMotherEarth() + 6)))
                    assertEquals(1, is.getStudentOnIsland().size());
                else
                    assertEquals(0, is.getStudentOnIsland().size()); /** if there is motherEarth, checks there aren't students on the island*/
            } else {
                if (!is.getMotherEarthOnIsland() && (is.getIdIsland() != (table.getPosMotherEarth() - 6)))
                    assertEquals(1, is.getStudentOnIsland().size());
                else
                    assertEquals(0, is.getStudentOnIsland().size()); /** if there is motherEarth, checks there aren't students on the island*/
            }
        }
        assertEquals(0,table.getBag().size());
    }



    @ParameterizedTest
    @EnumSource(GameMode.class)
    public void ExtractStudentOnCloudTest(GameMode gameMode){ /** Tests if is always extracted the correct number of students from the bag */
        table.generateCloudNumber(gameMode);
        int n = table.getBag().size();
        table.extractStudentOnCloud();
        for(CloudCard cloud : table.getCloudNumber()){
            assertNotNull(cloud.getStudentOnCloud());
            assertEquals(cloud.getNumberOfSpaces(),cloud.getStudentOnCloud().size());
        }
        assertEquals(n-(table.getCloudNumber().get(0).getNumberOfSpaces()*table.getCloudNumber().size()),table.getBag().size());
    }

    @RepeatedTest(100)
    void generateCharacterCardsOnTable() { /** Check if at the beginning of the expertMode game there are 3 casual character card on the table */
        DeckCharacter characterDeck = new DeckCharacter();
        characterDeck.generateCharacterDeck();
        table.generateCharacterCardsOnTable(characterDeck.getCharacterCards());
        int cardsOnTable = 0;

        assertNotNull(table.getCharacterCardsOnTable().get(0));
        assertNotNull(table.getCharacterCardsOnTable().get(2));
        for (CharacterCard card : table.getCharacterCardsOnTable()) {
            cardsOnTable++;
            assertNotNull(card);
            if(card.getCardEffect().equals(CardEffect.ABBOT)){ /**Tests if effects with students on the card works */
                assertEquals(card.getCardEffect(), CardEffect.ABBOT);
                assertEquals(4, card.getStudentsOnCard().size());
            }
        }
        assertEquals(3, cardsOnTable);
    }

    @RepeatedTest(100)
    public void MoveMotherEarthTest(){ /** Checks if MotherEarth always moves correctly */
        Random rn = new Random();
        int n = rn.nextInt(12) + 1, p=0;
        while(n==table.getPosMotherEarth()) {
            n = rn.nextInt(12) + 1;
        }
        table.moveMotherEarth(n);
        for(IslandCard islandCard : table.getListOfIsland()){
            if(islandCard.getMotherEarthOnIsland()){
                assertEquals(table.getPosMotherEarth(),islandCard.getIdIsland());
                p++;
            }
        }
        assertEquals(1,p);
    }

    @RepeatedTest(100)
    public void JoinIslandTest() {
        Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER2);
        Player player2 = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        player.generateSchool(table, GameMode.TWOPLAYERS);
        player2.generateSchool(table,GameMode.TWOPLAYERS);
        ArrayList<IslandCard> newListOfIslands = new ArrayList<>();
        Random rn = new Random();
        int r;
        for (int i = 0; i < 12; i++) {  /** Moves random number of students on Islands. */
            newListOfIslands.add(new IslandCard(i));
            r = rn.nextInt(8)+1;
            for (int n = 0; n < r; n++) {
                table.getListOfIsland().get(i).getStudentOnIsland().add(table.getBag().get(0));
                newListOfIslands.get(i).getStudentOnIsland().add(table.getBag().get(0));
                table.getBag().remove(0);
            }
        }

        /**  Checks if joinIsland functions in the case of the merge of 3 Islands */
        for (int p = 0; p < table.getListOfIsland().size(); p++) {
            table.moveMotherEarth(13+p-table.getPosMotherEarth());
            if (p == 0) {
                table.getListOfIsland().get(11).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(11).setTowerIsOnIsland(true);
            } else {
                table.getListOfIsland().get(p - 1).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(p - 1).setTowerIsOnIsland(true);
            }

            if (p == 11) {
                table.getListOfIsland().get(0).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(0).setTowerIsOnIsland(true);
            } else {
                table.getListOfIsland().get(p + 1).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(p + 1).setTowerIsOnIsland(true);
            }

            table.getListOfIsland().get(p).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
            table.getListOfIsland().get(p).setTowerIsOnIsland(true);
            table.joinIsland(table.getListOfIsland());

            assertEquals(10, table.getListOfIsland().size());

            if (p == 0) {
                assertEquals(newListOfIslands.get(11).getStudentOnIsland().size() + newListOfIslands.get(p).getStudentOnIsland().size() + newListOfIslands.get(p + 1).getStudentOnIsland().size(), table.getListOfIsland().get(table.getPosMotherEarth()-1).getStudentOnIsland().size());
                assertEquals(3, table.getListOfIsland().get(table.getPosMotherEarth()-1).getMergedIsland());
            } else if (p == 11) {
                assertEquals(newListOfIslands.get(p - 1).getStudentOnIsland().size() + newListOfIslands.get(p).getStudentOnIsland().size() + newListOfIslands.get(0).getStudentOnIsland().size(), table.getListOfIsland().get(table.getPosMotherEarth()-1).getStudentOnIsland().size());
                assertEquals(3, table.getListOfIsland().get(table.getPosMotherEarth()-1).getMergedIsland());
            } else {
                assertEquals(newListOfIslands.get(p - 1).getStudentOnIsland().size() + newListOfIslands.get(p).getStudentOnIsland().size() + newListOfIslands.get(p + 1).getStudentOnIsland().size(), table.getListOfIsland().get(table.getPosMotherEarth()-1).getStudentOnIsland().size());
                assertEquals(3, table.getListOfIsland().get(table.getPosMotherEarth()-1).getMergedIsland());
            }
            assertEquals(true,table.getListOfIsland().get(table.getPosMotherEarth()-1).getMotherEarthOnIsland());

            /** Regenerates the original conditions with new random number of students */
            table.getListOfIsland().clear();
            newListOfIslands.clear();
            table.getBag().clear();
            table.addFinalStudents();
            for (int i = 0; i < 12; i++) {
                table.getListOfIsland().add(new IslandCard(i));
                newListOfIslands.add(new IslandCard(i));
                r = rn.nextInt(8)+1;
                for (int n = 0; n < r; n++) {
                    table.getListOfIsland().get(i).getStudentOnIsland().add(table.getBag().get(0));
                    newListOfIslands.get(i).getStudentOnIsland().add(table.getBag().get(0));
                    table.getBag().remove(0);
                }
            }
            table.generateMotherEarth();
        }

        /**  Checks if joinIsland functions in the case of the merge of 2 Islands (the one in question and the one before). The next Island has a tower with different color on even positions */
        for (int p2 = 0; p2 < table.getListOfIsland().size(); p2++) {
            table.moveMotherEarth(13+p2-table.getPosMotherEarth());
            if (p2 == 0) {
                table.getListOfIsland().get(11).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(11).setTowerIsOnIsland(true);
            } else {
                table.getListOfIsland().get(p2 - 1).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(p2 - 1).setTowerIsOnIsland(true);
            }

            if (p2 == 11) {
                table.getListOfIsland().get(0).setTowerOnIsland(player2.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(0).setTowerIsOnIsland(true);
            } else if (p2%2==1) {
                table.getListOfIsland().get(p2 + 1).setTowerOnIsland(player2.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(p2 + 1).setTowerIsOnIsland(true);
            }

            table.getListOfIsland().get(p2).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
            table.getListOfIsland().get(p2).setTowerIsOnIsland(true);

            table.joinIsland(table.getListOfIsland());

            assertEquals(11, table.getListOfIsland().size());

            if (p2 == 0) {
                assertEquals(newListOfIslands.get(11).getStudentOnIsland().size() + newListOfIslands.get(p2).getStudentOnIsland().size(), table.getListOfIsland().get(table.getPosMotherEarth()-1).getStudentOnIsland().size());
                assertEquals(2, table.getListOfIsland().get(table.getPosMotherEarth()-1).getMergedIsland());
            } else if (p2 == 11) {
                assertEquals(newListOfIslands.get(p2 - 1).getStudentOnIsland().size() + newListOfIslands.get(p2).getStudentOnIsland().size(), table.getListOfIsland().get(table.getPosMotherEarth()-1).getStudentOnIsland().size());
                assertEquals(2, table.getListOfIsland().get(table.getPosMotherEarth()-1).getMergedIsland());
            } else {
                assertEquals(newListOfIslands.get(p2 - 1).getStudentOnIsland().size() + newListOfIslands.get(p2).getStudentOnIsland().size(), table.getListOfIsland().get(table.getPosMotherEarth()-1).getStudentOnIsland().size());
                assertEquals(2, table.getListOfIsland().get(table.getPosMotherEarth()-1).getMergedIsland());
            }

            assertEquals(true,table.getListOfIsland().get(table.getPosMotherEarth()-1).getMotherEarthOnIsland());

            /** Regenerates the original conditions with new random number of students */
            table.getListOfIsland().clear();
            newListOfIslands.clear();
            table.getBag().clear();
            table.addFinalStudents();
            for (int i = 0; i < 12; i++) {
                table.getListOfIsland().add(new IslandCard(i));
                newListOfIslands.add(new IslandCard(i));
                r = rn.nextInt(8)+1;
                for (int n = 0; n < r; n++) {
                    table.getListOfIsland().get(i).getStudentOnIsland().add(table.getBag().get(0));
                    newListOfIslands.get(i).getStudentOnIsland().add(table.getBag().get(0));
                    table.getBag().remove(0);
                }
            }
            table.generateMotherEarth();
        }

        /**  Checks if joinIsland functions in the case of the merge of 2 Islands (the one in question and the next one). The next Island has a tower with different color on even positions  */
        for (int p3 = 0; p3 < table.getListOfIsland().size(); p3++) {
            table.moveMotherEarth(13+p3-table.getPosMotherEarth());

            if (p3 == 0) {
                table.getListOfIsland().get(11).setTowerOnIsland(player2.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(11).setTowerIsOnIsland(true);
            } else if (p3%2==0) {
                table.getListOfIsland().get(p3 - 1).setTowerOnIsland(player2.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(p3 - 1).setTowerIsOnIsland(true);
            }

            if (p3 == 11) {
                table.getListOfIsland().get(0).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(0).setTowerIsOnIsland(true);
            } else {
                table.getListOfIsland().get(p3 + 1).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(p3 + 1).setTowerIsOnIsland(true);
            }


            table.getListOfIsland().get(p3).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
            table.getListOfIsland().get(p3).setTowerIsOnIsland(true);

            table.joinIsland(table.getListOfIsland());

            assertEquals(11, table.getListOfIsland().size());

            if (p3 == 0) {
                assertEquals(newListOfIslands.get(p3).getStudentOnIsland().size() + newListOfIslands.get(p3+1).getStudentOnIsland().size(), table.getListOfIsland().get(table.getPosMotherEarth()-1).getStudentOnIsland().size());
                assertEquals(2, table.getListOfIsland().get(table.getPosMotherEarth()-1).getMergedIsland());
            } else if (p3 == 11) {
                assertEquals(newListOfIslands.get(p3).getStudentOnIsland().size() + newListOfIslands.get(0).getStudentOnIsland().size(), table.getListOfIsland().get(table.getPosMotherEarth()-1).getStudentOnIsland().size());
                assertEquals(2, table.getListOfIsland().get(table.getPosMotherEarth()-1).getMergedIsland());
            } else {
                assertEquals(newListOfIslands.get(p3).getStudentOnIsland().size() + newListOfIslands.get(p3+1).getStudentOnIsland().size(), table.getListOfIsland().get(table.getPosMotherEarth()-1).getStudentOnIsland().size());
                assertEquals(2, table.getListOfIsland().get(table.getPosMotherEarth()-1).getMergedIsland());
            }

            assertEquals(true,table.getListOfIsland().get(table.getPosMotherEarth()-1).getMotherEarthOnIsland());

            /** Regenerates the original conditions with new random number of students */
            table.getListOfIsland().clear();
            newListOfIslands.clear();
            table.getBag().clear();
            table.addFinalStudents();
            for (int i = 0; i < 12; i++) {
                table.getListOfIsland().add(new IslandCard(i));
                newListOfIslands.add(new IslandCard(i));
                r = rn.nextInt(8)+1;
                for (int n = 0; n < r; n++) {
                    table.getListOfIsland().get(i).getStudentOnIsland().add(table.getBag().get(0));
                    newListOfIslands.get(i).getStudentOnIsland().add(table.getBag().get(0));
                    table.getBag().remove(0);
                }
            }
            table.generateMotherEarth();
        }

    }

    @Test
    void getCharacterCardTest(){
        table.getCharacterCardsOnTable().add(new CharacterCard(2, CardEffect.HOST));
        table.getCharacterCardsOnTable().add(new CharacterCard(1, CardEffect.BEARER));
        table.getCharacterCardsOnTable().add(new CharacterCard(3, CardEffect.CENTAUR));

        assertEquals(1, table.getCharacterCard(CardEffect.BEARER).getCostCharacter());
        assertEquals(CardEffect.HOST, table.getCharacterCard(CardEffect.HOST).getCardEffect());

    }

    @RepeatedTest(100)
    public void playerIsWinningTest(){

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
            for (int i = 0; i < index+2; i++) {
                game.getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                game.getListOfPlayers().get(i).generateSchool(table, GameMode.values()[index]);
            }

            for (int i = 0; i < 5; i++) {       /** The different Prof are positioned randomly*/
                int s = rn.nextInt(index+2);
                if (game.getListOfPlayers().get(s).getPersonalSchool().getProfOfPlayer().get(i).getIsInHall() == false)
                    game.getListOfPlayers().get(s).getPersonalSchool().getProfOfPlayer().get(i).setInHall(true);
                else
                    i = i - 1;
            }

            for (int i = 0; i < 12; i++) {      /** Students and Towers are positioned randomly on Islands */
                r = rn.nextInt(8) + 1;
                for (int n = 0; n < r; n++) {
                    table.getListOfIsland().get(i).getStudentOnIsland().add(table.getBag().get(0));
                    table.getBag().remove(0);
                }
                table.getListOfIsland().get(i).setTowerOnIsland(game.getListOfPlayers().get(rn.nextInt(game.getListOfPlayers().size())).getPersonalSchool().getTower().get(0));
                table.getListOfIsland().get(i).setTowerIsOnIsland(true);
            }

            int[] count = new int[]{0, 0, 0};
            int[] count2 = new int[]{-2, -2, -2};
            for (int i = 0; i < table.getListOfIsland().size(); i++) {
                if (table.getListOfIsland().get(i).getTowerOnIsland().getTColour() == TColor.WHITE)
                    count[0]++; //white
                else if (table.getListOfIsland().get(i).getTowerOnIsland().getTColour() == TColor.BLACK)
                    count[1]++; //black
                else
                    count[2]++; //grey
            }

            for (int i = 0; i < 2 * table.getListOfIsland().size(); i++) {   /** Islands are Joined */
                table.joinIsland(table.getListOfIsland());
                table.moveMotherEarth(1);
            }

            int max = 0, p = 0;
            for (int i = 0; i < count.length; i++) {
                if (max < count[i])
                    max = count[i];
                else if (max == count[i])
                    p = 1;
            }
            if (p == 1) {
                for (int i = 0; i < count2.length; i++) {
                    if (count[i] == max)
                        count2[i] = game.getListOfPlayers().get(i).getPersonalSchool().numberOfProf();
                }
                max = -1;
                int temp = 0;
                for (int i = 0; i < count.length; i++) {
                    if (max < count2[i])
                        max = count2[i];
                    else if (max == count2[i]) {
                        temp = max;
                        p = 2;
                    }
                    for (int j = 0; j < count.length; j++) {
                        if (temp < count2[j])
                            p = 1;
                    }
                }
            }


            for (int i = 0; i < count.length; i++) {
                if (max == count[i] && p == 0)
                    assertEquals(PlayerNumber.values()[i], table.playerIsWinning(game).getPlayerNumber());
                else if (max == count2[i] && p == 1)
                    assertEquals(PlayerNumber.values()[i], table.playerIsWinning(game).getPlayerNumber());
                else if (p == 2)
                    assertNull(table.playerIsWinning(game));
            }
        }
    }

    /** controlla i costCharacter
     for( CharacterCard card : deckCharacter.getCharacterCards()){
     if( card.getCardEffect().equals(CardEffect.BACCO)){
     assertEquals(1, card.getCostCharacter());
     }
     } */
}
