package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.message.ClientMessage;
import it.polimi.ingsw.view.VirtualView;

import java.io.Serializable;
import java.util.*;

/** la classe tiene traccia di tutti i clients connessi al server che stanno giocando allo stesso gioco */

public class Lobby implements Serializable {

    private Map<ClientHandler, String> clientHandlerMap;
    private GameController gameController;
    private String gameId;

    /** costruttore di default */
    public Lobby(String gameId) {
        clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
        gameController = new GameController();
        this.gameId = gameId;
    }

    /** add a new player to the lobby
     *
     * @param nickname of new player
     * @param playerDate
     * @param clientHandler
     */
    public void addPlayer(String nickname, GregorianCalendar playerDate, ClientHandler clientHandler){
        VirtualView virtualView = new VirtualView(clientHandler);

        if(!(isGameStarted())){
            int i = 1;
            String n = nickname + "(" + i + ")";

            if(!(clientHandlerMap.containsValue(n))) {
                clientHandlerMap.put(clientHandler, n);
                System.out.println(n);
            }
            else{
                /** case name with the same nickname */
                while (clientHandlerMap.containsValue(n)) {
                    n = nickname + "(" + i + ")";
                    i++;
                }

                clientHandlerMap.put(clientHandler, n);
            }

            if(!gameController.newPlayer(n, gameId, playerDate, virtualView)){
                clientHandlerMap.remove(clientHandler);
            }
        }
        else{
            virtualView.showLogin(nickname, gameId, playerDate, false);
        }
    }


    /**
     *
     * @return true if the game is started
     */
    public boolean isGameStarted(){
        return gameController.isGameStarted();
    }

    /**
     *
     * @return number of player connected
     */
    public int currentPlayers(){
        return clientHandlerMap.size();
    }


    /** remove a client handler from the map
     *
     * @param clientHandler
     */
    public void remove(ClientHandler clientHandler){
        clientHandlerMap.remove(clientHandler);
    }


    /** send the message to the gameController
     *
     * @param clientMessage
     */
    public void getMessage(ClientMessage clientMessage){
        gameController.getMessage(clientMessage);

    }

    public GameController getGameController() {
        return gameController;
    }

}
