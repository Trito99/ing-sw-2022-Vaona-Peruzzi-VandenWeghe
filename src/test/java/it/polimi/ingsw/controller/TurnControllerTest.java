package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.TColor;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class TurnControllerTest {
    private GameController gc = new GameController();

    @RepeatedTest(1)
    void changeOrder(){
        for (int index = 0; index < 2; index++) {
            gc.generateTable();
            Random random = new Random();

            for (int i = 0; i < index + 2; i++) {
                int r=random.nextInt(9);
                gc.getGameSession().getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                gc.getGameSession().getListOfPlayers().get(i).generateSchool(gc.getGameSession().getTable(), GameMode.values()[index]);
                gc.getGameSession().getListOfPlayers().get(i).setNickname(Integer.toString(i));
                gc.getGameSession().getListOfPlayers().get(i).setPlayerDate(new GregorianCalendar(i,i,i));
                gc.getGameSession().getListOfPlayers().get(i).setDeckOfPlayer(new DeckAssistant(AssistantDeckName.values()[i]));
                gc.getGameSession().playAssistantCard(gc.getGameSession().getListOfPlayers().get(i).getAssistantCardByNickname().get(r),gc.getGameSession().getListOfPlayers().get(i).getNickname());
            }

            //turnController = new TurnController(gameController);
            //turnController.changeOrder();

        }
    }

    /**
     @Test
     void getActivePlayer(){
     Game gameSession = new Game();
     Player player1 = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
     player1.setNickname("Pippo");
     player1.setPlayerDate(new GregorianCalendar(2000, 01, 01));
     gameSession.addPlayer(player1);
     Player player2 = new Player(TColor.BLACK, PlayerNumber.PLAYER2);
     player2.setNickname("Pluto");
     player2.setPlayerDate(new GregorianCalendar(2001, 01, 01));
     gameSession.addPlayer(player2);
     Player player3 = new Player(TColor.GREY, PlayerNumber.PLAYER3);
     player3.setNickname("Paperino");
     player3.setPlayerDate(new GregorianCalendar(2002, 01, 01));
     gameSession.addPlayer(player3);

     gc.setGameState(GameState.INIT);
     TurnController turn = new TurnController(gc);
     turn.setPlayingPlayer("Pippo");
     assertEquals("Pippo", turn.getActivePlayer());

     gc.disconnect();
     assertEquals(false, gc.isGameStarted());
     assertNotEquals("Pippo", turn.getActivePlayer());
     }
     */
}
