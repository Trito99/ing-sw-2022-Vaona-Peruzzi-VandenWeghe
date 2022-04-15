package it.polimi.ingsw.model.table;

import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.character.DeckCharacter;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TableTest{

    private Table table;

    @BeforeEach
    public void setup(){
        table = new Table();
        table.generateIslandCards();
        table.generateMotherEarth();
    }

    @Test
    public void generateBagInitTest() {
        int countgreen=0;
        int countyellow=0;
        int countred=0;
        int countblue=0;
        int countpink=0;
        table.generateBagInit();
        assertNotNull(table.getBag());
        assertNotNull(table.getBag().get(9));
        assertEquals(10, table.getBag().size());

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
        assertEquals(2, countgreen);
        assertEquals(2, countyellow);
        assertEquals(2, countred);
        assertEquals(2, countblue);
        assertEquals(2, countpink);

    }

    @Test
    public void addFinalStudentsTest(){
        int countgreen=0;
        int countyellow=0;
        int countred=0;
        int countblue=0;
        int countpink=0;
        table.addFinalStudents();
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
    public void extractStudentsInitTest() {
        table.generateBagInit();
        table.extractStudentsInit();
        for (IslandCard is : table.getListOfIsland()) {
            if (table.getPosMotherEarth() + 6 <= table.getListOfIsland().size()) {
                if (is.getMotherEarthOnIsland() == false && (is.getIdIsland() != (table.getPosMotherEarth() + 6)))
                    assertEquals(1, is.getStudentOnIsland().size());
                else
                    assertEquals(0, is.getStudentOnIsland().size());
            } else {
                if (is.getMotherEarthOnIsland() == false && (is.getIdIsland() != (table.getPosMotherEarth() - 6)))
                    assertEquals(1, is.getStudentOnIsland().size());
                else
                    assertEquals(0, is.getStudentOnIsland().size());
            }
        }
        assertEquals(0,table.getBag().size());
    }



    @ParameterizedTest
    @EnumSource(GameMode.class)
    public void ExtractStudentOnCloudTest(GameMode gameMode){
        table.generateCloudNumber(gameMode);
        table.addFinalStudents();
        int n = table.getBag().size();
        table.extractStudentOnCloud();
        for(CloudCard cloud : table.getCloudNumber()){
            assertNotNull(cloud.getStudentOnCloud());
            assertEquals(cloud.getNumberOfSpaces(),cloud.getStudentOnCloud().size());
        }
        assertEquals(n-(table.getCloudNumber().get(0).getNumberOfSpaces()*table.getCloudNumber().size()),table.getBag().size());
    }

    @RepeatedTest(100)
    void generateCharacterCardsOnTable() {
        table.addFinalStudents();
        DeckCharacter characterDeck = new DeckCharacter();
        characterDeck.generateCharacterDeck();
        table.generateCharacterCardsOnTable(characterDeck.getCharacterCards());
        int cardsOnTable = 0;

        assertNotNull(table.getCharacterCardsOnTable());
        for (CharacterCard card : table.getCharacterCardsOnTable()) {
            cardsOnTable++;
            assertNotNull(card);
        }
        assertEquals(3, cardsOnTable);
    }

    /** controlla i costCharacter
     for( CharacterCard card : deckCharacter.getCharacterCards()){
     if( card.getCardEffect().equals(CardEffect.MBRIACONE)){
     assertEquals(1, card.getCostCharacter());
     }
     } */
}
