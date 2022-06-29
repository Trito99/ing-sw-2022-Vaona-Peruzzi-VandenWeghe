package it.polimi.ingsw.network;

import it.polimi.ingsw.message.*;
import it.polimi.ingsw.message.Error;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.View;

import java.util.GregorianCalendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Gets inputs from the View and notifies the view and sends them as messages
 * Also notifies the View that a certain message has arrived
 */
public class ClientMessanger implements ObserverView, Observer {
    private String nickname;
    private final View view;
    private final ExecutorService queue;
    private ClientSocket client;

    /**
     * Default constructor
     * @param view to interact with
     */
    public ClientMessanger(View view){
        this.view = view;
        queue = Executors.newSingleThreadExecutor();
    }

    /**
     * Tries to connect a client (new player) to a certain server socket
     * @param address ip to connect to
     * @param port to connect to
     */
    public void updateConnect(String address, int port){
        try {
            client = new ClientSocket(address, port);
            client.add(this);
            client.listen();
            askLobbyServerInfo();
        } catch (Exception e) {
            queue.execute(view::askConnect);
        }
    }

    /**
     * Tries to log the infos of the lobby server
     */
    public void askLobbyServerInfo(){
        client.sendMessage(new LobbyServerRequest());
    }

    /**
     * Sets the player's nickname
     * @param nickname of the player
     */
    public void createNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Sends the game mode and the difficulty mode chosen by the player
     * @param playersNumber numbers of players of the match
     * @param difficulty mode of the match
     */
    public void choosePlayersNumberAndDifficulty(int playersNumber, Difficulty difficulty) {
        client.sendMessage(new PlayersNumberAndDifficulty(nickname, playersNumber,difficulty));
    }

    /**
     * Sends the tower's color and the Assistant Deck chosen by the player
     * @param towerColorChosen by the player
     * @param assistantDeckChosen by the player
     */
    public void chooseTowerColorAndDeck(TColor towerColorChosen, AssistantDeckName assistantDeckChosen) {
        client.sendMessage(new TowerColorAndDeckChosen(nickname, towerColorChosen, assistantDeckChosen));
    }

    /**
     * Sends the Character Card chosen by the player
     * @param cardNickname name of the Character Card chosen
     * @param choice set true when the card is activated
     */
    public void chooseCharacterCard(String cardNickname, boolean choice){
        client.sendMessage(new CharacterCardPlayed(nickname, cardNickname, choice));
    }

    /**
     * Handles the effect of the Herbalist Card and Junkdealer Card
     * Sends the color selected by the player
     * @param color of the students to block in the influence calculation or to remove from the entry
     */
    @Override
    public void chooseColorToBlock(String color) {
        client.sendMessage(new ColorBlocked(nickname, color));
    }

    /**
     * Sends the Assistant Card selected chosen by the player
     * @param cardNickname string associated to a certain Assistant Card
     */
    public void chooseAssistantCard(String cardNickname){
        client.sendMessage(new AssistantCardPlayed(nickname, cardNickname));
    }

    /**
     * Send the Cloud Card chosen by the player
     * @param id of the cloud card parsed
     * @param string of a character card (can be null)
     */
    public void chooseCloudCard(int id, String string){
        client.sendMessage(new CloudChosen(nickname, id, string));
    }

    /**
     * Sends the new position of a student chosen by the player during the Action phase
     * @param place where the player wants to move the student
     * @param id of the student to move
     */
    public void choosePlaceAndStudentForMove(String place, int id){
        client.sendMessage(new PlaceAndStudentForMoveChosen(nickname, place, id));
    }

    /**
     * Sends a Character Card's effect which involves moving a certain student
     * @param id of the student to move
     * @param choice true for herald and curator effect, otherwise false
     * @param index for the effect of the Acrobat Card
     * @param none boolean used to finish the effect for the Acrobat Card (it can lasts up to 3 times)
     */
    public void chooseId(int id, boolean choice,int index, boolean none){
        client.sendMessage(new IdChosen(nickname, id, choice, index, none));
    }

    /**
     * Sends the new position of Mother Earth chosen by the player
     * @param steps of Mother Earth chosen
     * @param maxSteps maximum number of steps in accord to the value of the last assistant card chosen by the player
     * @param string of a character card (can be null)
     */
    public void chooseMotherEarthSteps(int steps, int maxSteps, String string) {
        client.sendMessage(new MotherEarthStepsChosen(nickname, steps, maxSteps, string));
    }

    /**
     * Tries to log a player in a certain lobby
     */
    public void updateLobby(String nickname, GregorianCalendar playerDate, String lobby){
        this.nickname = nickname;
        client.sendMessage(new LoginRequest(nickname, lobby, playerDate));
    }

    /**
     * Disconnects a client
     */
    public void updateDisconnect(){
        client.disconnect();
    }

    @Override
    public void createPlayerDate(GregorianCalendar playerDate) {
    }

    /**
     * Notifies the view when a message is received
     * @param message specific message received
     */
    @Override
    public void update(GeneralMessage message) {
        switch (message.getMessageType()) {
            case LOBBY_SERVER_INFO:
                LobbyServerInfo lobbyServerInfo = (LobbyServerInfo) message;
                queue.execute(() -> view.askLobby(lobbyServerInfo.getLobbyMap()));
                break;
            case LOGIN_RESULT:
                LoginResult loginMessage = (LoginResult) message;
                queue.execute(() -> view.showLogin(loginMessage.getNickname(), loginMessage.getGameId(), loginMessage.getPlayerDate(), loginMessage.wasJoined()));
                break;
            case SUCCESSFUL_HOST:
                queue.execute(view::askPlayersNumberAndDifficulty);
                break;
            case SHOW_TABLE:
                ShowTable table = (ShowTable) message;
                queue.execute(() -> view.showTable(table.getTable(),table.getDifficulty()));
                break;
            case SHOW_LIST_OF_ISLANDS:
                ShowListOfIslands showListOfIslands = (ShowListOfIslands) message;
                queue.execute(() -> view.showListOfIsland(showListOfIslands.getTable(), showListOfIslands.getDifficulty()));
                break;
            case SHOW_COIN:
                ShowCoin showCoin = (ShowCoin) message;
                queue.execute(() -> view.showCoin(showCoin.getCoins()));
                break;
            case SHOW_PERSONAL_SCHOOL:
                ShowPersonalSchool player = (ShowPersonalSchool) message;
                queue.execute(() -> view.showPersonalSchool(player.getSchool(), player.getNickname(),player.getTrash(), player.getDifficulty(), player.getCoins(), player.getGameMode(), player.getTeamMate()));
                break;
            case SHOW_ASSISTANT_DECK:
                ShowAssistantDeck deck = (ShowAssistantDeck) message;
                queue.execute(() -> view.showDeckAssistant(deck.getDeckAssistant()));
                break;
            case PLAY_CHARACTER_CARD:
                PlayCharacterCard play = (PlayCharacterCard) message;
                queue.execute(() -> view.askCharacterCardToPlay(play.getChoice(), play.getCoins() , play.getList()));
                break;
            case PLAY_ASSISTANT_CARD:
                queue.execute(view::askAssistantCardToPlay);
                break;
            case CHOOSE_CLOUD_CARD:
                ChooseCloudCard tableTwo = (ChooseCloudCard) message;
                queue.execute(() -> view.askCloud(tableTwo.getTable()));
                break;
            case CHOOSE_PLACE_AND_STUDENT_FOR_MOVE:
                ChoosePlaceAndStudentForMove entry = (ChoosePlaceAndStudentForMove) message;
                queue.execute(() -> view.askPlaceAndStudentForMove(entry.getEntry()));
                break;
            case CHOOSE_TOWER_COLOR_AND_DECK:
                ChooseTowerColorAndDeck choice = (ChooseTowerColorAndDeck) message;
                queue.execute(() -> view.askTowerColorAndDeck(choice.getTowerColors(),choice.getAssistantDeckNames()));
                break;
            case CHOOSE_ID:
                ChooseId id = (ChooseId) message;
                queue.execute(() -> view.askId(id.getChoice(),id.getCharacterCard(), id.getIndex(), id.getSchool()));
                break;
            case CHOOSE_COLOR_TO_BLOCK:
                ChooseColor color = (ChooseColor) message;
                queue.execute(() -> view.askColorToBlock(color.getCardEffect()));
                break;
            case CHOOSE_MOTHER_EARTH_STEPS:
                ChooseMotherEarthSteps steps = (ChooseMotherEarthSteps) message;
                queue.execute(() -> view.askMotherEarthSteps(steps.getMaxSteps(), steps.getTable(), steps.getDifficulty()));
                break;
            case WIN:
                queue.execute(view::showWinMessage);
                break;
            case ERROR:
                queue.execute(() -> view.showErrorMessage(((Error) message).getMessage()));
                break;
            case STRING_MESSAGE:
                queue.execute(() -> view.showMessage(((StringMessage) message).getMessage()));
                break;
            case WAITING_MESSAGE:
                queue.execute(() -> view.showWaitingMessage(((StringWaitingMessage) message).getMessage()));
                break;
            case LOSE:
                LoseMessage lose = (LoseMessage) message;
                queue.execute(() -> view.showLoseMessage(lose.getNickname()));
                break;
        }
    }
}
