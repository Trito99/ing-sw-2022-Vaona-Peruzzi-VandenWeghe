package it.polimi.ingsw.network;

import it.polimi.ingsw.message.*;
import it.polimi.ingsw.message.Error;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.View;

import java.util.GregorianCalendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMessanger implements ObserverView, Observer {
    private String nickname;
    private GregorianCalendar playerDate;
    private View view;
    private ExecutorService queue;
    private ClientSocket client;
    private String lobby;


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
            queue.execute(view::askLobby);
        } catch (Exception e) {
            queue.execute(view::askConnect);
        }
    }

    /** crea/aggiorna il nickname se un giocatore era già presente con lo stesso nome*/
    public void createNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void createPlayerDate(GregorianCalendar playerDate) {
        this.playerDate = playerDate;
    }

    /** comunica il numero di giocatori del gioco in corso */
    public void choosePlayersNumberAndDifficulty(int playersNumber, Difficulty difficulty) {
        client.sendMessage(new PlayersNumberAndDifficulty(nickname, playersNumber,difficulty));
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

    public void chooseCloudCard(int id){
        client.sendMessage(new CloudChosen(nickname,id));
    }

    public void choosePlaceAndStudentForMove(String place,int id){
        client.sendMessage(new PlaceAndStudentForMoveChosen(nickname,place,id));
    }

    public void chooseId(int id, boolean choice,int index){
        client.sendMessage(new IdChosen(nickname,id,choice,index));
    }

    public void chooseMotherEarthSteps(int steps, int maxSteps) {
        client.sendMessage(new MotherEarthStepsChosen(nickname, steps, maxSteps));
    }


    /** cerca di loggare un giocatore ad una data lobby */
    public void updateLobby(String nickname, GregorianCalendar playerDate, String lobby){
        this.nickname = nickname;
        this.lobby = lobby;
        this.playerDate = playerDate;
        client.sendMessage(new LoginRequest(nickname, lobby, playerDate));
    }

    public void endGame(){
        client.sendMessage(new EndGame(nickname));
    }

    public void updateDisconnect(){
        client.disconnect();
    }

    /** mostra un giocatore */
    public void updateShowPlayer(String player){
       // client.sendMessage(new ShowPlayerRequest(nickname, player));
    }

    /** mostra la nuova influenza di quel giocatore */
    public void updateShowInfluence(int influence){
        //client.sendMessage(new PlayerInfluence(nickname, player));
    }

    @Override
    public void update(GeneralMessage message) {
        switch (message.getMessageType()) {
            case LOGIN_RESULT:
                LoginResult loginMessage = (LoginResult) message;
                queue.execute(() -> view.showLogin(loginMessage.getNickname(), loginMessage.getGameId(), loginMessage.getPlayerDate(), loginMessage.wasJoined()));
                break;
            case SUCCESSFUL_HOST:
                queue.execute(() -> view.askPlayersNumberAndDifficulty());
                break;
            case SHOW_TABLE:
                ShowTable table = (ShowTable) message;
                queue.execute(() -> view.showTable(table.getTable(),table.getDifficulty()));
                break;
            case SHOW_PERSONAL_SCHOOL:
                ShowPersonalSchool player = (ShowPersonalSchool) message;
                queue.execute(() -> view.showPersonalSchool(player.getSchool(), player.getNickname(),player.getTrash(), player.getDifficulty(), player.getCoins()));
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
                queue.execute(() -> view.askAssistantCardToPlay());
                break;
            case CHOOSE_CLOUD_CARD:
                ChooseCloudCard tableTwo = (ChooseCloudCard) message;
                queue.execute(() -> view.askCloud(tableTwo.getTable()));
                break;
            case CHOOSE_PLACE_AND_STUDENT_FOR_MOVE:
                ChoosePlaceAndStudentForMove entry = (ChoosePlaceAndStudentForMove) message;
                queue.execute(() -> view.askPlaceAndStudentForMove(entry.getEntry()));
                break;
            case CHOOSE_ID:
                ChooseId id = (ChooseId) message;
                queue.execute(() -> view.askId(id.getChoice(),id.getCharacterCard(), id.getIndex(), id.getEntry()));
                break;
            case CHOOSE_COLOR_TO_BLOCK:
                queue.execute(() -> view.askColorToBlock());
                break;
            case CHOOSE_MOTHER_EARTH_STEPS:
                ChooseMotherEarthSteps steps = (ChooseMotherEarthSteps) message;
                queue.execute(() -> view.askMotherEarthSteps(steps.getMaxSteps()));
                break;
            case WIN:
                queue.execute(() -> view.showWinMessage());
                break;
            case ERROR:
                queue.execute(() -> view.showErrorMessage(((Error) message).getMessage()));
                break;
            case STRING_MESSAGE:
                queue.execute(() -> view.showMessage(((StringMessage) message).getMessage()));
                break;
            case LOSE:
                LoseMessage lose = (LoseMessage) message;
                queue.execute(() -> view.showLoseMessage(lose.getNickname()));
                client.disconnect();
                break;
        }
    }
}
