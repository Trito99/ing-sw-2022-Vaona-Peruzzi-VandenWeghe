package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.GregorianCalendar;

import static java.util.Calendar.*;
import static org.junit.Assert.*;

public class PlayerTest {
    private Table table;
    private DeckAssistant deckOfPlayer;
    private GameMode gameMode = GameMode.TWOPLAYERS;

    @BeforeEach
    public void setup(){
        table = new Table();
        deckOfPlayer = new DeckAssistant();
        table.addFinalStudents();
        table.generateIslandCards();
        table.generateMotherEarth();
    }

    @ParameterizedTest
    @EnumSource(GameMode.class)
    public void generateSchool(GameMode gm){
        Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
        player.generateSchool(table, gm);
        assertNotNull(player.getPersonalSchool());
        assertNotNull(player.getPersonalSchool().getEntry());
        assertNotNull(player.getPersonalSchool().getTower());

        switch (gm){
            case TWOPLAYERS:
            case COOP:
                assertEquals(7, player.getPersonalSchool().getEntry().size());
                assertEquals(8, player.getPersonalSchool().getTower().size());
                break;
            case THREEPLAYERS:
                assertEquals(9, player.getPersonalSchool().getEntry().size());
                assertEquals(6, player.getPersonalSchool().getTower().size());
                break;
            default:
                break;
        }
    }

   @Test
   public void playerTest(){
        Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);

        assertNotNull(player.getCoinScore());
        assertEquals(0, player.getCoinScore());

        player.setNickname("Giocatore");
        player.setPlayerDate(new GregorianCalendar(1999, 03, 17));
        player.setAge(player.getPlayerDate());

        assertNotNull(player.getNickname());
        assertNotNull(player.getPlayerDate());
        assertNotNull(player.getAge());
        assertNotNull(player.getPlayerNumber());
        assertEquals("Giocatore", player.getNickname());
        assertEquals(1999, player.getPlayerDate().get(YEAR));
        assertEquals(03, player.getPlayerDate().get(MONTH));
        assertEquals(17, player.getPlayerDate().get(DAY_OF_MONTH));
        assertEquals(23, player.getAge());
        assertEquals(PlayerNumber.PLAYER1, player.getPlayerNumber());
   }

   @Test
   public void coinOfPlayer(){
       Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
       int count, coinValue = 0;
       assertEquals(0, player.getCoinScore());
       for (count = 0; count < 20; count++){
           player.setCoinScore(coinValue);
           assertNotNull(player.getCoinScore());
           assertEquals(count, player.getCoinScore());
           coinValue ++;
       }
   }

   @Test
   public void trashOfPlayer(){
       Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
       assertNull(player.getDeckOfPlayer());
       AssistantCard aCard = new AssistantCard("lion", 1, 1);
       player.setTrash(aCard);
       assertNotNull(player.getTrash());
       assertEquals(aCard, player.getTrash());

       aCard = new AssistantCard("turtle", 5, 10);
       player.setTrash(aCard);
       assertNotNull(player.getTrash());
       assertEquals(aCard, player.getTrash());
   }

   @Test
   public void tColorOfPlayer(){
       Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
       assertNotNull(player.getTColor());
       assertEquals(TColor.BLACK, player.getTColor());
       assertNotEquals(TColor.WHITE, player.getTColor());
       assertNotEquals(TColor.GREY, player.getTColor());

       /** bisogna inserire controllo che il colore grigio sia assegnato SOLO se 3 giocatori */
       player.setTColor(TColor.WHITE);
       assertNotNull(player.getTColor());
       assertEquals(TColor.WHITE, player.getTColor());
       assertNotEquals(TColor.BLACK, player.getTColor());
       assertNotEquals(TColor.GREY, player.getTColor());

       player.setTColor(TColor.GREY);
       assertNotNull(player.getTColor());
       assertEquals(TColor.GREY, player.getTColor());
       assertNotEquals(TColor.BLACK, player.getTColor());
       assertNotEquals(TColor.WHITE, player.getTColor());
    }

   @Test
   public void turnStateOfPlayer(){
       Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
       player.setTurnState(TurnState.PLANNING);
       assertNotNull(player.getTurnState());
       assertEquals(TurnState.PLANNING, player.getTurnState());
       player.setTurnState(TurnState.ACTION);
       assertNotNull(player.getTurnState());
       assertEquals(TurnState.ACTION, player.getTurnState());
       player.setTurnState(TurnState.NOTYOURTURN);
       assertNotNull(player.getTurnState());
       assertEquals(TurnState.NOTYOURTURN, player.getTurnState());
   }

   @Test
   public void deckOfPlayer(){
       Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
       DeckAssistant deckOfPlayer = new DeckAssistant();
       assertNull(player.getDeckOfPlayer());

       player.setDeckOfPlayer(deckOfPlayer);
       assertNotNull(player.getDeckOfPlayer());

       assertNotNull(player.getDeckOfPlayer().getCardsInHand());
       assertEquals(10, player.getDeckOfPlayer().getCardsInHand().size());
       int countCard = 0,countStepMotherEarth = 0,countTurnValue = 0,n=1;
       for(AssistantCard card : player.getDeckOfPlayer().getCardsInHand()){
           countStepMotherEarth = countStepMotherEarth + card.getStepMotherEarth();
           countTurnValue = countTurnValue + card.getTurnValue();
           countCard ++;
           for(int i = n; i < player.getDeckOfPlayer().getCardsInHand().size(); i++)
               assertNotEquals(card, player.getDeckOfPlayer().getCardsInHand().get(i));/**Checks that all the cards created are different */
           n++;
       }
       assertEquals(10, countCard);  /**Checks if are generated 10 cards and with the correct values */
       assertEquals(30, countStepMotherEarth);
       assertEquals(55, countTurnValue);

   }

   @Test
   public void influenceOnIsland(){
       Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
       int influence = 4;

       assertEquals(0, player.getInfluenceOnIsland());
       player.setInfluenceOnIsland(influence);
       assertNotNull(player.getInfluenceOnIsland());
       assertEquals(4, player.getInfluenceOnIsland());
   }

   @Test
   public void hasAlreadyPlayed(){
       Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
       assertNotNull(player.hasAlreadyPlayed());
       assertEquals(false, player.hasAlreadyPlayed());

       player.setHasAlreadyPlayed(true);
       assertEquals(true, player.hasAlreadyPlayed());
   }

}
