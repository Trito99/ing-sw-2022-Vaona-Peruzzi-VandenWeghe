package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.player.Player;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

public class TurnController {
    private HashMap<String, Boolean> activePlayer;
    private String playingPlayer;

    private ArrayList<String> orderPlayers;
    ArrayList<Player> playerOrder = null;

    //dichiarare attributi
    /** costruttore */
    public TurnController(GameController gameController){
        playingPlayer = YoungestPlayer(gameController).getNickname();
        playerOrder = new ArrayList<>();
        orderPlayers = new ArrayList<>();
        activePlayer= new HashMap<>();
        for(int i=0; i < gameController.getAllVirtualView().size(); i++){
            String nickname = gameController.getGameSession().getListOfPlayers().get(i).getNickname();
            activePlayer.put(nickname, true);
            orderPlayers.add(nickname);
            playerOrder.add(gameController.getGameSession().getListOfPlayers().get(i));
        }
    }

    private Player YoungestPlayer(GameController gameController){
        Player youngestPlayer=gameController.getGameSession().getListOfPlayers().get(0);
        for(int i=1;i<gameController.getGameSession().getListOfPlayers().size();i++) {
            if (youngestPlayer.getPlayerDate().compareTo(gameController.getGameSession().getListOfPlayers().get(i).getPlayerDate())==-1 || youngestPlayer.getPlayerDate().compareTo(gameController.getGameSession().getListOfPlayers().get(i).getPlayerDate())==0){
                youngestPlayer=gameController.getGameSession().getListOfPlayers().get(i);
            }else if (youngestPlayer.getPlayerDate().compareTo(gameController.getGameSession().getListOfPlayers().get(i).getPlayerDate())==1){
                youngestPlayer=gameController.getGameSession().getListOfPlayers().get(i-1);
            }
        }
        return youngestPlayer;
    }


    public String getActivePlayer() {
        return playingPlayer;
    }


    /** cambia l'active player */
    public String nextPlayer(){
        int player;
        if((orderPlayers.indexOf(playingPlayer)+1)>(orderPlayers.size()-1))
            player = 0;
        else
            player = orderPlayers.indexOf(playingPlayer)+1;
        playingPlayer = orderPlayers.get(player);

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

    public void changeStartingPlayer(){   //setta ordine dei giocatori nel round

        ArrayList<Player>  NewPlayerOrder = (ArrayList<Player>) playerOrder.clone();
        Collections.sort(NewPlayerOrder, (o1, o2) -> Integer.valueOf(o1.getTrash().getTurnValue()).compareTo(o2.getTrash().getTurnValue()));
        playingPlayer= NewPlayerOrder.get(0).getNickname();
    }

}
