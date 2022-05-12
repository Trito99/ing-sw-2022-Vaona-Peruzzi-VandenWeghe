package it.polimi.ingsw.controller;


import it.polimi.ingsw.message.*;
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
    private TurnController turnController;
    private GameState gameState;
    private final HashMap<String, VirtualView> allVirtualView;
    private int roundIndex;
    boolean again=false;


    public GameController(){
        this.allVirtualView = new HashMap<>();
        gameSession = new Game();
        gameState = GameState.INIT;
        roundIndex =0;
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
    public boolean newPlayer(String nickname, String gameId, GregorianCalendar playerDate, VirtualView virtualView) {

        if(allVirtualView.isEmpty()){
            generateTable();
            allVirtualView.put(nickname, virtualView);
            this.gameSession.addPlayer(new Player(TColor.WHITE, PlayerNumber.PLAYER1));
            this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setNickname(nickname);
            this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setPlayerDate(playerDate);
            virtualView.showLogin(nickname, gameId, playerDate, true);
            virtualView.askPlayersNumberAndDifficulty();
            return true;
        }
        else if(allVirtualView.size() < maxPlayers){
            /** da testare */
            if(allVirtualView.size() == 1){
                allVirtualView.put(nickname, virtualView);
                this.gameSession.addPlayer(new Player(TColor.BLACK, PlayerNumber.PLAYER2));
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setNickname(nickname);
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setPlayerDate(playerDate);
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).generateSchool(gameSession.getTable(),gameSession.getGameMode());
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setDeckOfPlayer(new DeckAssistant(AssistantDeckName.DECK2));
                this.gameSession.getListOfPlayers().get(0).generateSchool(gameSession.getTable(),gameSession.getGameMode());
                this.gameSession.getListOfPlayers().get(0).setDeckOfPlayer(new DeckAssistant(AssistantDeckName.DECK1));
                virtualView.showMessage("Waiting for other players...");
            }
            else if(allVirtualView.size() == 2){
                allVirtualView.put(nickname, virtualView);
                this.gameSession.addPlayer(new Player(TColor.GREY, PlayerNumber.PLAYER3));
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setNickname(nickname);
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setPlayerDate(playerDate);
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).generateSchool(gameSession.getTable(),gameSession.getGameMode());
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setDeckOfPlayer(new DeckAssistant(AssistantDeckName.DECK3));
                virtualView.showMessage("Waiting for other players...");
            }
            else if(allVirtualView.size() == 3){
                allVirtualView.put(nickname, virtualView);
                this.gameSession.addPlayer(new Player(TColor.WHITE, PlayerNumber.PLAYER4));
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setNickname(nickname);
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setPlayerDate(playerDate);
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).generateSchool(gameSession.getTable(),gameSession.getGameMode());
                this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setDeckOfPlayer(new DeckAssistant(AssistantDeckName.DECK4));
                virtualView.showMessage("Waiting for other players...");
            }

            allVirtualView.put(nickname, virtualView);
            virtualView.showLogin(nickname, gameId, playerDate,true);

            if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE))
                initializeExpertModeGame();

            if(allVirtualView.size() == maxPlayers){
                broadcastMessage("Everyone joined the game!");
                turnController = new TurnController(this);
                gameState=GameState.PLANNING;
                startTurn();
            }
            return true;
        }
        else virtualView.showLogin(nickname, gameId, playerDate, false);
        return false;
    }

    private void initializeExpertModeGame(){ /**Giocatori(+ personalSchool, +DeckAssistant), Table(isole, motherEarth, nuvole, bag, cartePersonaggioontable) */
    DeckCharacter characterDeck = new DeckCharacter();
    characterDeck.generateCharacterDeck();
    gameSession.getTable().generateCharacterCardsOnTable(characterDeck.getCharacterCards());
    }

    public void getMessage(ClientMessage receivedMessage) throws InvalidParameterException {
        VirtualView virtualView = allVirtualView.get(receivedMessage.getNickname());
        switch (gameState) {
            case INIT:
                if(receivedMessage.getMessageType() == MessageType.PLAYERS_NUMBER_AND_DIFFICULTY){
                    PlayersNumberAndDifficulty PNaDSelected = (PlayersNumberAndDifficulty) receivedMessage;
                    maxPlayers = PNaDSelected.getPlayersNumber();
                    switch(PNaDSelected.getPlayersNumber()){
                        case 4:
                            gameSession.setGameMode(GameMode.COOP);
                            virtualView.showMessage("Coop Mode. \nWaiting for other players...");
                            break;
                        case 3:
                            gameSession.setGameMode(GameMode.THREEPLAYERS);
                            virtualView.showMessage("Three Players Mode. \nWaiting for other players...");
                            break;
                        case 2:
                            gameSession.setGameMode(GameMode.TWOPLAYERS);
                            virtualView.showMessage("Two Players Mode. \nWaiting for other players...");
                            break;
                    }
                    switch (PNaDSelected.getDifficulty()){
                        case STANDARDMODE:
                            gameSession.setDifficulty(Difficulty.STANDARDMODE);
                            break;
                        case EXPERTMODE:
                            gameSession.setDifficulty(Difficulty.EXPERTMODE);
                            break;
                    }
                    gameSession.getTable().generateCloudNumber(gameSession.getGameMode());
                }
                break;
            case PLANNING:
                if(receivedMessage.getMessageType() == MessageType.ASSISTANTCARD_PLAYED){
                    AssistantCardPlayed CardSelected = (AssistantCardPlayed) receivedMessage;
                    int indexOfCurrentPlayer=gameSession.getListOfPlayers().indexOf(gameSession.getPlayer(receivedMessage.getNickname()));
                    System.out.println(indexOfCurrentPlayer);
                    boolean present=false;
                    for(int i=0;i<indexOfCurrentPlayer;i++){
                        if(gameSession.getListOfPlayers().get(i).getTrash().getAssistantName().equals(CardSelected.getCardNickname()))
                            present=true;
                    }
                    if(!present) {
                        again = false;
                        gameSession.playAssistantCard(CardSelected.getCardNickname(), receivedMessage.getNickname());
                    }else{
                        virtualView.showMessage("\nCard already played by another player. Try again");
                        virtualView.askAssistantCardToPlay();
                        again=true;
                    }
                }
                if(!again) {
                    turnController.nextPlayer();
                    roundIndex++;
                    startTurn();
                }
                break;
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

    public GameState getGameState() {
        return gameState;
    }

    public Game getGameSession() {
        return gameSession;
    }

    /** DA CONTROLLARE */
    /** inizia il turno */
    public void startTurn(){
        gameSession.getTable().extractStudentOnCloud();
        showGame();
        if (roundIndex<maxPlayers)
            allVirtualView.get(turnController.getActivePlayer()).askAssistantCardToPlay();
        if (roundIndex==maxPlayers)
            roundIndex = 0;
        /**switch(gameState){
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
        }*/
    }

    private void showGame(){
        for(String s: allVirtualView.keySet()){
            allVirtualView.get(s).showTable(gameSession.getTable());
            for(Player p : gameSession.getListOfPlayers()) {
                if (p.getNickname() != s)
                    allVirtualView.get(s).showPersonalSchool(p.getPersonalSchool(), p.getNickname(),p.getTrash());
            }
            for(Player p : gameSession.getListOfPlayers()){
                if (p.getNickname()==s) {
                    allVirtualView.get(s).showPersonalSchool(p.getPersonalSchool(), p.getNickname(), p.getTrash());
                    allVirtualView.get(s).showDeckAssistant(p.getDeckOfPlayer(),p.getNickname());
                }
            }
            if (s == turnController.getActivePlayer())
                allVirtualView.get(s).showMessage("\n\nYour Turn");
            else
                allVirtualView.get(s).showMessage("\n\nTurn of " + turnController.getActivePlayer());
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

        //allVirtualView.get(getActivePlayer()).askAssistantCardToPlay(assistantCards);
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
        allVirtualView.get(nickname).showPersonalSchool(gameSession.getPlayer(nickname).getPersonalSchool(), nickname,gameSession.getPlayer(nickname).getTrash());
        allVirtualView.get(nickname).showTable(gameSession.getTable());
        allVirtualView.get(nickname).showPlayerTurn(getActivePlayer());
    }

    /** rimouove giocatore dal gioco e controlla se era l'active player---> inizia nuovo turno */
    public void disconnect(String nickname){
        if(nickname.equals(getActivePlayer())) {
           /* if(turnController.nextPlayer().equals(turnController.firstPlayer())&&turnController.getActivePlayers().size()!=0){ */
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
           // }
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
