package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.view.View;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameController {
    private int maxPlayers;
    private Game gameSession;
    private final HashMap<String, VirtualView> allVirtualView;
    private TurnController turnController;
    private GameState gameState;
    private GameMode gameMode;
    private Difficulty difficulty;

    public GameController(){
        maxPlayers=0;
        tempCards = new ArrayList<>();
    }



    /**
     * @param nickname del Giocatore.
     * @param gameId id della partita a cui il giocatore sta giocando.
     */
    public void newPlayer(String nickname, int gameId, int age, View view) {
        if(view.size()<maxPlayers){
            this.game.addPlayer(new Player(nickname, gameId, age));
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
                game.setGameMode(gameMode.COOP);
                if(difficulty.equals(Difficulty.STANDARDMODE)){
                    Player.setCoinScore(0);
                }
                else if (difficulty.equals(Difficulty.EXPERTMODE)){
                    Player.setCoinScore(1);
                }
                for(int i=0;i<4;i++)
                    game.getListOfPlayer().add(new Player());
            case 3:
                game.setGameMode(gameMode.THREEPLAYERS);
                if(difficulty.equals(Difficulty.STANDARDMODE)){
                    Player.setCoinScore(0);
                }
                else if (difficulty.equals(Difficulty.EXPERTMODE)){
                    Player.setCoinScore(1);
                }
                for(int i=0;i<3;i++)
                    game.getListOfPlayer().add(new Player());
                /**if(player.getPlayerNumber()==PlayerNumber.PLAYER1) player.setTColour(TColor.WHITE);
                else if(player.getPlayerNumber()==PlayerNumber.PLAYER2) player.setTColour(TColor.GREY);
                else if(player.getPlayerNumber()==PlayerNumber.PLAYER1) player.setTColour(TColor.BLACK);*/

            case 2:
                game.setGameMode(gameMode.TWOPLAYERS);
                if(difficulty.equals(Difficulty.STANDARDMODE)){
                    Player.setCoinScore(0);
                }
                else if (difficulty.equals(Difficulty.EXPERTMODE)){
                    Player.setCoinScore(1);
                }
                for(int i=0;i<2;i++)
                    game.getListOfPlayer().add(new Player());
                /**
                if(player.getPlayerNumber()==PlayerNumber.PLAYER1) player.setTColour(TColor.WHITE);
                else if(player.getPlayerNumber()==PlayerNumber.PLAYER2) player.setTColour(TColor.BLACK);*/

        }
    }


    public void increaseCoinScore(Player player){
        player.setCoinScore(player.getCoinScore() + 1);
    }

    public void decreaseCoinScore(Player player, int decreaseValue) {
        player.setCoinScore(player.getCoinScore() - decreaseValue);
    }

    public void playCard(CharacterCard character, Player player, GameController gc, Table table, CharacterCard card, ArrayList<Player> players){
        //notify observer con scelta del giocatore -> sceglie attraverso l'id

        //selezione
        switch(character.getCardEffect()){
            /** 1 */
            case MBRIACONE:

            /** 2 */
            case CICCIOPANZA:
                character.getCardEffect().playCiccioPanza(player, gc, table, card, players);     //controllare il gc

            case ALZABANDIERA:   /** 3 */
                break;
            case CEPOSTAPERTE:   /** 4 */
                break;
            case SCIURA:   /** 5 */
                break;
            case TAURO:   /** 6 */
                break;
            case JOKER:   /** 7 */
                break;
            case SILVIO:   /** 8 */
                break;
            case FUNGAIOLO:   /** 9 */
                break;
            case MENESTRELLO:   /** 10 */
                break;
            case DAMA:   /** 11 */
                break;
            case TOSSICO:   /** 12 */
                break;
        }



    }

    public void moveMotherEarth(IslandCard islandCard, Table table, MotherEarth motherEarth) {    //Le scelte brooo,
        int position = motherEarth.getPosition();
        //notify Observer che mi dice la scelta del giocatore
        position = position + playerChoice;
        if(islandCard.towerIsOnIsland()) {
            islandCard.changeTowerColour(game.getListOfPlayer());
        }
        else {
            islandCard.buildTowerOnIsland(game.getListOfPlayer());
        }
        table.joinIsland();

    }

    /**
     * inseriamo le set e le get
     */

    public void setGameState(GameState gameState){
        this.gameState = gameState;
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
