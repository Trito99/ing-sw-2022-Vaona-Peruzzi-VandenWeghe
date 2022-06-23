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

public class ClientMessanger implements ObserverView, Observer {
    private String nickname;
    private final View view;
    private final ExecutorService queue;
    private ClientSocket client;


    /** costruttore di default */
    public ClientMessanger(View view){
        this.view = view;
        queue = Executors.newSingleThreadExecutor();
    }

    /** prova a connettere un client alla socket */
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

    public void askLobbyServerInfo(){
        client.sendMessage(new LobbyServerRequest());
    }

    /** crea/aggiorna il nickname se un giocatore era già presente con lo stesso nome*/
    public void createNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void createPlayerDate(GregorianCalendar playerDate) {
    }

    /** comunica il numero di giocatori del gioco in corso */
    public void choosePlayersNumberAndDifficulty(int playersNumber, Difficulty difficulty) {
        client.sendMessage(new PlayersNumberAndDifficulty(nickname, playersNumber,difficulty));
    }

    public void chooseTowerColorAndDeck(TColor towerColorChosen, AssistantDeckName assistantDeckChosen) {
        client.sendMessage(new TowerColorAndDeckChosen(nickname, towerColorChosen, assistantDeckChosen));
    }

    public void chooseCharacterCard(String cardNickname, boolean choice){
        client.sendMessage(new CharacterCardPlayed(nickname, cardNickname, choice));
    }

    @Override
    public void chooseColorToBlock(String color) {
        client.sendMessage(new ColorBlocked(nickname, color));
    }

    public void chooseAssistantCard(String cardNickname){
        client.sendMessage(new AssistantCardPlayed(nickname, cardNickname));
    }

    public void chooseCloudCard(int id, String string){
        client.sendMessage(new CloudChosen(nickname, id, string));
    }

    public void choosePlaceAndStudentForMove(String place, int id){
        client.sendMessage(new PlaceAndStudentForMoveChosen(nickname, place, id));
    }

    public void chooseId(int id, boolean choice,int index, boolean none){
        client.sendMessage(new IdChosen(nickname, id, choice, index, none));
    }

    public void chooseMotherEarthSteps(int steps, int maxSteps, String string) {
        client.sendMessage(new MotherEarthStepsChosen(nickname, steps, maxSteps, string));
    }


    /** cerca di loggare un giocatore ad una data lobby */
    public void updateLobby(String nickname, GregorianCalendar playerDate, String lobby){
        this.nickname = nickname;
        client.sendMessage(new LoginRequest(nickname, lobby, playerDate));
    }

    public void updateDisconnect(){
        client.disconnect();
    }

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
