package it.polimi.ingsw.view;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.observer.ObservableView;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Cli extends ObservableView implements View {
    private PrintStream output;
    private Thread inputThread;
    private List<String> commandList;

    public Cli(){
        output = System.out;
        commandList=new ArrayList<String>();
        /**for(Command command: Command.values()) {
            commandList.add(command.getVal());
        }*/
    }

    public void start(){
        /** ... */
    }

    @Override
    public void showLogin(String nickname, String gameId, boolean wasJoined) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showPlayerInfluence(int influence) {

    }

    @Override
    public void showPlayerTurn(String activePlayer) {

    }

    @Override
    public void showPlayerList(ArrayList<String> playerOrder) {

    }

    @Override
    public void showWinMessage() {

    }

    @Override
    public void showLoseMessage() {

    }

    @Override
    public void showPlayer(String nickname, PlayerNumber playerNumber, TColor tColor, int influenceOnIsland, School personalSchool, DeckAssistant deckOfPlayer, AssistantCard trash, int coinscore, String player) {

    }

    @Override
    public void showPersonalSchool(School school) {

    }

    @Override
    public void showTable(Table table) {

    }

    @Override
    public void askAction() {

    }

    @Override
    public void askPlayersNumber() {

    }

    @Override
    public void askGameMode(Game game) {

    }

    @Override
    public void askAssistantCardToPlay(ArrayList<CharacterCard> characterCards) {

    }

    @Override
    public void askStudentsToMove(ArrayList<Student> studentsInHall, School school, Table table) {

    }

    @Override
    public void askCharacterCardToPlay(Table table) {

    }

    @Override
    public void askMotherEarthSteps(Table table) {

    }

    @Override
    public void askChooseCloud(Table table) {

    }
}
