package it.polimi.ingsw.model.game;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void gameIsFinished() {
        GameController gameController = new GameController();
        Game game= new Game();
        Player player = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        Table table = new Table();
        DeckAssistant deckAssistant= new DeckAssistant();
        table.generateIslandCards();

        game.setActivePlayer(player);
        game.setTable(table);
        game.getActivePlayer().setDeckOfPlayer(deckAssistant);

        game.getTable().getBag().add(new Student(0, SColor.GREEN));

        game.getActivePlayer().getPersonalSchool().getTower().add(new Tower(0,TColor.WHITE));
        game.getActivePlayer().getPersonalSchool().getTower().add(new Tower(1,TColor.WHITE));
        game.getActivePlayer().getDeckOfPlayer().getCardsInHand().add(new AssistantCard("lion",3,4));

        assertEquals(false, game.gameIsFinished());

        gameController.setGameSession(game);
        gameController.playAssistantCard("lion");
        assertNotNull(gameController.getGameSession().getActivePlayer().getTrash());
        assertEquals("lion", gameController.getGameSession().getActivePlayer().getTrash().getAssistantName());
        assertEquals(true, game.gameIsFinished());  /** Control if return true when the player has no more card in his hand */

        gameController.getGameSession().getActivePlayer().getDeckOfPlayer().getCardsInHand().add(new AssistantCard("lion",3,4));

        gameController.getGameSession().getActivePlayer().getPersonalSchool().removeTower();
        assertEquals(false, game.gameIsFinished());
        gameController.getGameSession().getActivePlayer().getPersonalSchool().removeTower();
        assertEquals(true, game.gameIsFinished()); /** Control if return true when a player finishes his tower */

        gameController.getGameSession().getActivePlayer().getPersonalSchool().addTower(0,TColor.WHITE);

        /** MANCA CONTROLLO BAG VUOTA E 3 GRUPPI DI ISOLE */
    }
}