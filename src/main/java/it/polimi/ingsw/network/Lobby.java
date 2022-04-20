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
        gameController = new GameController();
        this.gameId = gameId;
    }

    /** aggiungo nuovo giocatore alla Lobby */
    public void addPlayer(String nickname, ClientHandler clientHandler){
        VirtualView virtualView = new VirtualView(clientHandler);

        if(!(isGameStarted())){

            int i=1;
            String n = new String(nickname);
            /** ... */
            /** stampo nickname così: nickname(1) / nickname(2) / ecc. */
            while (clientHandlerMap.containsValue(n)) {
                n = nickname + "(" + i + ")";
                System.out.println(n);
                i++;
            }
            clientHandlerMap.put(clientHandler, n);
            if(!gameController.newPlayer(n, gameId, virtualView)){
                clientHandlerMap.remove(clientHandler);
            }
        }

        else if(hasInactivePLayers()){
            List<String> inactive = getInactivePlayers();
            if(inactive.contains(nickname)){
                reconnect(nickname, clientHandler, virtualView);
            }
        }
        else{
            virtualView.showLogin(nickname, gameId, false);
        }
    }

    /** controlla se il gioco è inattivo o in corso ----> return se il gioco è già cominciato */
    public boolean isGameStarted(){
        return gameController.isGameStarted();
    }

    /** controlla se ci sono giocatori inattivi ----> return true */
    public boolean hasInactivePLayers(){
        return gameController.hasInactivePlayers();
    }

    /** controlla lista di giocatori inattivi  ----> return lista di nickname */
    public List<String> getInactivePlayers(){
        return gameController.getInactivePlayers();
    }

    /** return numero di giocatori connessi */
    public int currentPlayers(){
        return clientHandlerMap.size();
    }

    /** disconnette un client */
    public void disconnecting(ClientHandler clientHandler){
        if (gameController.isGameStarted()){
            disconnect(clientHandler);
        }
        else{
            /** rimuove giocatore dalla lobby */
            clientHandlerMap.remove(clientHandler);
        }
    }

    /** disconnette un giocatore dalla lobby */
    public void disconnect(ClientHandler clientHandler){
        gameController.disconnect(clientHandlerMap.get(clientHandler));
        /** rimuove giocatore dalla lobby */
        clientHandlerMap.remove(clientHandler);
    }

    /** riconnetti giocatore alla Lobby */
    public void reconnect(String username, ClientHandler clientHandler,VirtualView virtualView){
        clientHandlerMap.put(clientHandler, username);
        gameController.reconnect(username, virtualView);
    }



}
