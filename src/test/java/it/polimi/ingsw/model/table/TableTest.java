package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.character.DeckCharacter;
import it.polimi.ingsw.model.cloud.CloudCard;
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

import java.util.Random;

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
            if(card.getCardEffect().equals(CardEffect.ABATE)){ /**Tests if effects with students on the card works */
                assertEquals(card.getCardEffect(), CardEffect.ABATE);
                assertEquals(4, card.getStudentsOnCard().size());
            }
        }
        assertEquals(3, cardsOnTable);
    }

    @RepeatedTest(100)
    public void MoveMotherEarthTest(){ /** Checks if MotherEarth always move correctly */
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

    @ParameterizedTest
    @EnumSource(GameMode.class)
    public void JoinIslandTest(GameMode gameMode){
        Player player= new Player(TColor.BLACK, PlayerNumber.PLAYER2);
        player.generateSchool(table,gameMode);
        for(int i=0;i<12;i++) {
            for (int n = 0; n < i+1; n++)
                table.getListOfIsland().get(i).getStudentOnIsland().add(table.getBag().get(n));
        }
        assertEquals(1,table.getListOfIsland().get(0).getStudentOnIsland().size());
        assertEquals(2,table.getListOfIsland().get(1).getStudentOnIsland().size());
        assertEquals(3,table.getListOfIsland().get(2).getStudentOnIsland().size());
        assertEquals(12,table.getListOfIsland().get(11).getStudentOnIsland().size());

        table.setPosMotherEarth(2);
        table.getListOfIsland().get(1).setTowerIsOnIsland(true);
        table.getListOfIsland().get(0).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
        assertEquals(TColor.BLACK,table.getListOfIsland().get(0).getTowerOnIsland().getTColour());
        table.getListOfIsland().get(2).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
        table.getListOfIsland().get(1).setTowerOnIsland(player.getPersonalSchool().getTower().get(0));
        table.joinIsland(table.getListOfIsland());
        assertEquals(10,table.getListOfIsland().size());
        assertEquals(6,table.getListOfIsland().get(0).getStudentOnIsland().size());
    }

    /** controlla i costCharacter
     for( CharacterCard card : deckCharacter.getCharacterCards()){
     if( card.getCardEffect().equals(CardEffect.BACCO)){
     assertEquals(1, card.getCostCharacter());
     }
     } */
}
