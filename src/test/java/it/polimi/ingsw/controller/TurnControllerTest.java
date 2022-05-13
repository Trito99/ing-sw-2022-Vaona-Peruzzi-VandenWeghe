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

    private GameController gameController = new GameController();
    private TurnController turnController;

    @RepeatedTest(1)
    void changeOrder(){
        for (int index = 0; index < 2; index++) {
            gameController.generateTable();
            Random random = new Random();

            for (int i = 0; i < index + 2; i++) {
                int r=random.nextInt(9);
                gameController.getGameSession().getListOfPlayers().add(new Player(TColor.values()[i], PlayerNumber.values()[i]));
                gameController.getGameSession().getListOfPlayers().get(i).generateSchool(gameController.getGameSession().getTable(), GameMode.values()[index]);
                gameController.getGameSession().getListOfPlayers().get(i).setNickname(Integer.toString(i));
                gameController.getGameSession().getListOfPlayers().get(i).setPlayerDate(new GregorianCalendar(i,i,i));
                gameController.getGameSession().getListOfPlayers().get(i).setDeckOfPlayer(new DeckAssistant(AssistantDeckName.values()[i]));
                gameController.getGameSession().playAssistantCard(gameController.getGameSession().getListOfPlayers().get(i).getAssistantCardByNickname().get(r),gameController.getGameSession().getListOfPlayers().get(i).getNickname());
            }

            //turnController = new TurnController(gameController);
            //turnController.changeOrder();

        }
    }
}
