package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameController {
    private Game gameSession;
    private TurnController turnController; //?
    private GameState gameState;


    public GameController(){
        gameSession = new Game();
        //turnController  = null; ?
        gameState= GameState.INIT;

    }



    /**
     * @param nickname del Giocatore.
     * @param gameId id della partita a cui il giocatore sta giocando.
     */

    /** maxPlayers(GameMode) e difficulty vanno scelti da chi crea la partita */
    public void initializePlayer() { // Setto i player a inizio partita

        switch (gameSession.getGameMode()){
                /** Da inserire Nickname, data(età) ecc.. di tutti i player */
                /** Togliere AddPlayer in Game e usare getListofPlayer.add??? (già fatto)*/
            case TWOPLAYERS:
                    /**ESEMPIO:
                     Player 1: scegli nickname, scrivi la tua età (o JSON)
                     this.gameSession.addPlayer(new Player(nickname, età, TColor.WHITE, PlayerNumber.PLAYER1));
                     Player 2: scegli nickname, scrivi la tua età
                     this.gameSession.addPlayer(new Player(nickname, età, TColor.WHITE, PlayerNumber.PLAYER1)); */
                    this.gameSession.getListOfPlayer().add(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
                    this.gameSession.getListOfPlayer().add(new Player(TColor.BLACK, PlayerNumber.PLAYER2));

                    if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)){
                        for (Player p : gameSession.getListOfPlayer())
                            p.setCoinScore(1);
                    }
                    break;
                    //view.put(nickname, view);
            case THREEPLAYERS:
                    this.gameSession.getListOfPlayer().add(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
                    this.gameSession.getListOfPlayer().add(new Player(TColor.BLACK, PlayerNumber.PLAYER2));
                    this.gameSession.getListOfPlayer().add(new Player(TColor.GREY, PlayerNumber.PLAYER3));

                    if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)) {
                        for (Player p : gameSession.getListOfPlayer())
                            p.setCoinScore(1);
                    }
                    break;

            case COOP:
                    this.gameSession.getListOfPlayer().add(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
                    this.gameSession.getListOfPlayer().add(new Player(TColor.WHITE, PlayerNumber.PLAYER2));
                    gameSession.getTeam().add(new Team()) ;
                    gameSession.getTeam().get(0).intializeTeam(gameSession.getListOfPlayer().get(0), gameSession.getListOfPlayer().get(1));

                    this.gameSession.getListOfPlayer().add(new Player(TColor.BLACK, PlayerNumber.PLAYER3));
                    this.gameSession.getListOfPlayer().add(new Player(TColor.BLACK, PlayerNumber.PLAYER4));
                    gameSession.getTeam().add(new Team());
                    gameSession.getTeam().get(1).intializeTeam(gameSession.getListOfPlayer().get(2), gameSession.getListOfPlayer().get(3));

                    /** Mettere ArrayList<Player> team dentro alla classe Player???  */
                    if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)) {
                        for (Player p : gameSession.getListOfPlayer())
                            p.setCoinScore(1);
                    }
                    break;
                default:
                    break;

        }
    }

    public void initializeGame(){ /**Giocatori(+ personalSchool, +DeckAssistant), Table(isole, motherEarth, nuvole, bag, cartePersonaggioontable) */
        setGameState(GameState.INIT);

    }

    public void playTrashCard(Player player){   /** memorizzo solo ultima carta giocata */
        AssistantCard playedCard = null;
        //notify observer---->scelgo carta da scartare
        for(Player p : gameSession.getListOfPlayer()){
            AssistantCard alreadyTaken = null;
            if(playedCard != p.getTrash() && p.HasAlreadyPlayed()) {
                player.setTrash(playedCard);
                player.setHasAlreadyPlayed(true);
            }
            else if(playedCard == p.getTrash() && p.HasAlreadyPlayed())
                System.out.println("Scegli un'altra carta! ");
            /**ELSE  controlla se è l'unica carta giocabile */
            /** controlla se è l'unica carta giocabile */
        }
        player.getDeckOfPlayer().getCardsInHand().remove(playedCard);

        for(Player p : gameSession.getListOfPlayer()){    /** Rimetto tutto a false a fine "turno"--->Andrà fuori dal metodo */
            p.setHasAlreadyPlayed(false);
        }
    }

    public void increaseCoinScore(Player player){
        player.setCoinScore(player.getCoinScore() + 1);
    }

    public void decreaseCoinScore(Player player, int decreaseValue) {
        player.setCoinScore(player.getCoinScore() - decreaseValue);
    }

    public void playCharacterCard(CharacterCard character, Player player, Table table){
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
                character.getCardEffect().setMbriaconePlayed(true);

            /** 2 */
            case CICCIOPANZA:
                character.getCardEffect().setCiccioPanzaPlayed(true);

            case ALZABANDIERA:   /** 3 */
                IslandCard islandChosen = null;
                //notify (observer)----> islandChosen
                ArrayList<Player> playersList= new ArrayList<>(gameSession.getListOfPlayer());

                islandChosen.calculateInfluence(playersList, character.getCardEffect());
                islandChosen.buildTowerOnIsland(playersList, character.getCardEffect());
                islandChosen.changeTowerColour(playersList, character.getCardEffect());
                gameSession.getTable().joinIsland(islandChosen, gameSession.getTable().getListOfIsland());
                break;

            case CEPOSTAPERTE:   /** 4 */
                character.getCardEffect().setCePostaPerTePlayed(true);
                break;
            case SCIURA:   /** 5 */
                IslandCard islandChosenTwo = null;
                //notify (observer)----> islandChosen

                islandChosenTwo.setXCardOnIsland(true);
                if(islandChosenTwo.getXCardCounter() < 4 && character.getCardEffect().getXCardOnCard() > 0){
                    islandChosenTwo.setXCardCounter(islandChosenTwo.getXCardCounter() + 1);
                    islandChosenTwo.setXCardOnIsland(true);
                    character.getCardEffect().setXCardOnCard(character.getCardEffect().getXCardOnCard()-1);
                }
                else
                    System.out.println(" Non puoi!!!");
                break;

            case TAURO:   /** 6 */
                character.getCardEffect().setTauroPlayed(true);
                break;

            case JOKER:   /** 7 */
                for(int i =0; i<3; i++){
                    Student choice = null;  //nuovo studente da mettere nella entry
                    Student toChange = null;    //studente da togliere dalla entry
                    //notify (observer)---->scelta studente in entry da scambiare
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(toChange);
                    //notify (observer)---->scelta studente nella carta da scambiare
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(choice);
                    character.getStudentsOnCard().remove(choice);
                    character.getStudentsOnCard().add(toChange);
                }
                break;

            case SILVIO:   /** 8 */
                character.getCardEffect().setSilvioPlayed(true);
                break;

            case FUNGAIOLO:   /** 9 */
                SColor colorChosen = null;
                //notify (observer)----> colorChosen
                colorChosen.lockColor();
                break;

            case MENESTRELLO:   /** 10 */
                // TO DO: controlli se sono pieni
                /** togli da entry e metti in hall */
                for(int i =0; i<3; i++){
                    Student choice = null;
                    //notify (observer)---->scelta 2 studenti
                    if(choice.getsColour().equals(SColor.GREEN)){
                        gameSession.getActivePlayer().getPersonalSchool().getGTable().add(choice);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(choice);
                    }
                    else if(choice.getsColour().equals(SColor.RED)){
                        gameSession.getActivePlayer().getPersonalSchool().getRTable().add(choice);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(choice);
                    }
                    else if(choice.getsColour().equals(SColor.YELLOW)){
                        gameSession.getActivePlayer().getPersonalSchool().getYTable().add(choice);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(choice);
                    }
                    else if(choice.getsColour().equals(SColor.PINK)){
                        gameSession.getActivePlayer().getPersonalSchool().getPTable().add(choice);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(choice);
                    }
                    else if(choice.getsColour().equals(SColor.BLUE)){
                        gameSession.getActivePlayer().getPersonalSchool().getBTable().add(choice);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(choice);
                    }
                }
                /** togli da hall e metti in entry */
                for(int i =0; i<3; i++){
                    Student choice = null;
                    //notify (observer)---->scelta 2 studenti
                    if(choice.getsColour().equals(SColor.GREEN)){
                        gameSession.getActivePlayer().getPersonalSchool().getGTable().remove(choice);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().add(choice);
                    }
                    else if(choice.getsColour().equals(SColor.RED)){
                        gameSession.getActivePlayer().getPersonalSchool().getRTable().remove(choice);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().add(choice);
                    }
                    else if(choice.getsColour().equals(SColor.YELLOW)){
                        gameSession.getActivePlayer().getPersonalSchool().getYTable().remove(choice);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().add(choice);
                    }
                    else if(choice.getsColour().equals(SColor.PINK)){
                        gameSession.getActivePlayer().getPersonalSchool().getPTable().remove(choice);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().add(choice);
                    }
                    else if(choice.getsColour().equals(SColor.BLUE)){
                        gameSession.getActivePlayer().getPersonalSchool().getBTable().remove(choice);
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().add(choice);
                    }
                }
                break;

            case DAMA:   /** 11 */
                Student choice = null;
                int i =0;
                //notify (observer)---->scelgo pedina da mettere nel table
                if(choice.getsColour().equals(SColor.GREEN)){
                    gameSession.getActivePlayer().getPersonalSchool().getGTable().add(choice);
                }
                else if(choice.getsColour().equals(SColor.RED)){
                    gameSession.getActivePlayer().getPersonalSchool().getRTable().add(choice);
                }
                else if(choice.getsColour().equals(SColor.YELLOW)){
                    gameSession.getActivePlayer().getPersonalSchool().getYTable().add(choice);
                }
                else if(choice.getsColour().equals(SColor.PINK)){
                    gameSession.getActivePlayer().getPersonalSchool().getPTable().add(choice);
                }
                else if(choice.getsColour().equals(SColor.BLUE)){
                    gameSession.getActivePlayer().getPersonalSchool().getBTable().add(choice);
                }
                character.getStudentsOnCard().remove(choice);
                //notify (observer)---->pesco pedina da mettere sulla carta
                character.getStudentsOnCard().add(table.getBag().get(table.getBag().size() -1));
                table.getBag().remove(table.getBag().get(table.getBag().size() -1));
                break;

            case TOSSICO:   /** 12 */
                SColor colorChoice = null;
                //notify (observer)---->scelgo un colore
                for(Player p : gameSession.getListOfPlayer()){
                    if(colorChoice.equals(SColor.GREEN)){
                        for(int j=0; j<3; j++){
                            if(gameSession.getActivePlayer().getPersonalSchool().getGTable().size() != 0)
                                gameSession.getActivePlayer().getPersonalSchool().getGTable().remove(colorChoice);
                        }
                    }
                    else if(colorChoice.equals(SColor.RED)){
                        for(int j=0; j<3; j++){
                            if(gameSession.getActivePlayer().getPersonalSchool().getRTable().size() != 0)
                                gameSession.getActivePlayer().getPersonalSchool().getRTable().remove(colorChoice);
                        }
                    }
                    else if(colorChoice.equals(SColor.YELLOW)){
                        for(int j=0; j<3; j++){
                            if(gameSession.getActivePlayer().getPersonalSchool().getYTable().size() != 0)
                                gameSession.getActivePlayer().getPersonalSchool().getYTable().remove(colorChoice);
                        }
                    }
                    else if(colorChoice.equals(SColor.PINK)){
                        for(int j=0; j<3; j++){
                            if(gameSession.getActivePlayer().getPersonalSchool().getPTable().size() != 0)
                                gameSession.getActivePlayer().getPersonalSchool().getPTable().remove(colorChoice);
                        }
                    }
                    else if(colorChoice.equals(SColor.BLUE)){
                        for(int j=0; j<3; j++){
                            if(gameSession.getActivePlayer().getPersonalSchool().getBTable().size() != 0)
                                gameSession.getActivePlayer().getPersonalSchool().getBTable().remove(colorChoice);
                        }
                    }
                }
                break;
        }



    }

    public void moveMotherEarth(int n, Table table, Player player, CardEffect cardEffectPlayed) { /** DA TOGLIERE IN TABLE??? */
        table.getListOfIsland().get(table.getPosMotherEarth() - 1).setMotherEarthOnIsland(false);

        int playerChoice=0; /** da cambiare */
        //notify(observer)

        if (playerChoice <= player.getTrash().getStepMotherEarth() && !cardEffectPlayed.isCePostaPerTePlayed()) {

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
        else if(playerChoice <= player.getTrash().getStepMotherEarth()+2 && cardEffectPlayed.isCePostaPerTePlayed()){
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



  /**  @Override
    public void update(Observable o, Object arg) {
        if (o != view || !(arg instanceof Choice)){
            throw new IllegalArgumentException();
        }
         model.setPlayerChoice((Choice)arg);
         game();                 (DA CAMBIARE: preso da esercit)
    }*/
}


// Classe ModelView che ha come attributo una copia del model(es: Model modelCopy) con soli metodi get.
// Nella classe Model originale invece ci sono anche i set.
// Il model avrà come osservatore la modelView (model.addObserver(modelView) nel main,
// view non "ascolta" direttamente la classe model ma la modelView
