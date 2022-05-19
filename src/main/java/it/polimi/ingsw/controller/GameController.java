package it.polimi.ingsw.controller;

import it.polimi.ingsw.message.*;
import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.character.DeckCharacter;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.game.*;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.view.VirtualView;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class GameController {
    private Game gameSession;
    private int maxPlayers, roundIndex, studentId, movedStudents=0;
    private TurnController turnController;
    private GameState gameState;
    private final HashMap<String, VirtualView> allVirtualView;
    boolean again=false, lastRound=false, card=false;
    private ActionState actionState;
    private CharacterCard characterCard;


    public GameController(){
        this.allVirtualView = new HashMap<>();
        gameSession = new Game();
        gameState = GameState.INIT;
        roundIndex =0;
    }

    public void generateTable(){
        gameSession.getTable().generateBagInit();
        gameSession.getTable().generateIslandCards();
        gameSession.getTable().generateMotherEarth();
        gameSession.getTable().extractStudentsInit();
        gameSession.getTable().addFinalStudents();
    }

    public void generatePlayer(String nickname,GregorianCalendar playerDate,TColor tColor,int index){
        this.gameSession.addPlayer(new Player(tColor, PlayerNumber.values()[index]));
        this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setNickname(nickname);
        this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setPlayerDate(playerDate);
        this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).generateSchool(gameSession.getTable(),gameSession.getGameMode());
        this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setDeckOfPlayer(new DeckAssistant(AssistantDeckName.values()[index]));
        if (gameSession.getDifficulty().equals(Difficulty.EXPERTMODE))
            this.gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setCoinScore(1);
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
            if(allVirtualView.size() == 1){
                allVirtualView.put(nickname, virtualView);
                generatePlayer(nickname,playerDate,TColor.BLACK,allVirtualView.size()-1);
                this.gameSession.getListOfPlayers().get(0).generateSchool(gameSession.getTable(),gameSession.getGameMode());
                this.gameSession.getListOfPlayers().get(0).setDeckOfPlayer(new DeckAssistant(AssistantDeckName.DECK1));
                if (gameSession.getDifficulty().equals(Difficulty.EXPERTMODE))
                    this.gameSession.getListOfPlayers().get(0).setCoinScore(1);
                virtualView.showLogin(nickname, gameId, playerDate,true);
                if(maxPlayers == 3)
                    virtualView.showMessage("Three Players Mode. You have BLACK towers! \nWaiting for other players...");
                else if(maxPlayers == 4)
                    virtualView.showMessage("Coop Mode. You have WHITE towers! \nWaiting for other players...");
                else if(maxPlayers == 2)
                    virtualView.showMessage("Two Players Mode. You have BLACK towers!");

            }
            else if(allVirtualView.size() == 2){
                allVirtualView.put(nickname, virtualView);
                if(maxPlayers == 3)
                    generatePlayer(nickname,playerDate,TColor.GREY,allVirtualView.size()-1);
                else
                    generatePlayer(nickname,playerDate,TColor.BLACK,allVirtualView.size()-1);
                allVirtualView.put(nickname, virtualView);
                virtualView.showLogin(nickname, gameId, playerDate,true);
                if(maxPlayers==3)
                    virtualView.showMessage("Three Players Mode. You have GREY towers! \nWaiting for other players...");
                else if(maxPlayers==4)
                    virtualView.showMessage("Coop Mode. You have BLACK towers! \nWaiting for other players...");

            }
            else if(allVirtualView.size() == 3){
                allVirtualView.put(nickname, virtualView);
                generatePlayer(nickname,playerDate,TColor.WHITE,allVirtualView.size()-1);
                virtualView.showLogin(nickname, gameId, playerDate,true);
                if(maxPlayers==4)
                    virtualView.showMessage("Coop Mode. You have BLACK towers! \nWaiting for other players...");

            }


            if(allVirtualView.size() == maxPlayers){
                broadcastMessage("Everyone joined the game!");
                turnController = new TurnController(this);
                gameState = GameState.PLANNING;
                gameSession.getTable().extractStudentOnCloud();
                planning();
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
                if(receivedMessage.getMessageType() == MessageType.PLAYERS_NUMBER_AND_DIFFICULTY_CHOSEN){
                    PlayersNumberAndDifficulty PNaDSelected = (PlayersNumberAndDifficulty) receivedMessage;
                    maxPlayers = PNaDSelected.getPlayersNumber();
                    switch(PNaDSelected.getPlayersNumber()){
                        case 4:
                            gameSession.setGameMode(GameMode.COOP);
                            virtualView.showMessage("Coop Mode. You have WHITE towers! \nWaiting for other players...");
                            break;
                        case 3:
                            gameSession.setGameMode(GameMode.THREEPLAYERS);
                            virtualView.showMessage("Three Players Mode. You have WHITE towers! \nWaiting for other players...");
                            break;
                        case 2:
                            gameSession.setGameMode(GameMode.TWOPLAYERS);
                            virtualView.showMessage("Two Players Mode. You have WHITE towers! \nWaiting for other players...");
                            break;
                    }
                    switch (PNaDSelected.getDifficulty()){
                        case STANDARDMODE:
                            gameSession.setDifficulty(Difficulty.STANDARDMODE);
                            break;
                        case EXPERTMODE:
                            gameSession.setDifficulty(Difficulty.EXPERTMODE);
                            initializeExpertModeGame();
                            break;
                    }
                    gameSession.getTable().generateCloudNumber(gameSession.getGameMode());
                }
                break;
            case PLANNING:
                if(receivedMessage.getMessageType() == MessageType.ASSISTANTCARD_PLAYED){
                    AssistantCardPlayed CardSelected = (AssistantCardPlayed) receivedMessage;
                    int indexOfCurrentPlayer=gameSession.getListOfPlayers().indexOf(gameSession.getPlayer(receivedMessage.getNickname()));
                    boolean present = false, exists = false;
                    for(AssistantCard assistantCard : gameSession.getPlayer(turnController.getActivePlayer()).getDeckOfPlayer().getCardsInHand()){
                        if(assistantCard.getAssistantName().equals(CardSelected.getCardNickname()))
                            exists = true;
                    }
                    if(gameSession.getPlayer(turnController.getActivePlayer()).getDeckOfPlayer().getCardsInHand().size()>1){
                        for (int i = 1; i < (roundIndex + 1); i++) {
                            if ((indexOfCurrentPlayer - i) < 0) {
                                if (gameSession.getListOfPlayers().get(indexOfCurrentPlayer - i + maxPlayers).getTrash().getAssistantName().equals(CardSelected.getCardNickname()))
                                    present = true;
                            } else {
                                if (gameSession.getListOfPlayers().get(indexOfCurrentPlayer - i).getTrash().getAssistantName().equals(CardSelected.getCardNickname()))
                                    present = true;
                            }
                        }
                    }
                    if (exists) {
                        if (!present) {
                            again = false;
                            gameSession.playAssistantCard(CardSelected.getCardNickname(), receivedMessage.getNickname());
                        } else {
                            virtualView.showMessage("\nCard already played by another player. Try again");
                            virtualView.askAssistantCardToPlay();
                            again = true;
                        }
                    }else{
                        virtualView.showMessage("\nCard doesn't exist or has already been played. Try again");
                        virtualView.askAssistantCardToPlay();
                        again = true;
                    }
                }
                if(!again) {
                    turnController.nextPlayer(turnController.getPlayerOrderByName());
                    roundIndex++;
                    planning();
                }
                break;
            case ACTION:
                boolean turnFinished = false;
                if(receivedMessage.getMessageType() == MessageType.PLACE_AND_STUDENT_FOR_MOVE_CHOSEN){
                    PlaceAndStudentForMoveChosen Choice = (PlaceAndStudentForMoveChosen) receivedMessage;
                    boolean present = false;
                    for(Student student : gameSession.getPlayer(Choice.getNickname()).getPersonalSchool().getEntry()){
                        if(student.getIdStudent() == Choice.getId()) {
                            present = true;
                            studentId = Choice.getId();
                        }
                    }
                    if(present){
                        if (Choice.getPlace().equals("SCHOOL") || (Choice.getPlace().equals(("ISLAND")))) {
                            again = false;
                            if (Choice.getPlace().equals("SCHOOL")) {
                                gameSession.getPlayer(turnController.getActivePlayer()).getPersonalSchool().moveStudentFromEntryToHall(gameSession.getPlayer(turnController.getActivePlayer()), studentId, gameSession.getTable(), gameSession.getDifficulty());
                                gameSession.getPlayer(turnController.getActivePlayer()).getPersonalSchool().winProf(gameSession.getListOfPlayers(), gameSession.getPlayer(turnController.getActivePlayer()), CardEffect.STANDARDMODE);
                                movedStudents++;
                                if (movedStudents == gameSession.getTable().getCloudNumber().get(0).getNumberOfSpaces()) {
                                    movedStudents=0;
                                    if (gameSession.getDifficulty().equals(Difficulty.EXPERTMODE))
                                        this.setActionState(ActionState.CHARACTER);
                                    else
                                        setActionState(ActionState.MOTHERNATURE);
                                }
                                action();
                            } else if (Choice.getPlace().equals("ISLAND")) {
                                virtualView.askId(true,null);
                            }
                        } else {
                            virtualView.showMessage("\nWrong input");
                            again = true;
                            virtualView.askPlaceAndStudentForMove(gameSession.getPlayer(turnController.getActivePlayer()).getPersonalSchool().getEntry());
                        }
                    }else{
                        virtualView.showMessage("\nStudent selected is not available");
                        again = true;
                        virtualView.askPlaceAndStudentForMove(gameSession.getPlayer(turnController.getActivePlayer()).getPersonalSchool().getEntry());
                    }
                }
                if(receivedMessage.getMessageType() == MessageType.ID_CHOSEN){
                    IdChosen Choice = (IdChosen) receivedMessage;
                    if(Choice.getChoice()) {
                        boolean present = false;
                        for (IslandCard island : gameSession.getTable().getListOfIsland()) {
                            if (island.getIdIsland() == Choice.getId()) {
                                present = true;
                            }
                        }
                        if (present) {
                            again = false;
                            if(!card) {
                                gameSession.moveStudentFromListToIsland(gameSession.getTable().getListOfIsland().get(Choice.getId() - 1), studentId, gameSession.getPlayer(turnController.getActivePlayer()).getPersonalSchool().getEntry());
                                movedStudents++;
                            } else {
                                gameSession.playCharacterCard(characterCard.getCardEffect(),Choice.getNickname(),studentId,Choice.getId(), null);
                                setActionState(ActionState.MOTHERNATURE);
                                card=false;
                            }

                            if (movedStudents == gameSession.getTable().getCloudNumber().get(0).getNumberOfSpaces()) {
                                movedStudents=0;
                                if (gameSession.getDifficulty().equals(Difficulty.EXPERTMODE))
                                    this.setActionState(ActionState.CHARACTER);
                                else
                                    setActionState(ActionState.MOTHERNATURE);
                            }
                            action();
                        } else {
                            virtualView.showMessage("\nIsland doesn't exist or doesn't exists");
                            again = true;
                            virtualView.askId(true,null);
                        }
                    }else{
                        boolean present = false;
                        for(Student student : characterCard.getStudentsOnCard()){
                            if(student.getIdStudent() == Choice.getId()) {
                                present = true;
                                studentId = Choice.getId();
                            }
                        }
                        if (present) {
                            again = false;
                            virtualView.askId(true,null);
                        }else {
                            virtualView.showMessage("\nStudent selected is not available");
                            again = true;
                            virtualView.askId(false,characterCard);
                        }
                    }
                }
                if(receivedMessage.getMessageType() == MessageType.CHARACTER_CARD_PLAYED){
                    CharacterCardPlayed CardSelected = (CharacterCardPlayed) receivedMessage;
                    boolean exists = false, enough = true, playable=false;
                    if(!CardSelected.getChoice()){
                        if(CardSelected.getCardNickname().equals("YES")) {
                            for(CharacterCard characterCard : gameSession.getTable().getCharacterCardsOnTable()){
                                if(characterCard.getCoinOnCard()) {
                                    if ((characterCard.getCostCharacter() + 1) <= gameSession.getPlayer(CardSelected.getNickname()).getCoinScore())
                                        playable = true;
                                }else{
                                    if(characterCard.getCostCharacter()<=gameSession.getPlayer(CardSelected.getNickname()).getCoinScore())
                                        playable = true;
                                }
                            }
                            if(playable)
                                virtualView.askCharacterCardToPlay(true, -1, null);
                            else{
                                virtualView.showMessage("\nYou don't have enough coins for any card");
                                setActionState(ActionState.MOTHERNATURE);
                                action();
                            }
                        }else if (CardSelected.getCardNickname().equals("NO")){
                            setActionState(ActionState.MOTHERNATURE);
                            action();
                        }else{
                            virtualView.showMessage("\nWrong Input");
                            virtualView.askCharacterCardToPlay(false, gameSession.getPlayer(turnController.getActivePlayer()).getCoinScore(), gameSession.getTable().getCharacterCardsOnTable());
                        }
                    }else {
                        for (CharacterCard cc : gameSession.getTable().getCharacterCardsOnTable()) {
                            if (cc.getCardEffect().toString().equals(CardSelected.getCardNickname())) {
                                exists = true;
                                characterCard = cc;
                            }
                        }
                        if (exists) {
                            if (characterCard.getCoinOnCard()) {
                                if (gameSession.getPlayer(CardSelected.getNickname()).getCoinScore() >= characterCard.getCostCharacter() + 1) {
                                    gameSession.decreaseCoinScore(CardSelected.getNickname(), characterCard.getCostCharacter() + 1);
                                    gameSession.getTable().increaseCoinsOnTable(characterCard.getCostCharacter() + 1);
                                } else {
                                    enough = false;
                                }
                            } else {
                                if (gameSession.getPlayer(CardSelected.getNickname()).getCoinScore() >= characterCard.getCostCharacter()) {
                                    gameSession.decreaseCoinScore(CardSelected.getNickname(), characterCard.getCostCharacter());
                                    gameSession.getTable().increaseCoinsOnTable(characterCard.getCostCharacter());
                                    characterCard.setCoinOnCard(true);
                                } else {
                                    enough = false;
                                }
                            }
                        }
                        if (exists && enough) {
                            again = false;
                            switch(characterCard.getCardEffect()){
                                case ABBOT:
                                    card = true;
                                    virtualView.askId(false,characterCard);
                                    break;
                                case HERALD:
                                    card = true;
                                    virtualView.askId(true,characterCard);
                                    break;
                                default:
                                    gameSession.playCharacterCard(characterCard.getCardEffect(), CardSelected.getNickname(), -1,-1 , turnController.getActivePlayer());
                                    characterCard.setCoinOnCard(true);
                                    setActionState(ActionState.MOTHERNATURE);
                                    action();
                                    break;
                            }
                        } else {
                            again = true;
                            if (exists)
                                virtualView.showMessage("\nYou don't have enough coins for this card");
                            else
                                virtualView.showMessage("\nEffect not present. Try again");
                            virtualView.askCharacterCardToPlay(true, -1, null);
                        }
                    }
                }
                if(receivedMessage.getMessageType() == MessageType.STEP_MOTHER_EARTH_CHOSEN){
                    MotherEarthStepsChosen step = (MotherEarthStepsChosen) receivedMessage;
                    if(step.getSteps()>gameSession.getPlayer(step.getNickname()).getTrash().getStepMotherEarth()) {
                        virtualView.showMessage("\nSteps selected more than maximum available");
                        again=true;
                        virtualView.askMotherEarthSteps(gameSession.getPlayer(step.getNickname()).getTrash());
                    }else{
                        again=false;
                        int steps = step.getSteps();
                        gameSession.getTable().moveMotherEarth(steps);
                        gameSession.getTable().getListOfIsland().get(gameSession.getTable().getPosMotherEarth() - 1).buildTowerOnIsland(gameSession.getListOfPlayers(), CardEffect.STANDARDMODE);
                        gameSession.getTable().joinIsland(gameSession.getTable().getListOfIsland());
                        setActionState(ActionState.CLOUDCARD);
                        action();
                    }
                }
                if(receivedMessage.getMessageType() == MessageType.CLOUD_CHOSEN){
                    CloudChosen Cloud = (CloudChosen) receivedMessage;
                    boolean present =false;
                    for(CloudCard cloud : gameSession.getTable().getCloudNumber()){
                        if(Cloud.getId()==cloud.getIdCloud()) {
                            if (!cloud.getStudentOnCloud().isEmpty())
                                present = true;
                        }
                    }
                    if(present) {
                        again=false;
                        gameSession.getPlayer(turnController.getActivePlayer()).getPersonalSchool().moveStudentFromCloudToEntry(gameSession.getTable().getCloudNumber().get(Cloud.getId() - 1));
                        turnFinished = true;
                    }else{
                        virtualView.showMessage("\nCloud is empty or doesn't exist");
                        again=true;
                        virtualView.askCloud(gameSession.getTable());
                    }
                }
                if(!again && turnFinished) {
                    turnController.nextPlayer(turnController.getNewPlayerOrderByName());
                    roundIndex++;
                    this.setActionState(ActionState.STUDENT);
                    showGame();
                    action();
                }
                break;
            default:
                String message = "Errore!";
                for (VirtualView vv : allVirtualView.values()) {
                    vv.showMessage(message);
                }
        }

    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    public void setActionState(ActionState actionState){
        this.actionState = actionState;
    }

    public ActionState getActionState() {
        return actionState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Game getGameSession() {
        return gameSession;
    }

    /** DA CONTROLLARE */
    /** inizia il turno */
    public void planning(){
        if (roundIndex < maxPlayers) {
            showGame();
            allVirtualView.get(turnController.getActivePlayer()).askAssistantCardToPlay();
        }
        if (roundIndex == maxPlayers) {
            if(gameSession.gameIsFinished(turnController.getActivePlayer()))
                endGame();
            else{
                roundIndex = 0;
                turnController.changeOrder();
                turnController.setPlayingPlayer(turnController.getNewPlayerOrderByName().get(0));
                this.setGameState(GameState.ACTION);
                this.setActionState(ActionState.STUDENT);
                showGame();
                action();
            }
        }
    }

    public void action(){
        if (roundIndex < maxPlayers) {
            switch(actionState) {
                case STUDENT:
                    allVirtualView.get(turnController.getActivePlayer()).askPlaceAndStudentForMove(gameSession.getPlayer(turnController.getActivePlayer()).getPersonalSchool().getEntry());
                    break;
                case CHARACTER:
                    allVirtualView.get(turnController.getActivePlayer()).askCharacterCardToPlay(false, gameSession.getPlayer(turnController.getActivePlayer()).getCoinScore(), gameSession.getTable().getCharacterCardsOnTable());
                    break;
                case MOTHERNATURE:
                    allVirtualView.get(turnController.getActivePlayer()).askMotherEarthSteps(gameSession.getPlayer(turnController.getActivePlayer()).getTrash());
                    break;
                case CLOUDCARD:
                    if (gameSession.gameIsFinished(turnController.getActivePlayer())){
                        endGame();
                    }else
                        allVirtualView.get(turnController.getActivePlayer()).askCloud(gameSession.getTable());
                    break;
                default:
                    break;
            }
        }
        if (roundIndex == maxPlayers) {
            turnController.setPlayingPlayer(turnController.getNewPlayerOrderByName().get(0));
            roundIndex = 0;
            setGameState(GameState.PLANNING);
            if(!lastRound) {
                if (gameSession.getTable().getBag().size() >= ((gameSession.getTable().getCloudNumber().size()) * (gameSession.getTable().getCloudNumber().get(0).getNumberOfSpaces()))) {
                    gameSession.getTable().extractStudentOnCloud();
                    planning();
                } else if (gameSession.getTable().getBag().size() < ((gameSession.getTable().getCloudNumber().size()) * (gameSession.getTable().getCloudNumber().get(0).getNumberOfSpaces())) && gameSession.getTable().getBag().size() > 0) {
                    planning();
                    lastRound = true;
                } else if (gameSession.getTable().getBag().isEmpty()) {
                    endGame();
                }
            }else
                endGame();
        }
    }

    private void showGame(){
        for(String s : allVirtualView.keySet()){
            allVirtualView.get(s).showTable(gameSession.getTable(), gameSession.getDifficulty());
            for(Player p : gameSession.getListOfPlayers()) {
                if (p.getNickname() != s)
                    allVirtualView.get(s).showPersonalSchool(p.getPersonalSchool(), p.getNickname(), p.getTrash(), gameSession.getDifficulty(), p.getCoinScore());
            }
            for(Player p : gameSession.getListOfPlayers()){
                if (p.getNickname() == s) {
                    allVirtualView.get(s).showPersonalSchool(p.getPersonalSchool(), p.getNickname(), p.getTrash(), gameSession.getDifficulty(), p.getCoinScore());
                    allVirtualView.get(s).showDeckAssistant(p.getDeckOfPlayer());
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

    public void endGame(){
        broadcastMessage("\n"+gameSession.getTable().playerIsWinning(gameSession).getNickname()+" WINS");
        for(String nickname: allVirtualView.keySet()) {
            if(nickname==gameSession.getTable().playerIsWinning(gameSession).getNickname())
                allVirtualView.get(nickname).showWinMessage();
            else
                allVirtualView.get(nickname).showLoseMessage(nickname);
        }
    }


    /**
     * METODI USATI PER CONNESSIONE CLIENT - SERVER
     * */

    /**
     public boolean hasInactivePlayers(){
     return turnController.hasInactivePlayers();
     }*/

    /** @return lista di nickname dei giocatori disconnessi dal gioco */
    /**
     public List<String> getInactivePlayers(){
     return turnController.getInactivePlayers();
     }/*

     /** riconnette giocatore che si era disconnesso, durante il gioco avviato */
    /**
     public void reconnect(String nickname, VirtualView virtualView){
     allVirtualView.put(nickname, virtualView);
     turnController.reconnect(nickname);
     broadcastMessage(nickname + " si è riconnesso.");
     showPlayer(gameSession.getPlayer(nickname),nickname);
     allVirtualView.get(nickname).showPersonalSchool(gameSession.getPlayer(nickname).getPersonalSchool(), nickname,gameSession.getPlayer(nickname).getTrash());
     allVirtualView.get(nickname).showTable(gameSession.getTable());
     allVirtualView.get(nickname).showPlayerTurn(getActivePlayer());
     }/*

     /** rimouove giocatore dal gioco e controlla se era l'active player---> inizia nuovo turno */
    public void disconnect(String nickname){
        allVirtualView.remove(nickname);
        System.out.println(allVirtualView.keySet());
    }

    /** se il gioco non è cominciato ----> return false */
    public boolean isGameStarted(){
        return (gameState != GameState.INIT && gameState!= GameState.END_GAME);
    }

    /** giocatore attiivo in quel momento */
    public String getActivePlayer(){
        return turnController.getActivePlayer();
    }

    public HashMap<String, VirtualView> getAllVirtualView() {
        return allVirtualView;
    }


    /** invia un messaggio a ogni giocatore del gioco */
    public void broadcastMessage(String message) {
        for (VirtualView vv : allVirtualView.values()) {
            //vv.showMessage(message);
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
