package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class TurnController {

    //dichiarare attributi


    public ArrayList<Player> setOrder(ArrayList<Player> round){   //setta ordine dei giocatori nel round
        for(Player i : round) {
            round.set(i, Player.getTrash().getTurnValue()); //assegna ad ogni player il turnvalue della sua ultima carta giocata

            //DA CONTROLLARE !!!
        }
        round.sort(Player.getTrash().getTurnValue());
        return round;
    } //?

}
