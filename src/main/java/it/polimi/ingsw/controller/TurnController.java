package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.player.Player;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

public class TurnController {
    private HashMap<String, Boolean> activePlayer;
    private String playingPlayer;

    private ArrayList<String> orderPlayers;
    private ArrayList<Player> playersOrder; // Messo per changeOrder--> andrà tenuto solo orderPlayers(?)

    //dichiarare attributi
    /** costruttore */
    public TurnController(GameController gameController){
        playingPlayer =  gameController.getGameSession().getListOfPlayers().get(0).getNickname();
        orderPlayers =new ArrayList<String>();
        activePlayer=new HashMap<String, Boolean>();
        for(int i=0; i < gameController.getAllVirtualView().size(); i++){
            System.out.println((gameController.getAllVirtualView().size()));
            System.out.println(gameController.getGameSession().getListOfPlayers().size());
            String nickname = gameController.getGameSession().getListOfPlayers().get(i).getNickname();
            activePlayer.put(nickname, true);
            orderPlayers.add(nickname);
        }
    }


    public String getActivePlayer() {
        return playingPlayer;
    }

    /** primo giocatore del turno */
    public String firstPlayer(){
        return getActivePlayers().get(0);
    }

    /** cambia l'active player */
    public String nextPlayer(){
        int player = getActivePlayers().indexOf(playingPlayer)+1;
        if(player >= getActivePlayers().size()) player = 0;
        playingPlayer = getActivePlayers().get(player);
        //setMainAction(false);

        return playingPlayer;
    }

    /** giocatore ancora connessi ordinati con l'ordine del turno */
    public ArrayList<String> getActivePlayers(){
        ArrayList<String> activePlayers = new ArrayList<String>();
        for (String orderPlayer : orderPlayers) {
            if (activePlayer.get(orderPlayer))
                activePlayers.add(orderPlayer);
        }
        return activePlayers;
    }

    /** controlla se il gioco ha giocatori che si sono disconnessi ----> return se ci sono giocatori disconnessi */
    public boolean hasInactivePlayers(){
        return(getInactivePlayers().size()!=0);
    }

    /** lista di giocatori che si sono disconnessi ---> return lista di nickname dei giocatori */
    public List<String> getInactivePlayers(){
        return activePlayer.entrySet().stream().filter(entry -> (!entry.getValue())).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    /** imposta nickname del giocatore come attivo per permettergli di giocare */
    public void reconnect(String nickname){
        activePlayer.put(nickname, true);
    }

    /** imposta giocatore come inattivo per permettere di ricollegarsi più tardi */
    public boolean disconnect(String nickname){
        activePlayer.put(nickname,false);
        return(nickname.equals(getActivePlayer()));
    }

    public void changeOrder(ArrayList<Player> playerOrder){   //setta ordine dei giocatori nel round

        Collections.sort(playerOrder, (o1, o2) -> Integer.valueOf(o1.getTrash().getTurnValue()).compareTo(o2.getTrash().getTurnValue()));
    }

}
