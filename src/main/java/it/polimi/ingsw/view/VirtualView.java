package it.polimi.ingsw.view;

import it.polimi.ingsw.message.*;
import it.polimi.ingsw.message.Error;
import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.network.ClientHandlerInterface;
import it.polimi.ingsw.observer.Observer;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class VirtualView implements View, Observer {
    private final ClientHandlerInterface clientHandler;

    /** costruttore della classe */
    /** @param clientHandler il clientHandler a cui la view invia messaggi */
    public VirtualView(ClientHandlerInterface clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public void showLogin(String username, String gameId, GregorianCalendar playerDate, boolean wasJoined) {
        clientHandler.sendMessage(new LoginResult(username, gameId, playerDate, wasJoined));
    }

    @Override
    public void showMessage(String message) {
        clientHandler.sendMessage(new StringMessage(message));
    }

    @Override
    public void showPlayerInfluence(int influence) {
        //clientHandler.sendMessage(new ShowPlayerInfluence(influence));
    }

    @Override
    public void showPlayerTurn(String activePlayer) {
        //clientHandler.sendMessage(new ShowPlayerTurnMsg(activePlayer));
    }

    @Override
    public void showPlayerList(ArrayList<String> players) {
        //clientHandler.sendMessage(new ShowPlayerListMsg(players));
    }

    @Override
    public void showWinMessage() {
        clientHandler.sendMessage(new WinMessage());
    }

    @Override
    public void showLoseMessage(String nickname) {
        clientHandler.sendMessage(new LoseMessage(nickname));
    }

    @Override
    public void showErrorMessage(String message) {
        clientHandler.sendMessage(new Error(message));
    }

    @Override
    public void showPlayer(String nickname, PlayerNumber playerNumber, TColor tColor, int influenceOnIsland, School personalSchool, DeckAssistant deckOfPlayer, AssistantCard trash, int coinscore, String player) {
        clientHandler.sendMessage(new ShowPlayerInfo(nickname, playerNumber, tColor, influenceOnIsland, personalSchool, deckOfPlayer,trash, coinscore));
    }


    @Override
    public void showPersonalSchool(School school, String nickname, AssistantCard trash) {
        clientHandler.sendMessage(new ShowPersonalSchool(school, nickname, trash));
    }

    @Override
    public void showTable(Table table, Difficulty difficulty) {
        clientHandler.sendMessage(new ShowTable(table, difficulty));
    }

    @Override
    public void showDeckAssistant(DeckAssistant deckAssistant) {
        clientHandler.sendMessage(new ShowAssistantDeck(deckAssistant));
    }

    @Override
    public void showWinMessage(int numberOfTower) {

    }

    @Override
    public void askAction() {

    }

    @Override
    public void askConnect() {

    }

    @Override
    public void askLobby() { }

    @Override
    public void askPlayersNumberAndDifficulty() {
        clientHandler.sendMessage(new HostGameReply());
    }

    @Override
    public void askCharacterCardToPlay() {
        clientHandler.sendMessage(new PlayCharacterCard());
    }

    @Override
    public void askAssistantCardToPlay() {
        clientHandler.sendMessage(new PlayAssistantCard());
    }

    @Override
    public void askMotherEarthSteps(AssistantCard trash) {
        clientHandler.sendMessage(new ChooseMotherEarthSteps(trash));
    }

    @Override
    public void askCloud(Table table) {
        clientHandler.sendMessage(new ChooseCloudCard(table));
    }

    @Override
    public void askPlaceAndStudentForMove(ArrayList<Student> entry) {
        clientHandler.sendMessage(new ChoosePlaceAndStudentForMove(entry));
    }


    @Override
    public void askIdIsland() {
        clientHandler.sendMessage(new ChooseIdIsland());
    }



    /** riceve un messaggio aggiornato dal model e lo invia tramite il network al client  */
    @Override
    public void update(GeneralMessage message) {
        clientHandler.sendMessage(message);
    }

}
