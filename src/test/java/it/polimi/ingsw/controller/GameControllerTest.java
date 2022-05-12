package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.network.ClientHandler;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {
    HashMap<String, VirtualView> allVirtualView;
    Game gameSession;
    GameState gameState;
    int roundIndex;
    ClientHandler clientHandler;
    @BeforeEach
    void setup(){
        this.allVirtualView = new HashMap<>();
        gameSession = new Game();
        gameState = GameState.INIT;
        roundIndex = 0;

    }

    @Test
    void gameSession(){
        GameController gc = new GameController();
        gc.getGameSession();
        assertNotNull(gc.getGameSession());

        gc.setGameSession(gameSession);
        assertNotNull(gc.getGameSession());
        assertEquals(gameSession, gc.getGameSession());
    }

    @Test
    void gameState(){
        GameController gc = new GameController();

        gc.getGameState();
        assertNotNull(gc.getGameState());

        gc.setGameState(GameState.INIT);
        assertNotNull(gc.getGameState());
        assertEquals(GameState.INIT, gc.getGameState());

        gc.setGameState(GameState.PLANNING);
        assertNotNull(gc.getGameState());
        assertEquals(GameState.PLANNING, gc.getGameState());

        gc.setGameState(GameState.ACTION);
        assertNotNull(gc.getGameState());
        assertEquals(GameState.ACTION, gc.getGameState());

        gc.setGameState(GameState.IN_GAME);
        assertNotNull(gc.getGameState());
        assertEquals(GameState.IN_GAME, gc.getGameState());

        gc.setGameState(GameState.END_GAME);
        assertNotNull(gc.getGameState());
        assertEquals(GameState.END_GAME, gc.getGameState());

        /** test in disconnect
        Game game = new Game();
        Player player1 = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        player1.setNickname("Pippo");
        player1.setPlayerDate(new GregorianCalendar(2000, 01, 01));
        game.addPlayer(player1);
        Player player2 = new Player(TColor.BLACK, PlayerNumber.PLAYER2);
        player2.setNickname("Pluto");
        player2.setPlayerDate(new GregorianCalendar(2001, 01, 01));
        game.addPlayer(player2);
        Player player3 = new Player(TColor.GREY, PlayerNumber.PLAYER3);
        player3.setNickname("Paperino");
        player3.setPlayerDate(new GregorianCalendar(2002, 01, 01));
        game.addPlayer(player3);

        TurnController turn = new TurnController(gc);
        gc.startTurn();
        gc.setGameState(GameState.INIT);
        gc.disconnect("Pippo");
        assertEquals(GameState.IN_GAME, gc.getGameState());
         */
    }

    @Test
    void planning(){
        GameController gc = new GameController();

        Player player1 = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        player1.setNickname("Gino");
        gameSession.addPlayer(player1);

        Player player2 = new Player(TColor.BLACK, PlayerNumber.PLAYER2);
        player2.setNickname("Paolo");
        gameSession.addPlayer(player2);

        Table table = new Table();
        DeckAssistant deckAssistant1 = new DeckAssistant(AssistantDeckName.DECK1);
        ArrayList<AssistantCard> assistantCards1 = new ArrayList<>();
        DeckAssistant deckAssistant2 = new DeckAssistant(AssistantDeckName.DECK2);
        ArrayList<AssistantCard> assistantCards2 = new ArrayList<>();
        table.generateIslandCards();

        gameSession.setTable(table);
        gameSession.getPlayer("Gino").setDeckOfPlayer(deckAssistant1);
        gameSession.getPlayer("Gino").getDeckOfPlayer().getCardsInHand().clear();
        gameSession.getPlayer("Paolo").setDeckOfPlayer(deckAssistant2);
        gameSession.getPlayer("Paolo").getDeckOfPlayer().getCardsInHand().clear();

        assertNotNull(gc.getGameState());

        gc.setGameState(GameState.PLANNING);
        gc.planning();

        assertNotNull(gc.getGameState());
        assertEquals(GameState.PLANNING, gc.getGameState());

        for(String s : allVirtualView.keySet()){
            if (!s.equals(gc.getActivePlayer())){
                assertNotNull(allVirtualView.get(s));
            }
        }
    }

}
