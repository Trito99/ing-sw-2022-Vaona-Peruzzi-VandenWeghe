package it.polimi.ingsw.controller;


import it.polimi.ingsw.message.ClientMessage;
import it.polimi.ingsw.message.MessageType;
import it.polimi.ingsw.message.PlayersNumber;
import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.DeckCharacter;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.GameState;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.view.VirtualView;

import java.security.InvalidParameterException;
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

    private void generateTable(){
        gameSession.getTable().generateBagInit();
        gameSession.getTable().generateIslandCards();
        gameSession.getTable().generateMotherEarth();
        gameSession.getTable().extractStudentsInit();
        gameSession.getTable().addFinalStudents();
    }

    /**
     * @param nickname del Giocatore.
     * @param gameId id della partita a cui il giocatore sta giocando.
     */
    public boolean newPlayer(String nickname, String gameId, VirtualView virtualView) {
        if(allVirtualView.isEmpty()){
            allVirtualView.put(nickname, virtualView);
            virtualView.showLogin(nickname, gameId, true);
            virtualView.askPlayersNumber();
            return true;
        }
        else if(allVirtualView.size() < maxPlayers){
            /** da testare */
            if(allVirtualView.size() == 1){
                this.gameSession.addPlayer(new Player(TColor.BLACK, PlayerNumber.PLAYER2));
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setNickname(nickname);
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).generateSchool(gameSession.getTable(),gameSession.getGameMode());
            }
            else if(allVirtualView.size() == 2){
                this.gameSession.addPlayer(new Player(TColor.GREY, PlayerNumber.PLAYER3));
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setNickname(nickname);
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).generateSchool(gameSession.getTable(),gameSession.getGameMode());
            }
            else if(allVirtualView.size() == 3){
                this.gameSession.addPlayer(new Player(TColor.WHITE, PlayerNumber.PLAYER4));
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setNickname(nickname);
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).generateSchool(gameSession.getTable(),gameSession.getGameMode());
            }

            allVirtualView.put(nickname, virtualView);
            virtualView.showLogin(nickname, gameId,true);
            if(allVirtualView.size() == maxPlayers){
                initializeGame();
            }
            return true;
        }
        else virtualView.showLogin(nickname, gameId,false);
        return false;
    }

    /** maxPlayers(GameMode) e difficulty vanno scelti da chi crea la partita
    public void initializePlayer(VirtualView view) { // Setto i player a inizio partita
        String nickname = null;
        int year = 0;
        int month = 0;
        int dayOfMonth = 0;
        int i = 0;

        switch (gameSession.getGameMode()){
                /** Da inserire Nickname, data(età) ecc.. di tutti i player (Anche in Coop!!!)
            case TWOPLAYERS:
                System.out.println("Scegli il tuo nickname: ");
                /** funzione per leggere
                System.out.println("Inserisci la tua data di nascita: ");
                /** funzione per leggere
                GregorianCalendar dataPlayer = new GregorianCalendar(year, month , dayOfMonth);
                this.gameSession.addPlayer(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
                this.gameSession.getListOfPlayers().get(i).setPlayerDate(dataPlayer);
                this.gameSession.getListOfPlayers().get(i).setNickname(nickname);
                i++;

                //svuoto il campo
                dataPlayer = null;
                System.out.println("Scegli il tuo nickname: ");
                /** funzione per leggere
                System.out.println("Inserisci la tua età: ");
                /** funzione per leggere
                dataPlayer = new GregorianCalendar(year, month, dayOfMonth);
                this.gameSession.addPlayer(new Player(TColor.BLACK, PlayerNumber.PLAYER2));
                this.gameSession.getListOfPlayers().get(i).setPlayerDate(dataPlayer);
                this.gameSession.getListOfPlayers().get(i).setNickname(nickname);

                if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)){
                    for (Player p : gameSession.getListOfPlayers())
                        p.setCoinScore(1);
                    gameSession.getTable().setCoinsOnTable(18);
                }
                allVirtualView.put(nickname, view);
                break;
            case THREEPLAYERS:
                System.out.println("Scegli il tuo nickname: ");
                /** funzione per leggere
                System.out.println("Inserisci la tua data di nascita: ");
                /** funzione per leggere
                dataPlayer = new GregorianCalendar(year, month, dayOfMonth);
                this.gameSession.addPlayer(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
                this.gameSession.getListOfPlayers().get(i).setPlayerDate(dataPlayer);
                this.gameSession.getListOfPlayers().get(i).setNickname(nickname);
                i++;

                //svuoto il campo
                dataPlayer = null;
                System.out.println("Scegli il tuo nickname: ");
                /** funzione per leggere
                System.out.println("Inserisci la tua età: ");
                /** funzione per leggere *
                dataPlayer = new GregorianCalendar(year, month, dayOfMonth);
                this.gameSession.addPlayer(new Player(TColor.BLACK, PlayerNumber.PLAYER2));
                this.gameSession.getListOfPlayers().get(i).setPlayerDate(dataPlayer);
                this.gameSession.getListOfPlayers().get(i).setNickname(nickname);
                i++;

                //svuoto il campo
                dataPlayer = null;
                System.out.println("Scegli il tuo nickname: ");
                /** funzione per leggere
                System.out.println("Inserisci la tua età: ");
                /** funzione per leggere
                dataPlayer = new GregorianCalendar(year, month, dayOfMonth);
                this.gameSession.addPlayer(new Player(TColor.GREY, PlayerNumber.PLAYER3));
                this.gameSession.getListOfPlayers().get(i).setPlayerDate(dataPlayer);
                this.gameSession.getListOfPlayers().get(i).setNickname(nickname);

                if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)){
                    for (Player p : gameSession.getListOfPlayers())
                        p.setCoinScore(1);
                    gameSession.getTable().setCoinsOnTable(17);
                }
                allVirtualView.put(nickname, view);
                break;
            case COOP:

                this.gameSession.addPlayer(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
                this.gameSession.addPlayer(new Player(TColor.WHITE, PlayerNumber.PLAYER2));
                gameSession.getTeam().add(new Team()) ;
                gameSession.getTeam().get(0).intializeTeam(gameSession.getListOfPlayers().get(0), gameSession.getListOfPlayers().get(1), TColor.WHITE);

                this.gameSession.addPlayer(new Player(TColor.BLACK, PlayerNumber.PLAYER3));
                this.gameSession.addPlayer(new Player(TColor.BLACK, PlayerNumber.PLAYER4));
                gameSession.getTeam().add(new Team());
                gameSession.getTeam().get(1).intializeTeam(gameSession.getListOfPlayers().get(2), gameSession.getListOfPlayers().get(3), TColor.BLACK);

                if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)) {
                    for (Player p : gameSession.getListOfPlayers())
                            p.setCoinScore(1);
                    gameSession.getTable().setCoinsOnTable(16);
                }
                break;
            default:
                break;
        }
    } */

    public void initializeGame(){ /**Giocatori(+ personalSchool, +DeckAssistant), Table(isole, motherEarth, nuvole, bag, cartePersonaggioontable) */
        setGameState(GameState.INIT);
        turnController = new TurnController(this);
        switch(maxPlayers){
            case 4:

                gameSession.getTable().extractStudentOnCloud();

                if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)){
                    DeckCharacter characterDeck = new DeckCharacter();
                    characterDeck.generateCharacterDeck();
                    gameSession.getTable().generateCharacterCardsOnTable(characterDeck.getCharacterCards());
                }

                /** da decidere: cosa mostrare del giocatore a inizio gioco*/
                allVirtualView.get(gameSession.getPlayerListByNickname().get(3)).showPlayerList(gameSession.getPlayerListByNickname());
                break;
            case 3:

                if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)){
                    DeckCharacter characterDeck = new DeckCharacter();
                    characterDeck.generateCharacterDeck();
                    gameSession.getTable().generateCharacterCardsOnTable(characterDeck.getCharacterCards());
                }

                /** da decidere: cosa mostrare del giocatore a inizio gioco*/
                allVirtualView.get(gameSession.getPlayerListByNickname().get(2)).showPlayerList(gameSession.getPlayerListByNickname());
                break;
            case 2:

                if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)){
                    DeckCharacter characterDeck = new DeckCharacter();
                    characterDeck.generateCharacterDeck();
                    gameSession.getTable().generateCharacterCardsOnTable(characterDeck.getCharacterCards());
                }

                /** da decidere: cosa mostrare del giocatore a inizio gioco*/
                allVirtualView.get(gameSession.getPlayerListByNickname().get(1)).showPlayerList(gameSession.getPlayerListByNickname());
                break;
            default:
                break;
        }
        broadcastMessage("Everyone joined the game!");
        startTurn();
    }

    public void getMessage(ClientMessage receivedMessage) throws InvalidParameterException {
        switch (gameState) {
            case INIT:
                VirtualView virtualView = allVirtualView.get(receivedMessage.getNickname());
                generateTable();

                if(receivedMessage.getMessageType() == MessageType.PLAYERS_NUMBER){
                    PlayersNumber pnSelected = (PlayersNumber) receivedMessage;
                    maxPlayers = pnSelected.getPlayersNumber();
                    switch(pnSelected.getPlayersNumber()){
                        case 4:
                            gameSession.setGameMode(GameMode.COOP);
                            gameSession.getTable().generateCloudNumber(gameSession.getGameMode());
                            virtualView.showMessage("Gioco in modalità a squadre. \nIn attesa di altri giocatori...");
                            break;
                        case 3:
                            gameSession.setGameMode(GameMode.THREEPLAYERS);
                            gameSession.getTable().generateCloudNumber(gameSession.getGameMode());
                            virtualView.showMessage("Gioco in modalità a ("+pnSelected.getPlayersNumber()+") giocatori. \nIn attesa di altri giocatori...");
                            break;
                        case 2:
                            gameSession.setGameMode(GameMode.TWOPLAYERS);
                            gameSession.getTable().generateCloudNumber(gameSession.getGameMode());
                            virtualView.showMessage("Gioco in modalità a ("+pnSelected.getPlayersNumber()+") giocatori. \nIn attesa di altri giocatori...");
                            break;
                    }
                }
                break;
            case IN_GAME:
            case END_GAME:
                /** inGame è qualcosa che riconosca le varie azioni del gioco da svolgere, e chiama le funzioni*/
                //inGame(receivedMessage);
                break;
            default:
                String message = "Errore!";
                for (VirtualView vv : allVirtualView.values()) {
                    vv.showMessage(message);
                }
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
                gameSession.getTable().extractStudentOnCloud();
                break;
            case PLANNING:
                planning();
                break;
            case ACTION:
                action();
                break;
            case IN_GAME:
                //playAssistantCard(assistantName, nickname);
                //gameSession.getActivePlayer().getPersonalSchool().moveStudentFromEntryToIsland(choice2, choice3);
                //gameSession.getActivePlayer().getPersonalSchool().moveStudentInHall(choice1, choice2, choice3, choice4);
                //moveMotherEarth(choice5);
            case END_GAME:
                allVirtualView.get(getActivePlayer()).askAction();
                break;
        }
    }

    public void setGameSession(Game gameSession) {
        this.gameSession = gameSession;
    }

    public void planning(){
        /** fase pianificazione */
        for(String s: allVirtualView.keySet()){
            if (!s.equals(getActivePlayer())){
                allVirtualView.get(s).showPlayerTurn(getActivePlayer());
            }
        }
        ArrayList<AssistantCard> assistantCards = new ArrayList<>();
        for(int i=0; i<maxPlayers; i++){
            if (getActivePlayer().equals(gameSession.getListOfPlayers().get(i).getNickname())){
                Player currentPlayer = gameSession.getListOfPlayers().get(i);
                String currentDeck = currentPlayer.getDeckOfPlayer().getCardsInHand().get(0).getAssistantName();
                assistantCards.add(gameSession.playAssistantCard(currentDeck, getActivePlayer()));
            }
        }

        allVirtualView.get(getActivePlayer()).askAssistantCardToPlay(assistantCards);

    }

    public void action(){
        /** fase azione */
        for(String s: allVirtualView.keySet()){
            if (!s.equals(getActivePlayer())){
                allVirtualView.get(s).showPlayerTurn(getActivePlayer());
                allVirtualView.get(s).askCharacterCardToPlay(gameSession.getTable().getCharacterCardsOnTable());
            }
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

    /** riconnette giocatore che si era disconnesso, durante il gioco avviato */
    public void reconnect(String nickname, VirtualView virtualView){
        allVirtualView.put(nickname, virtualView);
        turnController.reconnect(nickname);
        broadcastMessage(nickname + " si è riconnesso.");
        showPlayer(gameSession.getPlayer(nickname),nickname);
        allVirtualView.get(nickname).showPersonalSchool(gameSession.getPlayer(nickname).getPersonalSchool());
        allVirtualView.get(nickname).showTable(gameSession.getTable());
        allVirtualView.get(nickname).showPlayerTurn(getActivePlayer());
    }

    /** rimouove giocatore dal gioco e controlla se era l'active player---> inizia nuovo turno */
    public void disconnect(String nickname){
        if(nickname.equals(getActivePlayer())) {
            if(turnController.nextPlayer().equals(turnController.firstPlayer())&&turnController.getActivePlayers().size()!=0){
                switch(gameState){
                    case INIT:
                        if(maxPlayers>=2){
                            setGameState(GameState.IN_GAME);}
                        break;
                    case IN_GAME:
                        for(String s: allVirtualView.keySet()){
                            if (!s.equals(getActivePlayer())){
                                allVirtualView.get(s).showPlayerTurn(getActivePlayer());
                            }
                        }
                        break;
                }
            }
            turnController.disconnect(nickname);
            startTurn();
        }
        allVirtualView.remove(nickname);
        turnController.disconnect(nickname);
    }

    /** se il gioco non è cominciato ----> return false */
    public boolean isGameStarted(){
        return gameState!=GameState.INIT;
    }

    /** giocatore attiivo in quel momento */
    public String getActivePlayer(){
        return turnController.getActivePlayer();
    }

    public HashMap<String, VirtualView> getAllVirtualView() {
        return allVirtualView;
    }

    public void showPlayer(Player player, String nickname){
        allVirtualView.get(nickname).showPlayer(player.getNickname(), player.getPlayerNumber(),  player.getTColor(),
                player.getInfluenceOnIsland(), player.getPersonalSchool(), player.getDeckOfPlayer(), player.getTrash(),
                player.getCoinScore(), nickname);
    }

    /** invia un messaggio a ogni giocatore del gioco */
    public void broadcastMessage(String message) {
        for (VirtualView vv : allVirtualView.values()) {
            vv.showMessage(message);
        }
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
