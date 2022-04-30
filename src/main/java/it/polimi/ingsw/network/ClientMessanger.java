package it.polimi.ingsw.network;

import it.polimi.ingsw.message.*;
import it.polimi.ingsw.message.Error;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.observer.ObserverView;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.message.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMessanger implements ObserverView, Observer {
    private String nickname;
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

    /** cerca di loggare un giocatore ad una data lobby */
    public void updateLobby(String nickname, String lobby){
        this.nickname = nickname;
        this.lobby=lobby;
        client.sendMessage(new LoginRequest(nickname, lobby));
    }

    /** aggiorna il nuovo nickname, se un giocatore con lo stesso nome è già nel gioco */
    public void updateNewUsername(String nickname){
        this.nickname = nickname;
    }

    /** invia il numero di giocatori al gioco corrente */
    public void updatePlayersNumber(int numPlayers){
        client.sendMessage(new PlayersNumber(nickname, numPlayers));
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
                queue.execute(() -> view.showLogin(loginMessage.getNickname(), loginMessage.getGameId(), loginMessage.wasJoined()));
                break;
            case SUCCESSFUL_HOST:
                queue.execute(() -> view.askPlayersNumber());
                break;
            case START_TURN:
                queue.execute(() -> view.askAction());
                break;
            case WIN: /** da controllare */
                queue.execute(() -> view.showWinMessage(((WinMessage) message).getNumberOfTower()));
                queue.execute(() -> view.showWinMessage(((WinMessage) message).getNumberOfIsland()));
                queue.execute(() -> view.showWinMessage(((WinMessage) message).getLastStudents()));
                break;
            case ERROR:
                queue.execute(()-> view.showErrorMessage(((Error) message).getMessage()));
                break;
            case STRING_MESSAGE:
                queue.execute(()-> view.showMessage(((StringMessage) message).getMessage()));
                break;
            case LOSE:
                queue.execute(()-> view.showLoseMessage());
                client.disconnect();
            case SHOW_PLAYER:
                ShowPlayerInfo player = (ShowPlayerInfo) message;
                queue.execute(() -> view.showPlayer(player.getNickname(), player.getPlayerNumber(), player.gettColor(), player.getInfluenceOnIsland(),
                        player.getPersonalSchool(), player.getDeckOfPlayer(), player.getTrash(), player.getCoinScore(), nickname));
                break;
            case SHOW_PLAYER_INFLUENCE:
                queue.execute(()->view.showPlayerInfluence(((ShowPlayerInfluence) message).getInfluence()));
                break;

        }
    }
}
