package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.view.VirtualView;

import java.util.*;

/** la classe tiene traccia di tutti i clients connessi al server che stanno giocando allo stesso gioco */

public class Lobby {

    private Map<ClientHandler, String> clientHandlerMap;
    private GameController gameController;
    private String gameId;

    /** costruttore di default */
    public Lobby(String gameId) {
        clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
        gameController=new GameController();
        this.gameId=gameId;
    }

    /** aggiungo nuovo giocatore alla Lobby */
    public void addPlayer(String username, ClientHandler clientHandler){
        VirtualView virtualView = new VirtualView(clientHandler);
        /**
        if(!(isGameStarted())){

            int i=1;
            String u = new String(username);
            // ...
            // stampo username così: (username)
            while (clientHandlerMap.containsValue(u)) {
                u = username + "(" + i + ")";
                System.out.println(u);
                i++;
            }
            clientHandlerMap.put(clientHandler, u);
            if(!gameController.newPlayer(u, gameId, virtualView)){
                clientHandlerMap.remove(clientHandler);
            }
        }
        else if(hasInactivePLayers()){
            List<String> inactive =getInactivePlayers();
            if(inactive.contains(username)){
                reconnect(username, clientHandler, virtualView);
            }
        }
        else{
            virtualView.showLogin(username, gameId, false);
        }
         */
    }



}
