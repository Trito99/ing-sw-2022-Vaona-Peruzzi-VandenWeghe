package it.polimi.ingsw.model.game;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.assistant.DeckName;
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
        Game game= new Game();
        Player player = new Player(TColor.WHITE, PlayerNumber.PLAYER1);
        player.setNickname("Gino");
        game.getListOfPlayers().add(player);
        Table table = new Table();
        DeckAssistant deckAssistant = new DeckAssistant(DeckName.DECK1);
        table.generateIslandCards();

        game.setTable(table);
        game.getPlayer("Gino").setDeckOfPlayer(deckAssistant);
        game.getPlayer("Gino").getDeckOfPlayer().getCardsInHand().clear();

        game.getTable().getBag().add(new Student(0, SColor.GREEN));

        game.getPlayer("Gino").getPersonalSchool().getTower().add(new Tower(0,TColor.WHITE));
        game.getPlayer("Gino").getPersonalSchool().getTower().add(new Tower(1,TColor.WHITE));
        game.getPlayer("Gino").getDeckOfPlayer().getCardsInHand().add(new AssistantCard("lion",3,4));

        assertEquals(false, game.gameIsFinished("Gino"));


        game.playAssistantCard("lion", "Gino");
        assertNotNull(game.getPlayer("Gino").getTrash());
        assertEquals("lion", game.getPlayer("Gino").getTrash().getAssistantName());
        assertEquals(true, game.gameIsFinished("Gino"));  /** Control if return true when the player has no more card in his hand */

        game.getPlayer("Gino").getDeckOfPlayer().getCardsInHand().add(new AssistantCard("lion",3,4));

        game.getPlayer("Gino").getPersonalSchool().removeTower();
        assertEquals(false, game.gameIsFinished("Gino"));
        game.getPlayer("Gino").getPersonalSchool().removeTower();
        assertEquals(true, game.gameIsFinished("Gino")); /** Control if return true when a player finishes his tower */

        game.getPlayer("Gino").getPersonalSchool().addTower(0,TColor.WHITE);

        /** MANCA CONTROLLO BAG VUOTA E 3 GRUPPI DI ISOLE */
    }
}