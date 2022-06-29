package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.message.ClientMessage;
import it.polimi.ingsw.view.VirtualView;

import java.io.Serializable;
import java.util.*;

/**
 * Keeps track all the clients connected to a certain lobby, playing in the same match
 */
public class Lobby implements Serializable {
    private Map<ClientHandler, String> clientHandlerMap;
    private GameController gameController;
    private String gameId;

    /**
     * Default constructor
     * @param gameId id of the match
     */
    public Lobby(String gameId) {
        clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
        gameController = new GameController();
        this.gameId = gameId;
    }

    /**
     * Adds a new player to the lobby
     * @param nickname of the new player
     * @param playerDate birthday date of the player
     * @param clientHandler of the player
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
                /** case of players with the same nickname */
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
     * Checks if the Game is started
     * @return true if the game is started
     */
    public boolean isGameStarted(){
        return gameController.isGameStarted();
    }

    /**
     * Checks the current number of players connected
     * @return number of players connected
     */
    public int currentPlayers(){
        return clientHandlerMap.size();
    }

    /**
     * Removes a player from the lobby
     * @param clientHandler associated to a certain player
     */
    public void remove(ClientHandler clientHandler){
        clientHandlerMap.remove(clientHandler);
    }


    /**
     * Sends messages to the Game Controller
     * @param clientMessage message sent from the Client to the Game Controller
     */
    public void getMessage(ClientMessage clientMessage){
        gameController.getMessage(clientMessage);

    }

    public GameController getGameController() {
        return gameController;
    }
}
