package it.polimi.ingsw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TurnController {
    private HashMap<String, Boolean> activePlayer;
    private String playingPlayer;

    private ArrayList<String> orderPlayers;

    //dichiarare attributi
    /** costruttore */
    public TurnController(GameController gameController){
        playingPlayer =  gameController.getGameSession().getListOfPlayers().get(0).getNickname();
        orderPlayers =new ArrayList<String>();
        activePlayer=new HashMap<String, Boolean>();
        for(int i=0; i < gameController.getAllVirtualView().size(); i++){
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
    public List<String> getActivePlayers(){
        ArrayList<String> activePlayers = new ArrayList<String>();
        for(int i = 0; i < orderPlayers.size(); i++) {
            if (activePlayer.get(orderPlayers.get(i))) activePlayers.add(orderPlayers.get(i));
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
    public void reconnect(String username){
        activePlayer.put(username, true);
    }

    /** imposta giocatore come inattivo per permettere di ricollegarsi più tardi */
    public boolean disconnect(String username){
        activePlayer.put(username,false);
        return(username.equals(getActivePlayer()));
    }



/**
    public ArrayList<Player> setOrder(ArrayList<Player> round){   //setta ordine dei giocatori nel round
       for(Player i : round) {
            round.set(i,Player.setTrash(Player.getTrash())); //assegna ad ogni player il turnvalue della sua ultima carta giocata (Update: non serve?)

            //DA CONTROLLARE !!!
    Fede: Non dovrebbe servire assegnare valore ai player perchè c'è già nella sua Trash, la trash andrebbe aggiornata quando è giocata la carta
        }
        round.sort(Player.getTrash().getTurnValue());  //da guardare come funziona sort
        return round;
    }    */

}
