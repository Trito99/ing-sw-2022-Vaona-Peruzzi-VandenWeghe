package it.polimi.ingsw.controller;

import it.polimi.ingsw.message.*;
import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.game.*;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.network.ClientHandlerInterface;
import it.polimi.ingsw.network.LobbyServer;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {
    private GameController gc;
    private ArrayList <VirtualView> allViews;
    private VirtualView singleView;
    private VirtualView extraView;
    private ClientHandlerInterface clientHandler;
    HashMap<String, VirtualView> allVirtualView;
    private Game gameSession;
    private GameState gameState;
    int roundIndex;
    private DeckAssistant deckOfPlayer;
    private Table table;

    /**@BeforeEach
    void setup(){
        gc = new GameController();
        allViews = new ArrayList<>();
        LobbyServer lobbyServer = new LobbyServer();
        allVirtualView = new HashMap<>();
        gameSession = new Game();
        roundIndex = 0;

        clientHandler = new ClientHandlerInterface(){
            @Override
            public void disconnect() {
            }

            @Override
            public void sendMessage(GeneralMessage message) {
            }
        };

        for(int i = 0; i < 4; i++){
            allViews.add(new VirtualView(clientHandler));
        }
        singleView = new VirtualView(clientHandler);
        extraView = new VirtualView(clientHandler);

        table = new Table();
        deckOfPlayer = new DeckAssistant(AssistantDeckName.WIZARD1);
        table.addFinalStudents();
        table.generateIslandCards();
        table.generateMotherEarth();

        Player player = new Player(null,PlayerNumber.PLAYER1);
        player.setNickname("Gino");
        allVirtualView.put(player.getNickname(), singleView);
    }

    @Test
    void gameSession(){
        gameState = GameState.INIT;
        GameController gc = new GameController();
        gc.getGameSession();
        assertNotNull(gc.getGameSession());

        gc.setGameSession(gameSession);
        assertNotNull(gc.getGameSession());
        assertEquals(gameSession, gc.getGameSession());

        gc.setActionState(ActionState.MOTHERNATURE);
        assertNotNull(gc.getActionState());
        assertEquals(ActionState.MOTHERNATURE, gc.getActionState());

        gc.setActionState(ActionState.STUDENT);
        assertNotNull(gc.getActionState());
        assertEquals(ActionState.STUDENT, gc.getActionState());

        gc.setActionState(ActionState.CLOUDCARD);
        assertNotNull(gc.getActionState());
        assertEquals(ActionState.CLOUDCARD, gc.getActionState());
    }

    @Test
    void gameState(){
        GameController gc = new GameController();
        gameState = GameState.INIT;

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

    }

    @Test
    void planning(){
        GameController gc = new GameController();
        gameState = GameState.INIT;

        Player player1 = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        player1.setNickname("Gino");
        gameSession.addPlayer(player1);

        Player player2 = new Player(TColor.BLACK, PlayerNumber.PLAYER2);
        player2.setNickname("Paolo");
        gameSession.addPlayer(player2);

        Table table = new Table();
        DeckAssistant deckAssistant1 = new DeckAssistant(AssistantDeckName.WIZARD1);
        ArrayList<AssistantCard> assistantCards1 = new ArrayList<>();
        DeckAssistant deckAssistant2 = new DeckAssistant(AssistantDeckName.WIZARD2);
        ArrayList<AssistantCard> assistantCards2 = new ArrayList<>();
        table.generateIslandCards();

        gameSession.setTable(table);
        gameSession.getPlayer("Gino").setDeckOfPlayer(deckAssistant1);
        gameSession.getPlayer("Gino").getDeckOfPlayer().getCardsInHand().clear();
        gameSession.getPlayer("Paolo").setDeckOfPlayer(deckAssistant2);
        gameSession.getPlayer("Paolo").getDeckOfPlayer().getCardsInHand().clear();

        assertNotNull(gc.getGameState());

        gc.setGameState(GameState.PLANNING);

        assertNotNull(gc.getGameState());
        assertEquals(GameState.PLANNING, gc.getGameState());

        for(String s : allVirtualView.keySet()){
            if (!s.equals(gc.getActivePlayer())){
                assertNotNull(allVirtualView.get(s));
            }
        }
    }

    /** provo ad aggiungere 5 giocatori, e
    @Test
    void extraViewTest(){
        assertFalse(gc.isGameStarted());

        for(int i = 0; i < 3; i++){
            gc.newPlayer("" + i , "gameId", new GregorianCalendar(), allViews.get(i));
            assertEquals(i + 1, gc.getAllVirtualView().size());

            if(i == 0){
                gc.getMessage(new PlayersNumberAndDifficulty("0", 4, Difficulty.STANDARDMODE));
            }

            if(i == 1) assertEquals(2, gc.getAllVirtualView().size());
            if(i == 2) assertEquals(3, gc.getAllVirtualView().size());
        }

        for(int j = 0; j < 10; j++){
            gc.newPlayer("" + j, "gameId", new GregorianCalendar(), extraView);
        }

        assertEquals(4, gc.getAllVirtualView().size());
    }

    @Test
    void threePlayers(){
        assertFalse(gc.isGameStarted());
        assertEquals(GameState.INIT, gc.getGameState());

        for(int i = 0; i < 3; i++){
            gc.newPlayer("" + i , "gameId", new GregorianCalendar(), allViews.get(i));
            assertEquals(i + 1, gc.getAllVirtualView().size());


            if(i == 0){
                gc.getMessage(new PlayersNumberAndDifficulty("0", 3, Difficulty.STANDARDMODE));
            }
        }

        assertEquals(3, gc.getAllVirtualView().size());
        //assertTrue(gc.isGameStarted());
       //assertEquals(GameState.PLANNING, gc.getGameState());

        //gc.getMessage(new PlayersNumberAndDifficulty("", 3, Difficulty.STANDARDMODE));
        //assertEquals(Difficulty.STANDARDMODE, gc.getGameSession().getDifficulty());
    }

    @Test
    void twoPlayers(){
        assertFalse(gc.isGameStarted());
        assertEquals(GameState.INIT, gc.getGameState());

        for(int i = 0; i < 2; i++){
            gc.newPlayer("" + i , "gameId", new GregorianCalendar(), allViews.get(i));
            assertEquals(i + 1, gc.getAllVirtualView().size());


            if(i == 0){
                gc.getMessage(new PlayersNumberAndDifficulty("0", 2, Difficulty.STANDARDMODE));
            }
        }

        assertEquals(2, gc.getAllVirtualView().size());
        //assertTrue(gc.isGameStarted());
        //assertEquals(GameState.PLANNING, gc.getGameState());

        //gc.getMessage(new PlayersNumberAndDifficulty("", 2, Difficulty.STANDARDMODE));
        //assertEquals(Difficulty.STANDARDMODE, gc.getGameSession().getDifficulty());
    }

    @Test
    void gameController(){
        assertFalse(gc.isGameStarted());
        assertEquals(GameState.INIT, gc.getGameState());

        for(int i = 0; i < 4; i++){
            gc.newPlayer("" + i , "gameId", new GregorianCalendar(), allViews.get(i));
            assertEquals(i + 1, gc.getAllVirtualView().size());


            if(i == 0){
                gc.getMessage(new PlayersNumberAndDifficulty("0", 4, Difficulty.EXPERTMODE));
            }
        }

        assertEquals(4, gc.getAllVirtualView().size());
        //assertTrue(gc.isGameStarted());
        //assertEquals(GameState.PLANNING, gc.getGameState());

        //gc.getMessage(new PlayersNumberAndDifficulty("", 4, Difficulty.EXPERTMODE));
        //assertEquals(Difficulty.EXPERTMODE, gc.getGameSession().getDifficulty());

    }
    @Test
    void towerColorAndDeckChosen(){
        ClientMessage  message = new TowerColorAndDeckChosen("Gino", TColor.WHITE, AssistantDeckName.WIZARD3);
        gc.getMessage(message);
        assertEquals(TColor.WHITE, gameSession.getPlayer("Gino").getTColor());
        assertEquals(AssistantDeckName.WIZARD3, gameSession.getPlayer("Gino").getDeckOfPlayer().getDeckName());

    } */

}
