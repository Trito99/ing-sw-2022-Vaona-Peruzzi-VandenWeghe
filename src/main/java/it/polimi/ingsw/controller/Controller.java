package src.main.java.it.polimi.ingsw.controller;


import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.student.Student;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.Observer;

public abstract class Controller implements Observer {




    /** public void initializeGame(){

    } */

    public void increaseCoinScore(Player player){
        player.setCoinScore(player.getCoinScore() + 1);
    }







}
