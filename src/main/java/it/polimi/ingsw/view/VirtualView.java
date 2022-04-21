package it.polimi.ingsw.view;

import it.polimi.ingsw.message.GeneralMessage;
import it.polimi.ingsw.network.ClientHandlerInterface;
import it.polimi.ingsw.observer.Observer;

import java.util.ArrayList;

public class VirtualView implements View, Observer {
    private final ClientHandlerInterface clientHandler;

    /** costruttore della classe */
    /** @param clientHandler il clientHandler a cui la view invia messaggi */
    public VirtualView(ClientHandlerInterface clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public void showLogin(String username, String gameId, boolean wasJoined) {
        //clientHandler.sendMessage(new LoginReply(username, gameId, wasJoined));
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


    /** riceve un messaggio aggiornato dal model e lo invia tramite il network al client  */
    @Override
    public void update(GeneralMessage message) {
        clientHandler.sendMessage(message);
    }
}
