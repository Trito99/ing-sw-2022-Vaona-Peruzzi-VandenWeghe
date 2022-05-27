package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.message.ClientMessage;
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
        gameController = new GameController();
        this.gameId = gameId;
    }

    /** aggiungo nuovo giocatore alla Lobby */
    public void addPlayer(String nickname, GregorianCalendar playerDate, ClientHandler clientHandler){
        VirtualView virtualView = new VirtualView(clientHandler);

        if(!(isGameStarted())){
            int i = 1;
            String n = nickname;
            clientHandlerMap.put(clientHandler, n);
            while (clientHandlerMap.containsValue(n)) {
                n = nickname + "(" + i + ")";
                System.out.println(n);
                i++;
            }
            if(!gameController.newPlayer(n, gameId, playerDate, virtualView)){
                clientHandlerMap.remove(clientHandler);
            }
        }
        else{
            virtualView.showLogin(nickname, gameId, playerDate, false);
        }
    }


    /** controlla se il gioco è inattivo o in corso ----> return se il gioco è già cominciato */
    public boolean isGameStarted(){
        return gameController.isGameStarted();
    }

    /** return numero di giocatori connessi */
    public int currentPlayers(){
        return clientHandlerMap.size();
    }


    public void remove(){
        for (ClientHandler cl : clientHandlerMap.keySet()){
            cl.disconnect();
            clientHandlerMap.remove(cl);
        }
    }


    /** passa messaggio al GameController*/
    public void getMessage(ClientMessage clientMessage){
        gameController.getMessage(clientMessage);

    }


}
