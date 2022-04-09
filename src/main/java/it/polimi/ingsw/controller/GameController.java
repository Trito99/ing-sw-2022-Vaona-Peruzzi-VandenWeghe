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
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.view.View;

import java.lang.reflect.Array;
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

    public void playCard(CharacterCard character, Player player, Table table){
        //notify observer con scelta del giocatore -> sceglie attraverso l'id o con Nome personaggio?

        if(character.getCoinOnCard()) {
            if(player.getCoinScore() >= character.getCostCharacter() +1) {
                decreaseCoinScore(player, character.getCostCharacter() + 1);
                table.increaseCoinsOnTable(character.getCostCharacter() + 1);
            }
            else{
                System.out.println("NON HAI ABBASTANZA MONETE! ");
                /** Rifai scelta */
            }
        }
        else{
            if(player.getCoinScore() >= character.getCostCharacter()) {
                decreaseCoinScore(player, character.getCostCharacter());
                table.increaseCoinsOnTable(character.getCostCharacter());
                character.setCoinOnCard(true);
            }
            else{
                System.out.println("NON HAI ABBASTANZA MONETE! ");
                /** Rifai scelta */
            }
        }

        //selezione
        switch(character.getCardEffect()){
            /** 1 */
            case MBRIACONE:
                character.getCardEffect().playMbriacone(player);
               /** player.setMbriaconePlayed(true); o questo???*/

            /** 2 */
            case CICCIOPANZA:
                character.getCardEffect().playCiccioPanza(player);

            case ALZABANDIERA:   /** 3 */
                //notify (observer)----> islandChosen
                character.getCardEffect().playAlzabandiera(player, gameSession.getListOfPlayer(), islandChosen);
                gameSession.getTable().joinIsland(islandChosen, gameSession.getTable().getListOfIsland());
                break;
            case CEPOSTAPERTE:   /** 4 */
                character.getCardEffect().playCepostaperte(player);
                break;
            case SCIURA:   /** 5 */
                break;
            case TAURO:   /** 6 */
                character.getCardEffect().playTauro(player);
                break;
            case JOKER:   /** 7 */
                break;
            case SILVIO:   /** 8 */
                character.getCardEffect().playSilvio(player);
                break;
            case FUNGAIOLO:   /** 9 */
                //notify (observer)----> colorChosen
                character.getCardEffect().playFungaiolo(player, colorChosen);
                break;
            case MENESTRELLO:   /** 10 */
               Student firstChoiceEntry = null;
                Student secondChoiceEntry = null;
                //notify (observer)---->scelta 2 studenti
                    if(firstChoiceEntry.getsColour().equals(SColor.GREEN)){
                        gameSession.getActivePlayer().getPersonalSchool().getGTable().add(firstChoiceEntry);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(firstChoiceEntry);
                    }
                    else if(firstChoiceEntry.getsColour().equals(SColor.RED)){
                        gameSession.getActivePlayer().getPersonalSchool().getRTable().add(firstChoiceEntry);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(firstChoiceEntry);
                    }
                    else if(firstChoiceEntry.getsColour().equals(SColor.YELLOW)){
                        gameSession.getActivePlayer().getPersonalSchool().getYTable().add(firstChoiceEntry);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(firstChoiceEntry);
                    }
                    else if(firstChoiceEntry.getsColour().equals(SColor.PINK)){
                        gameSession.getActivePlayer().getPersonalSchool().getPTable().add(firstChoiceEntry);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(firstChoiceEntry);
                    }
                    else if(firstChoiceEntry.getsColour().equals(SColor.BLUE)){
                        gameSession.getActivePlayer().getPersonalSchool().getBTable().add(firstChoiceEntry);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(firstChoiceEntry);
                     }

                    else if(secondChoiceEntry.getsColour().equals(SColor.GREEN)){
                        gameSession.getActivePlayer().getPersonalSchool().getGTable().add(secondChoiceEntry);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(secondChoiceEntry);
                    }
                    else if(secondChoiceEntry.getsColour().equals(SColor.RED)){
                        gameSession.getActivePlayer().getPersonalSchool().getRTable().add(secondChoiceEntry);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(secondChoiceEntry);
                    }
                    else if(secondChoiceEntry.getsColour().equals(SColor.YELLOW)){
                        gameSession.getActivePlayer().getPersonalSchool().getYTable().add(secondChoiceEntry);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(secondChoiceEntry);
                    }
                    else if(secondChoiceEntry.getsColour().equals(SColor.PINK)){
                        gameSession.getActivePlayer().getPersonalSchool().getPTable().add(secondChoiceEntry);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(secondChoiceEntry);
                    }
                    else if(secondChoiceEntry.getsColour().equals(SColor.BLUE)){
                        gameSession.getActivePlayer().getPersonalSchool().getBTable().add(secondChoiceEntry);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(secondChoiceEntry);
                    }

               Student firstChoiceRemove = null;
               Student secondChoiceRemove = null;
               //notify (observer)---->scelta 2 studenti
                if(firstChoiceRemove.getsColour().equals(SColor.GREEN)){
                    gameSession.getActivePlayer().getPersonalSchool().getGTable().remove(firstChoiceRemove);
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(firstChoiceRemove);
                }
                else if(firstChoiceRemove.getsColour().equals(SColor.RED)){
                    gameSession.getActivePlayer().getPersonalSchool().getRTable().remove(firstChoiceRemove);
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(firstChoiceRemove);
                }
                else if(firstChoiceRemove.getsColour().equals(SColor.YELLOW)){
                    gameSession.getActivePlayer().getPersonalSchool().getYTable().remove(firstChoiceRemove);
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(firstChoiceRemove);
                }
                else if(firstChoiceRemove.getsColour().equals(SColor.PINK)){
                    gameSession.getActivePlayer().getPersonalSchool().getPTable().remove(firstChoiceRemove);
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(firstChoiceRemove);
                }
                else if(firstChoiceRemove.getsColour().equals(SColor.BLUE)){
                    gameSession.getActivePlayer().getPersonalSchool().getBTable().remove(firstChoiceRemove);
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(firstChoiceRemove);
                }

                else if(secondChoiceRemove.getsColour().equals(SColor.GREEN)){
                    gameSession.getActivePlayer().getPersonalSchool().getGTable().remove(secondChoiceRemove);
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(secondChoiceRemove);
                }
                else if(secondChoiceRemove.getsColour().equals(SColor.RED)){
                    gameSession.getActivePlayer().getPersonalSchool().getRTable().remove(secondChoiceRemove);
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(secondChoiceRemove);
                }
                else if(secondChoiceRemove.getsColour().equals(SColor.YELLOW)){
                    gameSession.getActivePlayer().getPersonalSchool().getYTable().remove(secondChoiceRemove);
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(secondChoiceRemove);
                }
                else if(secondChoiceRemove.getsColour().equals(SColor.PINK)){
                    gameSession.getActivePlayer().getPersonalSchool().getPTable().remove(secondChoiceRemove);
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(secondChoiceRemove);
                }
                else if(secondChoiceRemove.getsColour().equals(SColor.BLUE)){
                    gameSession.getActivePlayer().getPersonalSchool().getBTable().remove(secondChoiceRemove);
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(secondChoiceRemove);
                }
                break;
            case DAMA:   /** 11 */
                break;
            case TOSSICO:   /** 12 */
                break;
        }



    }

    public void moveMotherEarth(int n, Table table, Player player) { /** DA TOGLIERE IN TABLE??? */
        table.getListOfIsland().get(table.getPosMotherEarth() - 1).setMotherEarthOnIsland(false);
        //notify(observer)

        if (playerchoice() <= player.getTrash().getStepMotherEarth() && !player.isCePostaPerTePlayed()) {

            if ((table.getPosMotherEarth() + n) > table.getListOfIsland().size()) {
                table.getListOfIsland().get(table.getPosMotherEarth() + n - table.getListOfIsland().size() - 1).setMotherEarthOnIsland(true);
                table.setPosMotherEarth(table.getPosMotherEarth() + n - table.getListOfIsland().size());
            }
            else {
                table.getListOfIsland().get(table.getPosMotherEarth() + n - 1).setMotherEarthOnIsland(true);
                table.setPosMotherEarth(table.getPosMotherEarth() + n);
            }
        }
        /** EFFETTO CEPOSTAPERTE */
        else if(playerChoice() <= player.getTrash().getStepMotherEarth()+2 && player.isCePostaPerTePlayed()){
            if ((table.getPosMotherEarth() + n + 2) > table.getListOfIsland().size()) {
                table.getListOfIsland().get(table.getPosMotherEarth() + n + 2 - table.getListOfIsland().size() - 1).setMotherEarthOnIsland(true);
                table.setPosMotherEarth(table.getPosMotherEarth() + n + 2 - table.getListOfIsland().size());
            }
            else {
                table.getListOfIsland().get(table.getPosMotherEarth() + n + 2 - 1).setMotherEarthOnIsland(true);
                table.setPosMotherEarth(table.getPosMotherEarth() + n + 2);
            }
        }
        else{
            System.out.println("Scelta non valida!!");
        }
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
