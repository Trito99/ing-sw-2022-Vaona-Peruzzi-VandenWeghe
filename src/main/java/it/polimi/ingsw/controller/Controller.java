package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.view.View;

public class Controller implements Observer {

    private Game game;
    private GameMode gameMode;
    private Difficulty difficulty;
    private View view;
    private int maxPlayers;
    private GameState gameState;
    private ArrayList<AssistantCard> tempCards;

    public Controller(){
        this.view = view;
        maxPlayers=0;
        tempCards = new ArrayList<>();
    }



    /**
     * @param nickname del Giocatore.
     * @param gameId id della partita a cui il giocatore sta giocando.
     */
    public void newPlayer(String nickname, int gameId, View view) {
        if(view.size()<maxPlayers){
            this.game.addPlayer(new Player(nickname, gameId));
            //view.put(nickname, view);

            if(view.size()==maxPlayers){
                initializeGame();
            }
        }
    }

    public void initializeGame(){
        setGameState(GameState.INIT);
        switch (maxPlayers){
            case 4:
                game.getListOfPlayer().get(3);      //array da 0 a 3
                game.setGameMode(gameMode.COOP);
                if(difficulty.equals(Difficulty.STANDARDMODE)){
                    Player.setCoinScore(0);
                }
                else if (difficulty.equals(Difficulty.EXPERTMODE)){
                    Player.setCoinScore(1);
                }

            case 3:
                game.getListOfPlayer().get(2);
                game.setGameMode(gameMode.THREEPLAYERS);
                if(difficulty.equals(Difficulty.STANDARDMODE)){
                    Player.setCoinScore(0);
                }
                else if (difficulty.equals(Difficulty.EXPERTMODE)){
                    Player.setCoinScore(1);
                }

            case 2:
                game.getListOfPlayer().get(1);
                game.setGameMode(gameMode.TWOPLAYERS);
                if(difficulty.equals(Difficulty.STANDARDMODE)){
                    Player.setCoinScore(0);
                }
                else if (difficulty.equals(Difficulty.EXPERTMODE)){
                    Player.setCoinScore(1);
                }
        }
    }



    public void increaseCoinScore(Player player){
        player.setCoinScore(player.getCoinScore() + 1);
    }


    /**
     * inseriamo tutte le set e le get
     */

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }



    ù
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