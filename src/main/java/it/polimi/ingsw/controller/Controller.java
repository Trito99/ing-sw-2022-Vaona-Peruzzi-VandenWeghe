package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.player.Player;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.model.Model;  //// dobbiamo mettere classe Model nella cartella model, o usare una classe già esistente (come da esercit)

public class Controller implements Observer {

    private Model model;        // dobbiamo mettere classe Model nella cartella model, o usare una classe già esistente (come da esercit)
    private View view;

    public Controller(Model model, View view){

        this.model = model;
        this.view = view;
    }


    /** public void initializeGame(){

    } */

    public void increaseCoinScore(Player player){
        player.setCoinScore(player.getCoinScore() + 1);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o != view || !(arg instanceof Choice)){
            throw new IllegalArgumentException();
        }
       /** model.setPlayerChoice((Choice)arg);
        game();                 (DA CAMBIARE: preso da esercit)  */
    }
}


// Classe ModelView che ha come attributo una copia del model(es: Model modelCopy) con soli metodi get.
// Nella classe Model originale invece ci sono anche i set.
// Il model avrà come osservatore la modelView (model.addObserver(modelView) nel main,
// view non "ascolta" direttamente la classe model ma la modelView