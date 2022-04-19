package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TurnController {
    private HashMap<String, Boolean> activePlayer;

    //dichiarare attributi


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
