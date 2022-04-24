package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
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
    }

    @Test
    public void generateSchool(){
        //Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
        //player.generateSchool(table, gameMode);
        //assertNull(player.getPersonalSchool());
        //assertNotNull(player.getPersonalSchool());
        return;
    }

   @Test
   public void playerTest(){
        Player player = new Player(TColor.BLACK, PlayerNumber.PLAYER1);
        AssistantCard aCard = new AssistantCard("folletto", 1, 1);
        int coinValue = 4;

        assertNotNull(player.getCoinScore());
        assertEquals(0, player.getCoinScore());

        player.setNickname("Giocatore");
        player.setPlayerDate(new GregorianCalendar(1999, 03, 17));
        player.setAge(player.getPlayerDate());
        player.setTurnState(TurnState.PLANNING);
        player.setTrash(aCard);
        player.setCoinScore(coinValue);

        assertNotNull(player.getNickname());
        assertNotNull(player.getPlayerDate());
        assertNotNull(player.getAge());
        assertNotNull(player.getPlayerNumber());
        assertNotNull(player.getTColour());
        assertNotNull(player.getTurnState());
        assertNotNull(player.getTrash());
        assertEquals("Giocatore", player.getNickname());
        assertEquals(1999, player.getPlayerDate().get(YEAR));
        assertEquals(03, player.getPlayerDate().get(MONTH));
        assertEquals(17, player.getPlayerDate().get(DAY_OF_MONTH));
        assertEquals(23, player.getAge());
        assertEquals(PlayerNumber.PLAYER1, player.getPlayerNumber());
        assertEquals(TurnState.PLANNING, player.getTurnState());
        assertEquals(aCard, player.getTrash());
        assertEquals(TColor.BLACK, player.getTColour());
        assertNotEquals(TColor.WHITE, player.getTColour());
        assertEquals(4, player.getCoinScore());

        player.setTurnState(TurnState.NOTYOURTURN);
        assertEquals(TurnState.NOTYOURTURN, player.getTurnState());
        /** bisogna inserire controllo che il colore grigio sia assegnato SOLO se 3 giocatori */
        player.setTColour(TColor.WHITE);
        assertEquals(TColor.WHITE, player.getTColour());


   }
   


}
