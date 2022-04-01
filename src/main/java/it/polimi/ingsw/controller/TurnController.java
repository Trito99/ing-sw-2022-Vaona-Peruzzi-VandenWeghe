package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class TurnController {

    //dichiarare attributi


    public ArrayList<Player> setOrder(ArrayList<Player> round){   //setta ordine dei giocatori nel round
/**       for(Player i : round) {
            round.set(i,Player.setTrash(Player.getTrash())); //assegna ad ogni player il turnvalue della sua ultima carta giocata (Update: non serve?)

            //DA CONTROLLARE !!!
    Fede: Non dovrebbe servire assegnare valore ai player perchè c'è già nella sua Trash, la trash andrebbe aggiornata quando è giocata la carta
        }                                   */
        round.sort(Player.getTrash().getTurnValue());  //da guardare come funziona sort
        return round;
    } //?

}
