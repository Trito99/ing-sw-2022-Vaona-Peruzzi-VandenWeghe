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
import it.polimi.ingsw.model.player.Team;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.view.VirtualView;

import java.security.InvalidParameterException;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Manages the game evolution
 * Reads messages from the clients
 * Sends messages to the Virtual Views
 */

public class GameController {
    private Game gameSession;
    private int maxPlayers, roundIndex, studentId, movedStudents=0, acrobatIndex=0, round=1;
    private TurnController turnController;
    private GameState gameState;
    private HashMap<String, VirtualView> allVirtualView;
    boolean again = false, lastRound = false, card = false, cardPlayed = false, characterCardAlreadyPlayed = false;
    private ActionState actionState;
    private CharacterCard characterCard;
    private ActionState savedActionState;


    /**
     * GameController constructor
     */
    public GameController(){
        this.allVirtualView = new HashMap<>();
        gameSession = new Game();
        gameState = GameState.INIT;
        roundIndex =0;
    }

    /**
     * Sets up the initial state of the table of the match
     */
    public void generateTable(){
        gameSession.getTable().generateBagInit();
        gameSession.getTable().generateIslandCards();
        gameSession.getTable().generateMotherEarth();
        gameSession.getTable().extractStudentsInit();
        gameSession.getTable().addFinalStudents();
    }

    /**
     * Sets up the information of the player added to the match
     * @param nickname nick of the player added to the match
     * @param playerDate date of the player added to the match
     * @param index index for setting the PlayerNumber
     */
    public void generatePlayer(String nickname,GregorianCalendar playerDate, int index){
        gameSession.addPlayer(new Player(null, PlayerNumber.values()[index]));
        gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setNickname(nickname);
        gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setPlayerDate(playerDate);
        if (gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)) {
            gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size() - 1).setCoinScore(1);
            gameSession.getTable().decreaseCoinsOnTable(1);
        }
    }

    /**
     * Adds a player to the list of players of the match and initialize his dashboard and the match
     * @param nickname of the player added to the match
     * @param gameId id of the match where we want to add the player
     * @param playerDate of the player added to the match
     * @param virtualView virtual view of the player added
     * @return if the player joined successfully the game
     */
    public boolean newPlayer(String nickname, String gameId, GregorianCalendar playerDate, VirtualView virtualView) {
        /** If the player added is the first of the match, ask him to choose the gamemode and the difficulty of the match and set up the table */
        if(allVirtualView.isEmpty()){
            generateTable();
            allVirtualView.put(nickname, virtualView);
            gameSession.addPlayer(new Player(null, PlayerNumber.PLAYER1));
            gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setNickname(nickname);
            gameSession.getListOfPlayers().get(gameSession.getListOfPlayers().size()-1).setPlayerDate(playerDate);
            virtualView.showLogin(nickname, gameId, playerDate, true);
            virtualView.askPlayersNumberAndDifficulty();
            return true;
        }
        else if(allVirtualView.size() < maxPlayers){
            if(allVirtualView.size() == 1){
                allVirtualView.put(nickname, virtualView);
                if (gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)) {
                    gameSession.getTable().setCoinsOnTable(20);
                    gameSession.getListOfPlayers().get(0).setCoinScore(1);
                    gameSession.getTable().decreaseCoinsOnTable(1);
                }
                generatePlayer(nickname,playerDate,allVirtualView.size()-1);
                virtualView.showLogin(nickname, gameId, playerDate,true);
                virtualView.askTowerColorAndDeck(gameSession.getTowerColors(),gameSession.getAssistantDeckNames());

            }
            else if(allVirtualView.size() == 2){
                allVirtualView.put(nickname, virtualView);
                if(maxPlayers == 3)
                    generatePlayer(nickname,playerDate,allVirtualView.size()-1);
                else
                    generatePlayer(nickname,playerDate,allVirtualView.size()-1);
                allVirtualView.put(nickname, virtualView);
                virtualView.showLogin(nickname, gameId, playerDate,true);
                virtualView.askTowerColorAndDeck(gameSession.getTowerColors(),gameSession.getAssistantDeckNames());
            }
            else if(allVirtualView.size() == 3){
                allVirtualView.put(nickname, virtualView);
                generatePlayer(nickname,playerDate,allVirtualView.size()-1);
                virtualView.showLogin(nickname, gameId, playerDate,true);
                virtualView.askTowerColorAndDeck(gameSession.getTowerColors(),gameSession.getAssistantDeckNames());
            }
            return true;
        }
        else virtualView.showLogin(nickname, gameId, playerDate, false);
        return false;
    }

    /**
     * Sets up the cards on table for the expert mode
     */
    private void initializeExpertModeGame(){
        DeckCharacter characterDeck = new DeckCharacter();
        characterDeck.generateCharacterDeck();
        gameSession.getTable().generateCharacterCardsOnTable(characterDeck.getCharacterCards());
    }

    /**
     * Receives messages of choice from the client
     * @param receivedMessage
     * @throws InvalidParameterException
     */
    public void getMessage(ClientMessage receivedMessage) throws InvalidParameterException {
        VirtualView virtualView = allVirtualView.get(receivedMessage.getNickname());
        switch (gameState) {
            /** Phase of set up of the match */
            case INIT:
                if(receivedMessage.getMessageType() == MessageType.PLAYERS_NUMBER_AND_DIFFICULTY_CHOSEN){
                    PlayersNumberAndDifficulty PNaDSelected = (PlayersNumberAndDifficulty) receivedMessage;
                    maxPlayers = PNaDSelected.getPlayersNumber();
                    switch(PNaDSelected.getPlayersNumber()){
                        case 4:
                            gameSession.setGameMode(GameMode.COOP);
                            break;
                        case 3:
                            gameSession.setGameMode(GameMode.THREEPLAYERS);
                            break;
                        case 2:
                            gameSession.setGameMode(GameMode.TWOPLAYERS);
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
                    gameSession.generateTowerColorsAndAssistant();
                    virtualView.askTowerColorAndDeck(gameSession.getTowerColors(),gameSession.getAssistantDeckNames());
                }
                if(receivedMessage.getMessageType() == MessageType.TOWER_COLOR_AND_DECK_CHOSEN){
                    /** Boolean to manage the error of choice that the client could make*/
                    boolean colorPresent = false, deckPresent = false;
                    TowerColorAndDeckChosen TCaDSelected = (TowerColorAndDeckChosen) receivedMessage;
                    for (TColor color : gameSession.getTowerColors()){
                        if (color.toString().equals(TCaDSelected.getTowerColor().toString()))
                            colorPresent = true;
                    }
                    for (AssistantDeckName assistantDeckName : gameSession.getAssistantDeckNames()){
                        if (assistantDeckName.toString().equals(TCaDSelected.getAssistantDeckName().toString()))
                            deckPresent = true;
                    }
                    /** */
                    if(colorPresent && deckPresent) {
                        again = false;
                        gameSession.getPlayer(TCaDSelected.getNickname()).setTColor(TCaDSelected.getTowerColor());
                        gameSession.getPlayer(TCaDSelected.getNickname()).generateSchool(gameSession.getTable(),gameSession.getGameMode());
                        gameSession.getPlayer(TCaDSelected.getNickname()).setDeckOfPlayer(new DeckAssistant(TCaDSelected.getAssistantDeckName()));
                        gameSession.getTowerColors().remove((TCaDSelected.getTowerColor()));
                        gameSession.getAssistantDeckNames().remove((TCaDSelected.getAssistantDeckName()));
                        virtualView.showWaitingMessage(gameSession.getGameMode()+" Mode.You have "+gameSession.getPlayer(TCaDSelected.getNickname()).getTColor()+" towers! \nWaiting for other players...");
                        /** If it's coop Mode, set up the teams and the teamMates */
                        if(gameSession.getPlayer(TCaDSelected.getNickname()).getPlayerNumber().equals(PlayerNumber.PLAYER4)) {
                            for (Player player : gameSession.getListOfPlayers()) {
                                for (Player player2 : gameSession.getListOfPlayers()) {
                                    if (!player2.equals(player)) {
                                        if (player.getTColor().equals(player2.getTColor())) {
                                            player.setTeamMate(player2.getNickname());
                                            boolean present = false;
                                            for (Team team : gameSession.getTeams()) {
                                                if (team.getTeamColor().equals(player.getTColor())) {
                                                    present = true;
                                                    break;
                                                }
                                            }
                                            if (!present) {
                                                gameSession.getTeams().add(new Team(player, player2, player.getTColor()));
                                            }
                                        }
                                    }
                                }
                                System.out.println(gameSession.getTeams().size());
                            }
                            for (int i = 0; i < 2; i++) {
                                for (Player player : gameSession.getListOfPlayers()) {
                                    if (player.getTColor().equals(TColor.values()[i]) && !player.getPersonalSchool().getTowers().isEmpty()) {
                                        gameSession.getPlayer(player.getTeamMate()).getPersonalSchool().getTowers().clear();
                                        player.setTeamLeader(true);
                                    }
                                }
                            }
                            for (String nick : allVirtualView.keySet()) {
                                allVirtualView.get(nick).showMessage("Your teamMate is " + gameSession.getPlayer(nick).getTeamMate());

                            }
                        }
                    }
                    else {
                        virtualView.showMessage("\nTower Color or Assistant Deck isn't available ");
                        virtualView.askTowerColorAndDeck(gameSession.getTowerColors(), gameSession.getAssistantDeckNames());
                        again = true;
                    }

                    if(allVirtualView.size() == maxPlayers){
                        boolean chosenColor = true;
                        for(Player player : gameSession.getListOfPlayers()){
                            if (player.getTColor() == null) {
                                chosenColor = false;
                                break;
                            }
                        }
                        if(chosenColor) {
                            broadcastMessage("Everyone joined the game!");
                            turnController = new TurnController(this);
                            gameState = GameState.PLANNING;
                            gameSession.getTable().extractStudentOnCloud();
                            planning();
                        }
                    }
                }
                break;

            case PLANNING:
                if(receivedMessage.getMessageType() == MessageType.ASSISTANT_CARD_PLAYED){
                    AssistantCardPlayed CardSelected = (AssistantCardPlayed) receivedMessage;
                    int indexOfCurrentPlayer=gameSession.getListOfPlayers().indexOf(gameSession.getPlayer(receivedMessage.getNickname()));
                    /** Boolean to manage the error of choice that the client could make */
                    boolean present = false, exists = false;
                    for(AssistantCard assistantCard : gameSession.getPlayer(getActivePlayer()).getDeckOfPlayer().getCardsInHand()){
                        if (assistantCard.getAssistantName().equals(CardSelected.getCardNickname())) {
                            exists = true;
                            break;
                        }
                    }
                    if(gameSession.getPlayer(getActivePlayer()).getDeckOfPlayer().getCardsInHand().size()>1){
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
                            /** if the card selected exists and wasn't already chosen, play it */
                            again = false;
                            gameSession.playAssistantCard(CardSelected.getCardNickname(), receivedMessage.getNickname());
                            virtualView.showDeckAssistant(gameSession.getPlayer(getActivePlayer()).getDeckOfPlayer());
                        } else {
                            virtualView.showMessage("\nCard already played by another player. Try again ");
                            virtualView.askAssistantCardToPlay();
                            again = true;
                        }
                    }else{
                        virtualView.showMessage("\nCard doesn't exist or has already been played. Try again ");
                        virtualView.askAssistantCardToPlay();
                        again = true;
                    }
                }
                /** If the card selected is correct, go to the next player */
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
                    /** Boolean to manage the error of choice that the client could make */
                    boolean present = false, full = false;
                    SColor studentColor = null;

                    for(Student student : gameSession.getPlayer(Choice.getNickname()).getPersonalSchool().getEntry()){
                        if(student.getIdStudent() == Choice.getId()) {
                            present = true;
                            studentId = student.getIdStudent();
                            studentColor = student.getSColor();
                        }
                    }

                    if(Choice.getPlace().equals("CHARACTER CARD")){
                        if(gameSession.getDifficulty().equals(Difficulty.STANDARDMODE)) {
                            virtualView.showMessage("You are playing the standard mode, you can't use character cards! ");
                            virtualView.askPlaceAndStudentForMove(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getEntry());
                        }
                        else if(characterCardAlreadyPlayed){
                            virtualView.showMessage("You can't activate 2 effects in a round! ");
                            virtualView.askPlaceAndStudentForMove(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getEntry());
                        }
                        else {
                            if (!(getActionState().equals(ActionState.CHARACTER)))
                                savedActionState = getActionState();
                            setActionState(ActionState.CHARACTER);
                            action();
                        }
                    }

                    /** the student selected is available */
                    if(present){
                        if (Choice.getPlace().equals("SCHOOL") || (Choice.getPlace().equals(("ISLAND")))) {
                            again = false;
                            if (Choice.getPlace().equals("SCHOOL")) {
                                /** Verify that the table of the color selected isn't already full */
                                switch(studentColor){
                                    case GREEN:
                                        if(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getGTable().size()==10)
                                            full = true;
                                        break;
                                    case RED:
                                        if(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getRTable().size()==10)
                                            full = true;
                                        break;
                                    case YELLOW:
                                        if(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getYTable().size()==10)
                                            full = true;
                                        break;
                                    case PINK:
                                        if(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getPTable().size()==10)
                                            full = true;
                                        break;
                                    case BLUE:
                                        if(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getBTable().size()==10)
                                            full = true;
                                        break;
                                }
                                if(!full) {
                                    gameSession.moveStudentFromListToHall(gameSession.getPlayer(getActivePlayer()), studentId, gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getEntry());
                                    virtualView.showCoinAndCharacterCards(gameSession.getTable().getCoinsOnTable(),gameSession.getTable().getCharacterCardsOnTable());
                                    if(gameSession.getDifficulty().equals(Difficulty.EXPERTMODE) && characterCard!=null) {
                                        gameSession.getPlayer(getActivePlayer()).getPersonalSchool().winProf(gameSession.getListOfPlayers(), gameSession.getPlayer(getActivePlayer()), characterCard.getCardEffect());
                                    }else
                                        gameSession.getPlayer(getActivePlayer()).getPersonalSchool().winProf(gameSession.getListOfPlayers(), gameSession.getPlayer(getActivePlayer()), CardEffect.STANDARDMODE);
                                    virtualView.showPersonalSchool(gameSession.getPlayer(getActivePlayer()).getPersonalSchool(), "Your ",gameSession.getPlayer(getActivePlayer()).getTrash(), gameSession.getDifficulty(), gameSession.getPlayer(getActivePlayer()).getCoinScore(), gameSession.getGameMode(), gameSession.getPlayer(getActivePlayer()).getTeamMate());
                                    for (Player p : gameSession.getListOfPlayers()) {
                                        if(p.getNickname()!=turnController.getActivePlayer())
                                            virtualView.showPersonalSchool(p.getPersonalSchool(), p.getNickname() + "'s ", p.getTrash(), gameSession.getDifficulty(), p.getCoinScore(), gameSession.getGameMode(), p.getTeamMate());
                                    }
                                    movedStudents++;
                                    allStudentsMoved();
                                }else{
                                    virtualView.showMessage("Table already full. Select another student ");
                                    again = true;
                                    virtualView.askPlaceAndStudentForMove(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getEntry());
                                }
                            } else if (Choice.getPlace().equals("ISLAND")) {
                                virtualView.askId(true,null,-1, null);
                            }
                        } else {
                            virtualView.showMessage("\nWrong input  ");
                            again = true;

                            virtualView.askPlaceAndStudentForMove(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getEntry());
                        }
                    }
                    else if(!Choice.getPlace().equals("CHARACTER CARD")){
                        virtualView.showMessage("\nStudent selected is not available ");
                        again = true;
                        virtualView.askPlaceAndStudentForMove(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getEntry());
                    }
                }
                if(receivedMessage.getMessageType() == MessageType.CHARACTER_CARD_PLAYED){
                    CharacterCardPlayed CardSelected = (CharacterCardPlayed) receivedMessage;
                    /** Boolean to manage the error of choice that the client could make */
                    boolean exists = false, enough = true, playable = false, changeIdea = false, cantPlay = false;
                    if(!CardSelected.getChoice()){
                        if(CardSelected.getCardNickname().equals("YES")) {
                            for(CharacterCard characterCard : gameSession.getTable().getCharacterCardsOnTable()){
                                /**Checks if the player can buy any character card */
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
                                virtualView.showMessage("\nYou don't have enough coins for any card ");
                                setActionState(savedActionState);
                                action();
                            }
                        }else if (CardSelected.getCardNickname().equals("NO")){
                            setActionState(savedActionState);
                            action();
                        }else{
                            virtualView.showMessage("\nWrong input  ");
                            virtualView.askCharacterCardToPlay(false, gameSession.getPlayer(getActivePlayer()).getCoinScore(), gameSession.getTable().getCharacterCardsOnTable());
                        }
                    }else {
                        /** Checks if the card selected exists */
                        for (CharacterCard cc : gameSession.getTable().getCharacterCardsOnTable()) {
                            if (cc.getCardEffect().toString().equals(CardSelected.getCardNickname())) {
                                exists = true;
                                characterCard = cc;
                            }
                        }
                        /** client changes idea and doesn't want to buy a card */
                        if (CardSelected.getCardNickname().equals("NONE")){
                            changeIdea = true;
                            setActionState(savedActionState);
                            action();
                        }
                        /** if the card selected is bard checks if the player has any students in the hall */
                        else if (CardSelected.getCardNickname().equals("BARD")){
                            cantPlay = true;
                            for(SColor s : SColor.values()){
                                if (gameSession.getPlayer(getActivePlayer()).getPersonalSchool().numberOfStudentsInHall(s) != 0){
                                    cantPlay = false;
                                    break;
                                }
                            }
                        }
                        boolean empty = false;
                        if (exists) {
                            /** decrease the money of the player */
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
                                    gameSession.getTable().increaseCoinsOnTable(characterCard.getCostCharacter()-1);
                                    characterCard.setCoinOnCard(true);
                                } else {
                                    enough = false;
                                }
                            }
                            switch(characterCard.getCardEffect()){
                                case ACROBAT:
                                case COURTESAN:
                                case ABBOT:
                                    if (characterCard.getStudentsOnCard().isEmpty())
                                        empty = true;
                                    break;
                                case CURATOR:
                                    if (characterCard.getXCardOnCard()==0)
                                        empty = true;
                                    break;

                            }
                        }
                        if (exists && enough && !empty && !cantPlay) {
                            again = false;
                            /** Play the card selected */
                            switch(characterCard.getCardEffect()){
                                case ABBOT:
                                case ACROBAT:
                                case COURTESAN:
                                case BARD:
                                    card = true;
                                    broadcastMessage(gameSession.getPlayer(getActivePlayer()).getNickname() +" has activated " + characterCard.getCardEffect().toString() + " effect!");
                                    virtualView.askId(false,characterCard,acrobatIndex, gameSession.getPlayer(getActivePlayer()).getPersonalSchool());
                                    break;
                                case HERALD:
                                case CURATOR:
                                    card = true;
                                    broadcastMessage(gameSession.getPlayer(getActivePlayer()).getNickname() +" has activated " + characterCard.getCardEffect().toString() + " effect!");
                                    virtualView.askId(true, characterCard, -1, null);
                                    break;
                                case HERBALIST:
                                case JUNKDEALER:
                                    broadcastMessage(gameSession.getPlayer(getActivePlayer()).getNickname() +" has activated " + characterCard.getCardEffect().toString() + " effect!");
                                    virtualView.askColorToBlock(characterCard.getCardEffect());
                                    break;
                                default:
                                    broadcastMessage(gameSession.getPlayer(getActivePlayer()).getNickname() +" has activated " + characterCard.getCardEffect().toString() + " effect!");
                                    gameSession.playCharacterCard(characterCard.getCardEffect(), CardSelected.getNickname(), -1,-1 , -1, null);
                                    characterCard.setCoinOnCard(true);
                                    virtualView.showCoinAndCharacterCards(gameSession.getTable().getCoinsOnTable(),gameSession.getTable().getCharacterCardsOnTable());
                                    cardPlayed=true;
                                    setActionState(savedActionState);
                                    action();
                                    break;

                            }
                            characterCardAlreadyPlayed = true;
                        } else {
                            again = true;
                            if (exists && !empty && !cantPlay) {
                                virtualView.showMessage("\nYou don't have enough coins for this card ");
                                setActionState(savedActionState);
                                action();
                            }else if (!exists)
                                virtualView.showMessage("\nEffect not present. Try again ");
                            else if (empty ) {
                                virtualView.showMessage("\nCard selected is empty ");
                                setActionState(savedActionState);
                                action();
                            }else if(cantPlay) {
                                characterCard.setCoinOnCard(false);
                                gameSession.getPlayer(getActivePlayer()).setCoinScore(gameSession.getPlayer(getActivePlayer()).getCoinScore() + 1);
                                virtualView.showMessage("\nYou can't play bard effect if you don't have any students in hall. Choose another effect ");
                            }else if (changeIdea)
                                virtualView.askCharacterCardToPlay(true, -1, null);
                        }
                    }
                }
                if(receivedMessage.getMessageType() == MessageType.ID_CHOSEN){
                    IdChosen Choice = (IdChosen) receivedMessage;
                    boolean present = false;
                    if(Choice.getChoice()) {
                        /** Verify that the island selected is available */
                        for (IslandCard island : gameSession.getTable().getListOfIsland()) {
                            if (island.getIdIsland() == Choice.getId()) {
                                present = true;
                                break;
                            }
                        }
                        if (present) {
                            again = false;
                            /** If it's not activated an effect, move the student to the island selected  */
                            if(!card) {
                                gameSession.moveStudentFromListToIsland(gameSession.getTable().getListOfIsland().get(Choice.getId() - 1), studentId, gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getEntry());
                                virtualView.showListOfIsland(gameSession.getTable(),gameSession.getDifficulty());
                                virtualView.showPersonalSchool(gameSession.getPlayer(getActivePlayer()).getPersonalSchool(), "Your ",gameSession.getPlayer(getActivePlayer()).getTrash(), gameSession.getDifficulty(), gameSession.getPlayer(getActivePlayer()).getCoinScore(), gameSession.getGameMode(), gameSession.getPlayer(getActivePlayer()).getTeamMate());
                                movedStudents++;
                            } else {
                                gameSession.playCharacterCard(characterCard.getCardEffect(),Choice.getNickname(),studentId,Choice.getId(), -1, null);
                                virtualView.showCoinAndCharacterCards(gameSession.getTable().getCoinsOnTable(),gameSession.getTable().getCharacterCardsOnTable());
                                virtualView.showListOfIsland(gameSession.getTable(),gameSession.getDifficulty());
                                cardPlayed=true;
                                setActionState(savedActionState);
                                card = false;
                            }
                            allStudentsMoved();
                        } else {
                            virtualView.showMessage("Island selected doesn't exist. Select another island ");
                            again = true;
                            virtualView.askId(true, characterCard,-1, null);
                        }
                    }else{
                        if(!characterCard.getCardEffect().equals(CardEffect.ACROBAT) && !characterCard.getCardEffect().equals(CardEffect.COURTESAN) && !characterCard.getCardEffect().equals(CardEffect.BARD)){
                            for (Student student : characterCard.getStudentsOnCard()) {
                                if (student.getIdStudent() == Choice.getId()) {
                                    present = true;
                                    studentId = Choice.getId();
                                    break;
                                }
                            }
                            if (present) {
                                again = false;
                                virtualView.askId(true,null,-1, null);
                            }else {
                                virtualView.showMessage("\nStudent selected is not available ");
                                again = true;
                                virtualView.askId(false,characterCard,-1, null);
                            }
                        }else if (characterCard.getCardEffect().equals(CardEffect.ACROBAT) || characterCard.getCardEffect().equals(CardEffect.BARD)){
                            if(Choice.getIndex()%2==1) {
                                int studentIdCard = -1, max;
                                /** Acrobat effect */
                                if(characterCard.getCardEffect().equals(CardEffect.ACROBAT)) {
                                    max = 6;
                                    for (Student student : characterCard.getStudentsOnCard()) {
                                        if (student.getIdStudent() == Choice.getId()) {
                                            present = true;
                                            studentIdCard = Choice.getId();
                                            break;
                                        }
                                    }
                                }else{
                                    /** Bard effect */
                                    max = 4;
                                    for (Student student : gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getGTable()) {
                                        if (student.getIdStudent() == Choice.getId() && gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getGTable().indexOf(student) == (gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getGTable().size()-1)) {
                                            present = true;
                                            studentIdCard = Choice.getId();
                                        }
                                    }
                                    for (Student student : gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getRTable()) {
                                        if (student.getIdStudent() == Choice.getId() && gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getRTable().indexOf(student) == (gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getRTable().size()-1)) {
                                            present = true;
                                            studentIdCard = Choice.getId();
                                        }
                                    }
                                    for (Student student : gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getYTable()) {
                                        if (student.getIdStudent() == Choice.getId() && gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getYTable().indexOf(student) == (gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getYTable().size()-1)) {
                                            present = true;
                                            studentIdCard = Choice.getId();
                                        }
                                    }
                                    for (Student student : gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getPTable()) {
                                        if (student.getIdStudent() == Choice.getId() && gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getPTable().indexOf(student) == (gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getPTable().size()-1)) {
                                            present = true;
                                            studentIdCard = Choice.getId();
                                        }
                                    }
                                    for (Student student : gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getBTable()) {
                                        if (student.getIdStudent() == Choice.getId() && gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getBTable().indexOf(student) == (gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getBTable().size()-1)) {
                                            present = true;
                                            studentIdCard = Choice.getId();
                                        }
                                    }
                                }
                                if (present) {
                                    again = false;
                                    acrobatIndex++;
                                    gameSession.playCharacterCard(characterCard.getCardEffect(), Choice.getNickname(), studentIdCard, -1, studentId, null);
                                    virtualView.showPersonalSchool(gameSession.getPlayer(getActivePlayer()).getPersonalSchool(), "Your ",gameSession.getPlayer(getActivePlayer()).getTrash(), gameSession.getDifficulty(), gameSession.getPlayer(getActivePlayer()).getCoinScore(), gameSession.getGameMode(), gameSession.getPlayer(getActivePlayer()).getTeamMate());
                                    virtualView.showCoinAndCharacterCards(gameSession.getTable().getCoinsOnTable(),gameSession.getTable().getCharacterCardsOnTable());
                                    if (acrobatIndex<max)
                                        virtualView.askId(false, characterCard, acrobatIndex, gameSession.getPlayer(getActivePlayer()).getPersonalSchool());
                                }else{
                                    if (characterCard.getCardEffect().equals(CardEffect.ACROBAT))
                                        virtualView.showMessage("\nStudent selected is not available ");
                                    else
                                        virtualView.showMessage("\nStudent selected is not available or isn't the last on the table you selected ");
                                    again = true;
                                    virtualView.askId(false,characterCard,acrobatIndex, gameSession.getPlayer(getActivePlayer()).getPersonalSchool());
                                }

                                if (acrobatIndex == max) {
                                    acrobatIndex = 0;
                                    card = false;
                                    cardPlayed=true;
                                    setActionState(savedActionState);
                                    action();
                                }
                            }
                            else {
                                for (Student student : gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getEntry()) {
                                    if (student.getIdStudent() == Choice.getId()) {
                                        present = true;
                                        studentId = Choice.getId();
                                        break;
                                    }
                                }
                                if (present) {
                                    again = false;
                                    acrobatIndex++;
                                    virtualView.askId(false, characterCard, acrobatIndex, gameSession.getPlayer(getActivePlayer()).getPersonalSchool());
                                } else {
                                    if (Choice.getId() == -2 && Choice.getNone()) {
                                        acrobatIndex = 0;
                                        cardPlayed=true;
                                        setActionState(savedActionState);
                                        action();
                                    }else{
                                        virtualView.showMessage("\nStudent selected is not available ");
                                        again = true;
                                        virtualView.askId(false, characterCard, acrobatIndex, gameSession.getPlayer(getActivePlayer()).getPersonalSchool());
                                    }
                                }
                            }
                        }
                        else if(characterCard.getCardEffect().equals(CardEffect.COURTESAN)){
                            /** Courtesan effect */
                            for (Student student : characterCard.getStudentsOnCard()) {
                                if (student.getIdStudent() == Choice.getId()) {
                                    present = true;
                                    studentId = Choice.getId();
                                    break;
                                }
                            }
                            if (present) {
                                again = false;
                                gameSession.playCharacterCard(characterCard.getCardEffect(),getActivePlayer(), studentId,-1,-1, null);
                                virtualView.showPersonalSchool(gameSession.getPlayer(getActivePlayer()).getPersonalSchool(), "Your ",gameSession.getPlayer(getActivePlayer()).getTrash(), gameSession.getDifficulty(), gameSession.getPlayer(getActivePlayer()).getCoinScore(), gameSession.getGameMode(), gameSession.getPlayer(getActivePlayer()).getTeamMate());
                                virtualView.showCoinAndCharacterCards(gameSession.getTable().getCoinsOnTable(),gameSession.getTable().getCharacterCardsOnTable());
                                card = false;
                                cardPlayed=true;
                                setActionState(savedActionState);
                                action();

                            }else {
                                virtualView.showMessage("\nStudent selected is not available ");
                                again = true;
                                virtualView.askId(false,characterCard,-1, null);
                            }
                        }
                    }
                }
                if(receivedMessage.getMessageType() == MessageType.COLOR_CHOSEN) {
                    ColorBlocked blockColor = (ColorBlocked) receivedMessage;
                    boolean exists=false;
                    SColor colorChosen = null;
                    /** checks if the color selected exists */
                    for (SColor color : SColor.values()) {
                        if (color.toString().equals(blockColor.getColor())) {
                            exists = true;
                            colorChosen = color;
                        }
                    }
                    /** Herbalist effect */
                    if(exists && characterCard.getCardEffect().equals(CardEffect.HERBALIST)){
                        again=false;
                        gameSession.playCharacterCard(characterCard.getCardEffect(), getActivePlayer(), -1,-1,-1, colorChosen);
                        virtualView.showCoinAndCharacterCards(gameSession.getTable().getCoinsOnTable(),gameSession.getTable().getCharacterCardsOnTable());
                        cardPlayed=true;
                        setActionState(savedActionState);
                        action();
                    }
                    /** Junk dealer effect */
                    else if(exists && characterCard.getCardEffect().equals(CardEffect.JUNKDEALER)){
                        again=false;
                        gameSession.playCharacterCard(CardEffect.JUNKDEALER, getActivePlayer(), -1,-1,-1, colorChosen);
                        virtualView.showCoinAndCharacterCards(gameSession.getTable().getCoinsOnTable(),gameSession.getTable().getCharacterCardsOnTable());
                        gameSession.getPlayer(getActivePlayer()).getPersonalSchool().winProf(gameSession.getListOfPlayers(), gameSession.getPlayer(getActivePlayer()), characterCard.getCardEffect());

                        /** show all school updated */
                        for(String s : allVirtualView.keySet()) {
                            for (Player p : gameSession.getListOfPlayers()) {
                                if (!p.getNickname().equals(s))
                                    allVirtualView.get(s).showPersonalSchool(p.getPersonalSchool(), p.getNickname() + "'s ", p.getTrash(), gameSession.getDifficulty(), p.getCoinScore(), gameSession.getGameMode(), p.getTeamMate());
                            }
                            for (Player p : gameSession.getListOfPlayers()) {
                                if (p.getNickname().equals(s)) {
                                    allVirtualView.get(s).showPersonalSchool(p.getPersonalSchool(), "Your ", p.getTrash(), gameSession.getDifficulty(), p.getCoinScore(), gameSession.getGameMode(), p.getTeamMate());
                                }
                            }
                        }

                        for (VirtualView vv : allVirtualView.values()) {
                            if (vv!=virtualView)
                                vv.showMessage(getActivePlayer()+" has played the JUNKDEALER Character Card for the color "+ colorChosen);
                        }
                        cardPlayed=true;
                        setActionState(savedActionState);
                        action();
                    }
                    else {
                        again = true;
                        virtualView.showMessage("\nColor selected doesn't exists!  ");
                        virtualView.askColorToBlock(characterCard.getCardEffect());
                    }
                }
                if(receivedMessage.getMessageType() == MessageType.STEP_MOTHER_EARTH_CHOSEN){
                    MotherEarthStepsChosen step = (MotherEarthStepsChosen) receivedMessage;

                    if (step.getString().equals("CHARACTER CARD")) {
                        if(gameSession.getDifficulty().equals(Difficulty.STANDARDMODE)) {
                            virtualView.showMessage("You are playing the standard mode, you can't use character cards! ");
                            virtualView.askMotherEarthSteps(gameSession.getPlayer(getActivePlayer()).getTrash().getStepMotherEarth(), gameSession.getTable(), gameSession.getDifficulty());
                        }
                        else if(characterCardAlreadyPlayed){
                            virtualView.showMessage("You can't activate 2 effects in a round! ");
                            virtualView.askMotherEarthSteps(gameSession.getPlayer(getActivePlayer()).getTrash().getStepMotherEarth(), gameSession.getTable(), gameSession.getDifficulty());
                        }
                        else {
                            if (!(getActionState().equals(ActionState.CHARACTER)))
                                savedActionState = getActionState();
                            setActionState(ActionState.CHARACTER);
                            action();
                        }
                    }

                    else{
                        if (step.getSteps() > step.getMaxSteps()) {
                            virtualView.showMessage("\nSteps selected more than maximum available ");
                            again = true;
                            if (characterCard != null && characterCard.getCardEffect().isBearerPlayed()) {
                                allVirtualView.get(getActivePlayer()).askMotherEarthSteps(gameSession.getPlayer(getActivePlayer()).getTrash().getStepMotherEarth() + 2, gameSession.getTable(), gameSession.getDifficulty());
                            } else
                                allVirtualView.get(getActivePlayer()).askMotherEarthSteps(gameSession.getPlayer(getActivePlayer()).getTrash().getStepMotherEarth(), gameSession.getTable(), gameSession.getDifficulty());
                        } else {
                            again = false;
                            int steps = step.getSteps();
                            gameSession.getTable().moveMotherEarth(steps);
                            if (gameSession.getDifficulty().equals(Difficulty.EXPERTMODE) && characterCard != null) {
                                if (!characterCard.getCardEffect().equals(CardEffect.HERALD))
                                    gameSession.getTable().getListOfIsland().get(gameSession.getTable().getPosMotherEarth() - 1).buildTowerOnIsland(gameSession.getListOfPlayers(), characterCard.getCardEffect(), gameSession.getPlayer(getActivePlayer()), gameSession.getGameMode(), gameSession.getTeams());
                                else
                                    gameSession.getTable().getListOfIsland().get(gameSession.getTable().getPosMotherEarth() - 1).buildTowerOnIsland(gameSession.getListOfPlayers(), CardEffect.STANDARDMODE, gameSession.getPlayer(getActivePlayer()), gameSession.getGameMode(), gameSession.getTeams());

                            }
                            else
                                gameSession.getTable().getListOfIsland().get(gameSession.getTable().getPosMotherEarth() - 1).buildTowerOnIsland(gameSession.getListOfPlayers(), CardEffect.STANDARDMODE, gameSession.getPlayer(getActivePlayer()), gameSession.getGameMode(), gameSession.getTeams());

                            /** Join Islands after the movement */
                            gameSession.getTable().joinIsland(gameSession.getTable().getListOfIsland());
                            virtualView.showListOfIsland(gameSession.getTable(),gameSession.getDifficulty());
                            virtualView.showPersonalSchool(gameSession.getPlayer(getActivePlayer()).getPersonalSchool(), "Your ",gameSession.getPlayer(getActivePlayer()).getTrash(), gameSession.getDifficulty(), gameSession.getPlayer(getActivePlayer()).getCoinScore(), gameSession.getGameMode(), gameSession.getPlayer(getActivePlayer()).getTeamMate());
                            setActionState(ActionState.CLOUDCARD);
                            action();
                        }
                    }
                }
                if(receivedMessage.getMessageType() == MessageType.CLOUD_CHOSEN){
                    CloudChosen Cloud = (CloudChosen) receivedMessage;

                    if (Cloud.getString().equals("CHARACTER CARD")) {
                        if(gameSession.getDifficulty().equals(Difficulty.STANDARDMODE)) {
                            virtualView.showMessage("You are playing the standard mode, you can't use character cards! ");
                            virtualView.askCloud(gameSession.getTable());
                        }
                        else if(characterCardAlreadyPlayed){
                            virtualView.showMessage("You can't activate 2 effects in a round! ");
                            virtualView.askCloud(gameSession.getTable());
                        }
                        else {
                            if (!(getActionState().equals(ActionState.CHARACTER)))
                                savedActionState = getActionState();
                            setActionState(ActionState.CHARACTER);
                            action();
                        }
                    }
                    else {
                        boolean present = false;
                        for (CloudCard cloud : gameSession.getTable().getCloudNumber()) {
                            if (Cloud.getId() == cloud.getIdCloud()) {
                                if (!cloud.getStudentOnCloud().isEmpty())
                                    present = true;
                            }
                        }
                        if (present) {
                            again = false;
                            gameSession.getPlayer(getActivePlayer()).getPersonalSchool().moveStudentFromCloudToEntry(gameSession.getTable().getCloudNumber().get(Cloud.getId() - 1));
                            turnFinished = true;
                        } else {
                            virtualView.showMessage("\nCloud is empty or doesn't exist ");
                            again = true;
                            virtualView.askCloud(gameSession.getTable());
                        }
                    }
                }
        /** end of round */
                if(!again && turnFinished) {
                    endTurn();
                }
                break;
            default:
                String message = "Error! ";
                for (VirtualView vv : allVirtualView.values()) {
                    vv.showMessage(message);
                }
        }

    }

    /**
     * If the player has finished to move the students from the entry, send him at the phase of movement of Mother Earth
     */
    private void allStudentsMoved() {
        if (movedStudents == gameSession.getTable().getCloudNumber().get(0).getNumberOfSpaces()) {
            movedStudents = 0;
            setActionState(ActionState.MOTHEREARTH);
        }
        action();
    }

    /**
     * Sets the new action state
     * @param actionState updated
     */
    public void setActionState(ActionState actionState){
        this.actionState = actionState;
    }

    /**
     * Returns the action state
     * @return current action state
     */
    public ActionState getActionState() {
        return actionState;
    }

    /**
     * Sets the new Game State
     * @param gameState updated
     */
    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    /**
     * Returns the Game object
     * @return Game object
     */
    public Game getGameSession() {
        return gameSession;
    }

    /**
     * Manage the planning phases of the match
     */
    public void planning(){
        if (roundIndex < maxPlayers) {
            showGame();
            allVirtualView.get(getActivePlayer()).askAssistantCardToPlay();
        }
        /** At the end of the planning phase of the last player, set up the new playing order for the action phase */
        if (roundIndex == maxPlayers) {
            if(gameSession.gameIsFinished(getActivePlayer()))
                endGame(null);
            else{
                roundIndex = 0;
                turnController.changeOrder();
                turnController.setPlayingPlayer(turnController.getNewPlayerOrderByName().get(0));
                this.setGameState(GameState.ACTION);
                if (gameSession.getDifficulty().equals(Difficulty.EXPERTMODE))
                    this.setActionState(ActionState.STUDENT);
                else
                    setActionState(ActionState.STUDENT);
                showGame();
                action();
            }
        }
    }

    /**
     * Manage the action phases of the match
     */
    public void action(){
        if (roundIndex < maxPlayers) {
            switch(actionState) {
                case STUDENT:
                    allVirtualView.get(getActivePlayer()).askPlaceAndStudentForMove(gameSession.getPlayer(getActivePlayer()).getPersonalSchool().getEntry());
                    break;
                case CHARACTER:
                    allVirtualView.get(getActivePlayer()).askCharacterCardToPlay(false, gameSession.getPlayer(getActivePlayer()).getCoinScore(), gameSession.getTable().getCharacterCardsOnTable());
                    break;
                case MOTHEREARTH:
                    if(characterCard!=null && characterCard.getCardEffect().isBearerPlayed())
                        allVirtualView.get(getActivePlayer()).askMotherEarthSteps(gameSession.getPlayer(getActivePlayer()).getTrash().getStepMotherEarth() + 2, gameSession.getTable(), gameSession.getDifficulty());
                    else
                        allVirtualView.get(getActivePlayer()).askMotherEarthSteps(gameSession.getPlayer(getActivePlayer()).getTrash().getStepMotherEarth(), gameSession.getTable(), gameSession.getDifficulty());
                    break;
                case CLOUDCARD:
                    if (lastRound){
                        endTurn();
                    }else {
                        if (gameSession.gameIsFinished(getActivePlayer())) {
                            endGame(null);
                        } else
                            allVirtualView.get(getActivePlayer()).askCloud(gameSession.getTable());
                    }
                    break;
                default:
                    break;
            }
        }
        /** set up the new round */
        else if (roundIndex == maxPlayers) {
            turnController.setPlayingPlayer(turnController.getNewPlayerOrderByName().get(0));
            roundIndex = 0;
            round++;
            setGameState(GameState.PLANNING);
            if(!lastRound) {
                if (gameSession.getTable().getBag().size() >= ((gameSession.getTable().getCloudNumber().size()) * (gameSession.getTable().getCloudNumber().get(0).getNumberOfSpaces()))) {
                    gameSession.getTable().extractStudentOnCloud();
                    planning();
                } else if (gameSession.getTable().getBag().size() < ((gameSession.getTable().getCloudNumber().size()) * (gameSession.getTable().getCloudNumber().get(0).getNumberOfSpaces())) && gameSession.getTable().getBag().size() > 0) {
                    planning();
                    lastRound = true;
                } else if (gameSession.getTable().getBag().isEmpty()) {
                    endGame(null);
                }
            }else
                endGame(null);
        }
    }

    /**
     * Prints the current situation of the match
     */
    private void showGame(){
        for(String s : allVirtualView.keySet()){
            allVirtualView.get(s).showTable(gameSession.getTable(), gameSession.getDifficulty());
            for(Player p : gameSession.getListOfPlayers()) {
                if (!p.getNickname().equals(s))
                    allVirtualView.get(s).showPersonalSchool(p.getPersonalSchool(), p.getNickname()+"'s ", p.getTrash(), gameSession.getDifficulty(), p.getCoinScore(), gameSession.getGameMode(), p.getTeamMate());
            }
            for(Player p : gameSession.getListOfPlayers()){
                if (p.getNickname().equals(s)) {
                    allVirtualView.get(s).showPersonalSchool(p.getPersonalSchool(), "Your ", p.getTrash(), gameSession.getDifficulty(), p.getCoinScore(), gameSession.getGameMode(), p.getTeamMate());
                    allVirtualView.get(s).showDeckAssistant(p.getDeckOfPlayer());
                }
            }
            if (s.equals(getActivePlayer()))
                allVirtualView.get(s).showMessage("\nRound "+round+" |\nYour Turn");
            else
                allVirtualView.get(s).showMessage("\nRound "+round+" |\nTurn of " + getActivePlayer());
        }
    }

    /**
     *  Manage the end of turn of a player (action phase)
     */
    private void endTurn(){
        turnController.nextPlayer(turnController.getNewPlayerOrderByName());
        roundIndex++;
        if (gameSession.getDifficulty().equals(Difficulty.EXPERTMODE)) {
            if(characterCard!= null) {
                for (CharacterCard c : gameSession.getTable().getCharacterCardsOnTable())
                    c.getCardEffect().setAllFalse();
                gameSession.getPlayer(getActivePlayer()).getPersonalSchool().winProf(gameSession.getListOfPlayers(), gameSession.getPlayer(getActivePlayer()), characterCard.getCardEffect()); //rimette a false
            }
            characterCardAlreadyPlayed = false;
        }

        setActionState(ActionState.STUDENT);
        showGame();
        action();
    }

    /**
     * Shows the results of the match and disconnects the players from the game
     * @param disconnectedNickname nick of the player who has disconnected from the match (null if no one disconnects during the match)
     */
    public void endGame(String disconnectedNickname){
        broadcastMessage("The Match is finished. ");
        if(gameSession.getTable().playerIsWinning(gameSession) == null) {
            if (disconnectedNickname != null) {
                broadcastMessage(disconnectedNickname + " has disconnceted!");
            }
            broadcastMessage("**** TIE ****");
        }
        else {
            if(gameSession.getGameMode()==GameMode.COOP){
                for (Team team : gameSession.getTeams()) {
                    if (team.getTeamColor() == gameSession.getTable().playerIsWinning(gameSession).getTColor()) {
                        for (Player player : team.getTeam()) {
                            if (disconnectedNickname != null){
                                allVirtualView.get(player.getNickname()).showMessage(disconnectedNickname + " has disconnceted!");
                            }
                            allVirtualView.get(player.getNickname()).showWinMessage();
                        }
                    } else {
                        for (Player player : team.getTeam()) {
                            if (disconnectedNickname != null){
                                allVirtualView.get(player.getNickname()).showMessage(disconnectedNickname + " has disconnceted!");
                            }
                            allVirtualView.get(player.getNickname()).showLoseMessage("Team " + gameSession.getTable().playerIsWinning(gameSession).getTColor().toString());
                        }
                    }
                }
            }
            else {
                for (String nickname : allVirtualView.keySet()) {
                    if (nickname.equals(gameSession.getTable().playerIsWinning(gameSession).getNickname())) {
                        if (disconnectedNickname != null){
                            allVirtualView.get(nickname).showMessage(disconnectedNickname + " has disconnceted!");
                        }
                        allVirtualView.get(nickname).showWinMessage();
                    }
                    else {
                        if (disconnectedNickname != null){
                            allVirtualView.get(nickname).showMessage(disconnectedNickname + " has disconnceted!");
                        }
                        allVirtualView.get(nickname).showLoseMessage(gameSession.getTable().playerIsWinning(gameSession).getNickname());
                    }
                }
            }
        }
        disconnect();
    }


    /**
     * METHODS USED FOR CLIENT-SERVER CONNECTION
     */

    /**
     * Disconnects all the players from the match
     */
    public void disconnect(){
        for(VirtualView vv : allVirtualView.values()) {
            allVirtualView.remove(vv);
        }
    }

    /**
     * @return true if the game is started
     */
    public boolean isGameStarted(){
        return (gameState != GameState.INIT && gameState!= GameState.END_GAME);
    }

    /**
     * @return the player (nickname) that's moving
     */
    public String getActivePlayer(){
        return turnController.getActivePlayer();
    }

    /**
     * @return allVirtualView
     */
    public HashMap<String, VirtualView> getAllVirtualView() {
        return allVirtualView;
    }

    /**
     * Sends a message to all the players of the match
     */
    public void broadcastMessage(String message) {
        for (VirtualView vv : allVirtualView.values()) {
            vv.showMessage(message);
        }
    }
}