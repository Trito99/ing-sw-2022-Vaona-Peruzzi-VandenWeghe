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
import it.polimi.ingsw.view.VirtualView;
import it.polimi.ingsw.observer.ObservableView;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class GameController {
    private Game gameSession;
    private int maxPlayers;
    private TurnController turnController; //?
    private GameState gameState;
    private final HashMap<String, VirtualView> allVirtualView;


    public GameController(){
        this.allVirtualView= new HashMap<>();
        gameSession = new Game();
        //turnController  = null; ?
        gameState= GameState.INIT;
    }


    /**
     * @param nickname del Giocatore.
     * @param gameId id della partita a cui il giocatore sta giocando.
     */
    public boolean newPlayer(String nickname, String gameId, VirtualView virtualView) {
        if(allVirtualView.isEmpty()){
            allVirtualView.put(nickname, virtualView);
            virtualView.showLogin(nickname,gameId, true);
            //virtualView.askPlayersNumber();
            return true;
        }
        else if(allVirtualView.size()<maxPlayers){
            //this.gameSession.addPlayer(new Player(nickname));
            allVirtualView.put(nickname, virtualView);
            virtualView.showLogin(nickname, gameId,true);
            /**if(allVirtualView.size()==maxPlayers){
                startGame();
            } */
            return true;
        }
        else virtualView.showLogin(nickname, gameId,false);
        return false;
    }

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
                     this.gameSession.addPlayer(new Player(nickname, età, TColor.WHITE, PlayerNumber.PLAYER1));
                    int year = 0;
                    int month=0;
                    int dayOfMonth=0;
                    GregorianCalendar dataPlayer = new GregorianCalendar(year, month, dayOfMonth);      */
                    this.gameSession.getListOfPlayers().add(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
                    this.gameSession.getListOfPlayers().add(new Player(TColor.BLACK, PlayerNumber.PLAYER2));

                    if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)){
                        for (Player p : gameSession.getListOfPlayers())
                            p.setCoinScore(1);
                    }
                    break;
                    //view.put(nickname, view);
            case THREEPLAYERS:
                    this.gameSession.getListOfPlayers().add(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
                    this.gameSession.getListOfPlayers().add(new Player(TColor.BLACK, PlayerNumber.PLAYER2));
                    this.gameSession.getListOfPlayers().add(new Player(TColor.GREY, PlayerNumber.PLAYER3));

                    if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)) {
                        for (Player p : gameSession.getListOfPlayers())
                            p.setCoinScore(1);
                    }
                    break;

            case COOP:
                    this.gameSession.getListOfPlayers().add(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
                    this.gameSession.getListOfPlayers().add(new Player(TColor.WHITE, PlayerNumber.PLAYER2));
                    gameSession.getTeam().add(new Team()) ;
                    gameSession.getTeam().get(0).intializeTeam(gameSession.getListOfPlayers().get(0), gameSession.getListOfPlayers().get(1));

                    this.gameSession.getListOfPlayers().add(new Player(TColor.BLACK, PlayerNumber.PLAYER3));
                    this.gameSession.getListOfPlayers().add(new Player(TColor.BLACK, PlayerNumber.PLAYER4));
                    gameSession.getTeam().add(new Team());
                    gameSession.getTeam().get(1).intializeTeam(gameSession.getListOfPlayers().get(2), gameSession.getListOfPlayers().get(3));

                    /** Mettere ArrayList<Player> team dentro alla classe Player???  */
                    if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)) {
                        for (Player p : gameSession.getListOfPlayers())
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

        /** DA TOGLIERE */
    public void playTrashCard(Player player){   /** memorizzo solo ultima carta giocata */
        AssistantCard playedCard = null;
        //notify observer---->scelgo carta da scartare

        for(Player p : gameSession.getListOfPlayers()){
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

        for(Player p : gameSession.getListOfPlayers()){    /** Rimetto tutto a false a fine "turno"--->Andrà fuori dal metodo */
            p.setHasAlreadyPlayed(false);
        }
    }

    /** DA TOGLIERE ?? */
    public void increaseCoinScore(Player player){
        player.setCoinScore(player.getCoinScore() + 1);
    }

    public void decreaseCoinScore(Player player, int decreaseValue) {
        player.setCoinScore(player.getCoinScore() - decreaseValue);
    }

    public void playAssistantCard(String assistantName){
        //Scelta dal giocatore
        AssistantCard assistantCardPlayed = null;

        do {
            for (AssistantCard card : gameSession.getActivePlayer().getDeckOfPlayer().getCardsInHand()) {
                if (card.getAssistantName().equals(assistantName)) {
                    /** DA FARE: Controllo se il giocatore può giocare quella carta (non è già stata giocata da altri) o se è l'unica che può mettere */
                    assistantCardPlayed = card;
                }
            }
        }
        while(assistantCardPlayed==null);

        gameSession.getActivePlayer().setTrash(assistantCardPlayed);
        gameSession.getActivePlayer().setHasAlreadyPlayed(true);
        gameSession.getActivePlayer().getDeckOfPlayer().getCardsInHand().remove(assistantCardPlayed);
    }

    public void playCharacterCard(CardEffect cardEffect){ /** da rifare!!! */
        //notify observer con scelta del giocatore -> sceglie attraverso l'id o con Nome personaggio?
        CharacterCard characterCardPlayed = null;

        do {
            for (CharacterCard card : gameSession.getTable().getCharacterCardsOnTable()) {
                if (card.getCardEffect().equals(cardEffect)) {
                    /** DA FARE: Controllo se il giocatore può giocare quella carta (non è già stata giocata da altri) o se è l'unica che può mettere */
                    characterCardPlayed = card;
                }
            }
        }
        while(characterCardPlayed==null);

        if(characterCardPlayed.getCoinOnCard()) {
            if(gameSession.getActivePlayer().getCoinScore() >= characterCardPlayed.getCostCharacter() +1) {
                decreaseCoinScore(gameSession.getActivePlayer(), characterCardPlayed.getCostCharacter() + 1);
                gameSession.getTable().increaseCoinsOnTable(characterCardPlayed.getCostCharacter() + 1);
            }
            else{
                System.out.println("NON HAI ABBASTANZA MONETE! ");
                /** Rifai scelta */
            }
        }
        else{
            if(gameSession.getActivePlayer().getCoinScore() >= characterCardPlayed.getCostCharacter()) {
                decreaseCoinScore(gameSession.getActivePlayer(), characterCardPlayed.getCostCharacter());
                gameSession.getTable().increaseCoinsOnTable(characterCardPlayed.getCostCharacter());
                characterCardPlayed.setCoinOnCard(true);
            }
            else{
                System.out.println("NON HAI ABBASTANZA MONETE! ");
                /** Rifai scelta */
            }
        }

        //selezione
        switch(characterCardPlayed.getCardEffect()){
            /** 1 */
            case ABATE:
                Student studentChosen = null;
                IslandCard islandCardChosen= null;
                //notify (observer)----> studentChosen
                for(Student s : characterCardPlayed.getStudentsOnCard()){
                    if(s.equals(studentChosen)){
                        characterCardPlayed.getStudentsOnCard().remove(s);
                        gameSession.getTable().getListOfIsland().get(islandCardChosen.getIdIsland() - 1).getStudentOnIsland().add(s);
                    }
                }
                characterCardPlayed.getStudentsOnCard().add(gameSession.getTable().getBag().get(0));
                gameSession.getTable().getBag().remove(0);

            /** 2 */
            case OSTE:
                characterCardPlayed.getCardEffect().setOstePlayed(true);

            case ARALDO:   /** 3 */
                IslandCard islandChosen = null;
                //notify (observer)----> islandChosen
                ArrayList<Player> playersList= new ArrayList<>(gameSession.getListOfPlayers());

                islandChosen.calculateInfluence(playersList, characterCardPlayed.getCardEffect());
                islandChosen.buildTowerOnIsland(playersList, characterCardPlayed.getCardEffect());
                islandChosen.changeTowerColour(playersList, characterCardPlayed.getCardEffect());
                gameSession.getTable().joinIsland(gameSession.getTable().getListOfIsland());
                break;

            case LATORE:   /** 4 */
                characterCardPlayed.getCardEffect().setLatorePlayed(true);
                break;
            case CURATRICE:   /** 5 */
                IslandCard islandChosenTwo = null;
                //notify (observer)----> islandChosen

                islandChosenTwo.setXCardOnIsland(true);
                if(islandChosenTwo.getXCardCounter() < 4 && characterCardPlayed.getCardEffect().getXCardOnCard() > 0){
                    islandChosenTwo.setXCardCounter(islandChosenTwo.getXCardCounter() + 1);
                    islandChosenTwo.setXCardOnIsland(true);
                    characterCardPlayed.getCardEffect().setXCardOnCard(characterCardPlayed.getCardEffect().getXCardOnCard()-1);
                }
                else
                    System.out.println(" Non puoi!!!");
                break;

            case CENTAURO:   /** 6 */
                characterCardPlayed.getCardEffect().setCentauroPlayed(true);
                break;

            case SALTIMBANCO:   /** 7 */
                for(int i =0; i<3; i++){
                    Student choice = null;  //nuovo studente da mettere nella entry
                    Student toChange = null;    //studente da togliere dalla entry
                    //notify (observer)---->scelta studente in entry da scambiare
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(toChange);
                    //notify (observer)---->scelta studente nella carta da scambiare
                    gameSession.getActivePlayer().getPersonalSchool().getEntry().add(choice);
                    characterCardPlayed.getStudentsOnCard().remove(choice);
                    characterCardPlayed.getStudentsOnCard().add(toChange);
                }
                break;

            case CAVALIERE:   /** 8 */
                characterCardPlayed.getCardEffect().setCavalierePlayed(true);
                break;

            case ERBORISTA:   /** 9 */
                SColor colorChosen = null;
                //notify (observer)----> colorChosen
                colorChosen.lockColor();
                break;

            case BARDO:   /** 10 */
                // TO DO: controlli se sono pieni
                /** togli da entry e metti in hall */
                for(int i =0; i<3; i++){
                    Student choice = null;
                    //notify (observer)---->scelta 2 studenti
                    if(choice.getsColour().equals(SColor.GREEN)){
                        gameSession.getActivePlayer().getPersonalSchool().getGTable().add(choice);
                        getCoinFromStudentMove();
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(choice);
                    }
                    else if(choice.getsColour().equals(SColor.RED)){
                        gameSession.getActivePlayer().getPersonalSchool().getRTable().add(choice);
                        getCoinFromStudentMove();
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(choice);
                    }
                    else if(choice.getsColour().equals(SColor.YELLOW)){
                        gameSession.getActivePlayer().getPersonalSchool().getYTable().add(choice);
                        getCoinFromStudentMove();
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(choice);
                    }
                    else if(choice.getsColour().equals(SColor.PINK)){
                        gameSession.getActivePlayer().getPersonalSchool().getPTable().add(choice);
                        getCoinFromStudentMove();
                        gameSession.getActivePlayer().getPersonalSchool().getEntry().remove(choice);
                    }
                    else if(choice.getsColour().equals(SColor.BLUE)){
                        gameSession.getActivePlayer().getPersonalSchool().getBTable().add(choice);
                        getCoinFromStudentMove();
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

            case CORTIGIANA:   /** 11 */
                Student choice = null;
                int i =0;
                //notify (observer)---->scelgo pedina da mettere nel table
                if(choice.getsColour().equals(SColor.GREEN)){
                    gameSession.getActivePlayer().getPersonalSchool().getGTable().add(choice);
                    getCoinFromStudentMove();
                }
                else if(choice.getsColour().equals(SColor.RED)){
                    gameSession.getActivePlayer().getPersonalSchool().getRTable().add(choice);
                    getCoinFromStudentMove();
                }
                else if(choice.getsColour().equals(SColor.YELLOW)){
                    gameSession.getActivePlayer().getPersonalSchool().getYTable().add(choice);
                    getCoinFromStudentMove();
                }
                else if(choice.getsColour().equals(SColor.PINK)){
                    gameSession.getActivePlayer().getPersonalSchool().getPTable().add(choice);
                    getCoinFromStudentMove();
                }
                else if(choice.getsColour().equals(SColor.BLUE)){
                    gameSession.getActivePlayer().getPersonalSchool().getBTable().add(choice);
                    getCoinFromStudentMove();
                }
                characterCardPlayed.getStudentsOnCard().remove(choice);
                //notify (observer)---->pesco pedina da mettere sulla carta
                characterCardPlayed.getStudentsOnCard().add(gameSession.getTable().getBag().get(gameSession.getTable().getBag().size() -1));
                gameSession.getTable().getBag().remove(gameSession.getTable().getBag().get(gameSession.getTable().getBag().size() -1));
                break;

            case RIGATTIERE:   /** 12 */
                SColor colorChoice = null;
                //notify (observer)---->scelgo un colore
                for(Player p : gameSession.getListOfPlayers()){
                    if(colorChoice.equals(SColor.GREEN)){
                        for(int j=0; j<3; j++){
                            if(gameSession.getActivePlayer().getPersonalSchool().getGTable().size() != 0)
                                gameSession.getActivePlayer().getPersonalSchool().getGTable().remove(gameSession.getActivePlayer().getPersonalSchool().getGTable().size()-1);
                        }
                    }
                    else if(colorChoice.equals(SColor.RED)){
                        for(int j=0; j<3; j++){
                            if(gameSession.getActivePlayer().getPersonalSchool().getRTable().size() != 0)
                                gameSession.getActivePlayer().getPersonalSchool().getRTable().remove(gameSession.getActivePlayer().getPersonalSchool().getRTable().size()-1);
                        }
                    }
                    else if(colorChoice.equals(SColor.YELLOW)){
                        for(int j=0; j<3; j++){
                            if(gameSession.getActivePlayer().getPersonalSchool().getYTable().size() != 0)
                                gameSession.getActivePlayer().getPersonalSchool().getYTable().remove(gameSession.getActivePlayer().getPersonalSchool().getYTable().size()-1);
                        }
                    }
                    else if(colorChoice.equals(SColor.PINK)){
                        for(int j=0; j<3; j++){
                            if(gameSession.getActivePlayer().getPersonalSchool().getPTable().size() != 0)
                                gameSession.getActivePlayer().getPersonalSchool().getPTable().remove(gameSession.getActivePlayer().getPersonalSchool().getPTable().size()-1);
                        }
                    }
                    else if(colorChoice.equals(SColor.BLUE)){
                        for(int j=0; j<3; j++){
                            if(gameSession.getActivePlayer().getPersonalSchool().getBTable().size() != 0)
                                gameSession.getActivePlayer().getPersonalSchool().getBTable().remove(gameSession.getActivePlayer().getPersonalSchool().getBTable().size()-1);
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

        if (playerChoice <= player.getTrash().getStepMotherEarth() && !cardEffectPlayed.isLatorePlayed()) {

            if ((table.getPosMotherEarth() + n) > table.getListOfIsland().size()) {
                table.getListOfIsland().get(table.getPosMotherEarth() + n - table.getListOfIsland().size() - 1).setMotherEarthOnIsland(true);
                table.setPosMotherEarth(table.getPosMotherEarth() + n - table.getListOfIsland().size());
            }
            else {
                table.getListOfIsland().get(table.getPosMotherEarth() + n - 1).setMotherEarthOnIsland(true);
                table.setPosMotherEarth(table.getPosMotherEarth() + n);
            }
        }
        /** EFFETTO LATORE */
        else if(playerChoice <= player.getTrash().getStepMotherEarth()+2 && cardEffectPlayed.isLatorePlayed()){
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

    public Game getGameSession() {
        return gameSession;
    }

    /** DA CONTROLLARE */
    /** inizia il turno */
    public void startTurn(){
        switch(gameState){
            case INIT:
                /** da spostare qui le funzioni di inizializzazione
                 * tavolo, scuola, nuvole, deckassistant, deckcharacter, ecc
                 * */
                break;
            case IN_GAME:
            case END_GAME:
                //allVirtualView.get(getActivePlayer()).askAction();
                break;
        }
    }

    public void setGameSession(Game gameSession) {
        this.gameSession = gameSession;
    }

    private void getCoinFromStudentMove() {
        if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE) && (gameSession.getActivePlayer().getPersonalSchool().getGTable().size()==3)){
            gameSession.getActivePlayer().setCoinScore(gameSession.getActivePlayer().getCoinScore() + 1);
            gameSession.getTable().setCoinsOnTable(gameSession.getTable().getCoinsOnTable() - 1);
        }
        else if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE) && (gameSession.getActivePlayer().getPersonalSchool().getGTable().size()==6)){
            gameSession.getActivePlayer().setCoinScore(gameSession.getActivePlayer().getCoinScore() + 1);
            gameSession.getTable().setCoinsOnTable(gameSession.getTable().getCoinsOnTable() - 1);
        }
        else if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE) && (gameSession.getActivePlayer().getPersonalSchool().getGTable().size()==9)){
            gameSession.getActivePlayer().setCoinScore(gameSession.getActivePlayer().getCoinScore() + 1);
            gameSession.getTable().setCoinsOnTable(gameSession.getTable().getCoinsOnTable() - 1);
        }
    }


    /**
     * METODI USATI PER CONNESSIONE CLIENT - SERVER
     * */

    public boolean hasInactivePlayers(){
        return turnController.hasInactivePlayers();
    }

    /** @return lista di nickname dei giocatori disconnessi dal gioco */
    public List<String> getInactivePlayers(){
        return turnController.getInactivePlayers();
    }

    /** DA COMPLETARE */
    /** riconnette giocatore che si era disconnesso, durante il gioco avviato */
    public void reconnect(String username, VirtualView virtualView){
        allVirtualView.put(username, virtualView);
        turnController.reconnect(username);
        //broadcastMessage(username + " has reconnected.");
        /** showPlayer(gameSession.getPlayer(username),username);
         allVirtualView.get(username).showMarket(gameSession.getMarket().getMarketTray(), gameSession.getMarket().getCornerMarble());
         allVirtualView.get(username).showDevMarket(gameSession.getCardMarket().availableCards(), gameSession.getCardMarket().remainingCards());
         allVirtualView.get(username).showPlayerTurn(getActivePlayer());
         */

    }

    /** se il gioco non è cominciato ----> return false */
    public boolean isGameStarted(){
        return gameState!=GameState.INIT;
    }

    /** rimouove giocatore dal gioco e controlla se era l'active player---> inizia nuovo turno */
    public void disconnect(String username){
        if(username.equals(getActivePlayer()))
        {

            if(turnController.nextPlayer().equals(turnController.firstPlayer())&&turnController.getActivePlayers().size()!=0){
                /** switch(gameState){
                    case DRAWLEADER:
                        if(maxPlayers>=2){
                            setGameState(GameState.GIVERES);}
                        break;
                    case GIVERES:
                        setGameState(GameState.IN_GAME);
                        for(String s: allVirtualView.keySet()){
                            if (!s.equals(getActivePlayer())){
                                allVirtualView.get(s).showPlayerTurn(getActivePlayer());
                            }
                        }
                        break;
                } */
            }
            turnController.disconnect(username);
            startTurn();
        }
        allVirtualView.remove(username);
        turnController.disconnect(username);
    }

    /** giocatore attiivo in quel momento */
    public String getActivePlayer(){
        return turnController.getActivePlayer();
    }

    public HashMap<String, VirtualView> getAllVirtualView() {
        return allVirtualView;
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
