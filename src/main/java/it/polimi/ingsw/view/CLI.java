package it.polimi.ingsw.view;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.AssistantDeckName;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.network.LobbyForPrint;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.observer.ObserverView;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.System.out;

/**
 * Implements the CLI (command line interface)
 */
public class CLI extends ObservableView implements View {
    private final List<String> commandList;
    private static final String WRONG_INPUT = "Wrong input, Type again  ";

    public static final  String ANSI_RESET = "\u001B[0m";
    public static final  String ANSI_GREEN = "\u001B[32m";
    public static final  String ANSI_YELLOW = "\u001B[33m";
    public static final  String ANSI_RED = "\u001B[31m";
    public static final  String ANSI_PINK = "\u001B[35m";
    public static final  String ANSI_BLUE = "\u001B[34m";
    public static final  String ANSI_GREY = "\u001B[37m";
    public static final  String ANSI_BLACK = "\u001B[30m";

    /**
     * Cli constructor
     */
    public CLI(){
        commandList = new ArrayList<>();
    }

    /**
     * Starts the cli
     */
    public void start(){
        out.println(ANSI_BLUE + "\n" +
                "    __      ______          _                   _                      __   \n" +
                "   / /     |  ____|        (_)                 | |                     \\ \\  \n" +
                "  | |      | |__     _ __   _    __ _   _ __   | |_   _   _   ___       | | \n" +
                " / /       |  __|   | '__| | |  / _` | | '_ \\  | __| | | | | / __|       \\ \\\n" +
                " \\ \\       | |____  | |    | | | (_| | | | | | | |_  | |_| | \\__ \\       / /\n" +
                "  | |      |______| |_|    |_|  \\__,_| |_| |_|  \\__|  \\__, | |___/      | | \n" +
                "   \\_\\                                                 __/ |           /_/  \n" +
                "                                                      |___/                 " +
                "\n");
        out.println(ANSI_RESET + "  Welcome to Eriantys!!  ");
        askConnect();
    }

    /**
     * Asks the ip address and the server port for the connection to the server
     */
    @Override
    public void askConnect() {
        boolean succeeded;
        do {
            try{
                succeeded = false;
                out.print("Insert a valid ip address (127.0.0.1) : ");
                String ipAddress = readInput();
                out.print("Insert a valid port (4000) : ");
                int port = Integer.parseInt(readInput());
                notifyObserver(obs -> obs.updateConnect(ipAddress, port));
            }
            catch (Exception e){
                out.println(WRONG_INPUT);
                succeeded = true;
            }
        }while(succeeded);
    }

    /**
     * Reads lines from standard input
     * @return the string read from input
     * @throws ExecutionException when the thread is interrupted
     */
    public String readInput() throws ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new ReadFromInput());
        Thread inputThread = new Thread(futureTask);
        inputThread.start();

        String input = null;

        try {
            input = futureTask.get();
        } catch (InterruptedException e) {
            futureTask.cancel(true);
            Thread.currentThread().interrupt();
        }
        return input;
    }

    /**
     * Asks the players info and the game id that the player wants to join (or create)
     * @param lobbyMap map for print the information of the lobby
     */
    @Override
    public void askLobby(Map<String, LobbyForPrint> lobbyMap){
        boolean ye;
        String nickname = "";
        GregorianCalendar playerDate = new GregorianCalendar();
        do{
            try{
                ye = false;
                out.print("Enter your nickname: ");
                nickname = readInput();
            }catch(Exception exception){
                ye = true;
                out.println(WRONG_INPUT);
            }
        } while(ye);
        do {
            try {
                ye = false;
                out.print("Enter your Birth Day (gg): ");
                int birthDay= Integer.parseInt(readInput());
                out.print("Enter your Birth Month (mm): ");
                int birthMonth= Integer.parseInt(readInput());
                out.print("Enter your Birth Year (aaaa): ");
                int birthYear= Integer.parseInt(readInput());

                playerDate.setLenient(false);
                playerDate.set(birthYear,birthMonth -1,birthDay,0,0,0);
                playerDate.getTime();


                if(birthYear < 1900 || birthYear > 2021){
                    out.print("Error in selecting the date of birth! Try again. \n");
                    ye = true;
                }
            } catch (IllegalArgumentException exception){
                ye = true;
                out.print("Error in selecting the date of birth! Try again. \n");
            } catch (Exception exception){
                ye = true;
                out.println(WRONG_INPUT);
            }

        }while (ye);
        do{
            try{
                ye = false;
                if(lobbyMap != null){
                    out.print("\nLobby Id | Difficulty   | Game Mode  | Current Players\n");
                    for(String lobbyId : lobbyMap.keySet()){
                        if (lobbyMap.get(lobbyId).getDifficulty()!=null)
                        out.println("    " + lobbyId + "    | " + lobbyMap.get(lobbyId).getDifficulty()+ " | " + lobbyMap.get(lobbyId).getGameMode() + " | " + lobbyMap.get(lobbyId).getCurrentPlayers() + "/" + lobbyMap.get(lobbyId).getMaxPlayers());
                    }
                }
                out.print("\nEnter the gameID: ");
                String gameID = readInput();
                String finalNickname = nickname;
                notifyObserver(obs -> obs.updateLobby(finalNickname, playerDate, gameID));
            }catch(Exception exception){
                exception.printStackTrace();
                ye = true;
                out.println(WRONG_INPUT);
            }
        } while(ye);
    }


    /**
     * Asks at the player who creates the lobby the difficulty and the gameMode
     */
    @Override
    public void askPlayersNumberAndDifficulty() {
        int playersNumber;
        boolean ye;
        Difficulty difficulty = Difficulty.STANDARDMODE;
        String g;
        do {
            out.print("Enter the number of players who will join the room (2-4): ");
            try {
                playersNumber = Integer.parseInt(readInput());
            } catch (Exception e) {
                out.println(WRONG_INPUT);
                playersNumber=1;
            }

        } while(playersNumber > 4 || playersNumber <= 1);

        do {
            out.print("Enter the game difficulty that you want to play (Standard, Expert): ");
            try {
                g=readInput().toUpperCase(Locale.ROOT)+"MODE";
                difficulty = Difficulty.valueOf(g);
                ye=false;
            } catch (Exception e) {
                out.println(WRONG_INPUT);
                ye=true;
            }
        } while (ye);
        int finalPlayersNumber = playersNumber;
        Difficulty finalDifficulty = difficulty;
        notifyObserver(obs -> obs.choosePlayersNumberAndDifficulty(finalPlayersNumber,finalDifficulty));
    }

    /**
     * Asks the player to select deck and towers
     * @param towerColors array with the colors of the towers
     * @param assistantDeckNames array with the string's name of decks
     */
    @Override
    public void askTowerColorAndDeck(ArrayList<TColor> towerColors, ArrayList<AssistantDeckName> assistantDeckNames){
        TColor towerColorChosen = null;
        AssistantDeckName assistantDeckNameChosen = null;
        boolean ye;
        do {
            out.print("\nChoose a color for your towers:\n");
            for (TColor color : towerColors)
                out.print(getTowerAnsiColor(new Tower(color)) + color.toString() + ANSI_RESET + " | ");
            out.print("\n");

            try {
                towerColorChosen = TColor.valueOf(readInput().toUpperCase(Locale.ROOT));
                ye=false;
            } catch (Exception e) {
                out.println(WRONG_INPUT);
                ye=true;
            }

        } while(ye);
        do {
            out.print("\nChoose an Assistant Deck: \n");
            for(AssistantDeckName deckName : assistantDeckNames)
                out.print(deckName.toString()+" | ");
            out.print("\n");

            try {
                assistantDeckNameChosen= AssistantDeckName.valueOf(readInput().toUpperCase(Locale.ROOT));
                ye = false;
            } catch (Exception e) {
                out.println(WRONG_INPUT);
                ye = true;
            }

        } while(ye);
        TColor finalTowerColorChosen = towerColorChosen;
        AssistantDeckName finalAssistantDeckNameChosen = assistantDeckNameChosen;
        notifyObserver(obs -> obs.chooseTowerColorAndDeck(finalTowerColorChosen, finalAssistantDeckNameChosen));
    }


    /**
     * Prints if the login was successful
     * @param nickname of the player
     * @param gameId chosen by the player
     * @param playerDate of the player
     * @param gameNotFull false if the game selected is already full
     */
    @Override
    public void showLogin(String nickname, String gameId, GregorianCalendar playerDate, boolean gameNotFull) {
        if (gameNotFull){
            notifyObserver(obs -> obs.createNickname(nickname));
            notifyObserver(obs -> obs.createPlayerDate(playerDate));
            out.println("\nYou joined the game "+gameId+ " as "+ nickname);
        }
        else {
            out.println("\nGame "+ gameId + " is already full.");
            notifyObserver(ObserverView::askLobbyServerInfo);
        }
    }

    /**
     * Prints a generic message
     * @param message to be shown
     */
    @Override
    public void showMessage(String message) {
        out.println("\n" + message);
        if (message.equals("Something went wrong.") || message.equals("**** TIE ****")){
            notifyObserver(ObserverView::updateDisconnect);
        }
    }

    /**
     * Prints a waiting message
     * @param message to be shown
     */
    @Override
    public void showWaitingMessage(String message){              /** ???? Perchè non usiamo showMessage?*/
        out.println("\n" + message);
    }

    /**
     * Prints Winning message and disconnects the player
     */
    @Override
    public void showWinMessage() {
        out.print("\n**** YOU WIN ****");
        notifyObserver(ObserverView::updateDisconnect);
    }

    /**
     * Prints Losing message and disconnects the player
     * @param nickname of the player that won the match
     */
    @Override
    public void showLoseMessage(String nickname) {
        out.print("\n**** YOU LOSE ****");
        out.print("\n"+ nickname +" WINS!");
        notifyObserver(ObserverView::updateDisconnect);
    }

    /**
     * Prints the updated School of the player
     * @param school of the player
     * @param nickname of the player
     * @param trash card of the player
     * @param difficulty of the match
     * @param coins coinScore of the player
     * @param gameMode of the match
     * @param teamMate of the player (if coop mode)
     */
    @Override
    public void showPersonalSchool(School school, String nickname, AssistantCard trash, Difficulty difficulty,int coins, GameMode gameMode, String teamMate) {

        out.println("\n****"+ nickname +"School****");
        if(gameMode.equals(GameMode.COOP)){
            out.println("TeamMate: " + teamMate);
        }
        printEntry(school.getEntry());
        printHall(school);
        printTowers(school.getTowers());
        printTrash(trash);
        printCoinScore(difficulty, coins);
        out.println("\n----------------------------------------------");
    }

    /**
     * Prints the updated list of islands
     * @param table of the match
     * @param difficulty of the match
     */
    public void showListOfIsland(Table table, Difficulty difficulty){
        out.print("\n**** ISLANDS ****");
        printIslandCards(table, difficulty);
        out.print("\n----------------------------------------------");
    }

    /**
     * Prints the updated table of the match
     * @param table of the match
     * @param difficulty mode of the match
     */
    @Override
    public void showTable(Table table, Difficulty difficulty) {
        out.print("\n**** TABLE ****");
        printIslandCards(table, difficulty);
        out.println("\n----------------------------------------------");

        printClouds(table.getCloudNumber());

        if(difficulty.equals(Difficulty.EXPERTMODE)){
            out.println("\n----------------------------------------------");
            printCharacterCards(table);
        }
        out.println("\n----------------------------------------------");
    }

    /**
     * Prints the updated cards in hasnd of the player
     * @param deckAssistant of the player
     */
    @Override
    public void showDeckAssistant(DeckAssistant deckAssistant) {
        out.print("\n****Your Cards in hand **** ");
        for(AssistantCard card : deckAssistant.getCardsInHand() ){
            out.print("\n"+card.getAssistantName() + " -> Turn Value: " +card.getTurnValue()+ " MotherEarth Steps: "+card.getStepMotherEarth()+" | ");
        }
    }

    /**
     * Prints the player an error message
     * @param message to be shown
     */
    @Override
    public void showErrorMessage(String message) {
    }

    /**
     * Shows coins and character cards on table
     * @param coins on table
     * @param characterCardsOnTable on table
     */
    @Override
    public void showCoinAndCharacterCards(int coins, ArrayList<CharacterCard> characterCardsOnTable) {
    }

    /**
     * Asks the player which Assistant Card wants to play
     */
    @Override
    public void askAssistantCardToPlay() {
        boolean ye;
        do {
            try {
                ye=false;
                out.print("\nPLANNING PHASE\nChoose an Assistant Card from your Deck (name): ");
                String nickname = readInput();
                notifyObserver(obs -> obs.chooseAssistantCard(nickname));
            } catch (Exception e) {
                ye = true;
                out.println(WRONG_INPUT);
            }
        } while (ye);
    }

    /**
     * Asks at the player which Character Card he wants to play
     * @param choice false if the player hasn't already decided if he wants to activate a character card,
     *               true if the player wants to play a certain character card
     * @param coins of player
     * @param list of Character Cards on table
     */
    @Override
    public void askCharacterCardToPlay(boolean choice, int coins, ArrayList<CharacterCard> list) {
        boolean ye;
        do{
            try{
                ye = false;
                if(!choice) {
                    out.println();
                    for (CharacterCard cc : list) {
                        int cost = cc.getCostCharacter();
                        if (cc.getCoinOnCard())
                            cost = cost + 1;
                        out.print(cc.getCardEffect().toString() + " Cost: " + cost + " | ");
                    }
                    if(coins==1)
                        out.print("\nYou have " + coins + " coin.\nDo you want to play a Character card? (yes/no) ");
                    else
                        out.print("\nYou have " + coins + " coins.\nDo you want to play a Character card? (yes/no) ");
                }else{
                    out.print("\nWhich character card do you want to play: (name or none) \n");
                }
                String character = readInput().toUpperCase(Locale.ROOT);
                notifyObserver(obs -> obs.chooseCharacterCard(character,choice));
            } catch (Exception e){
                ye = true;
                out.println(WRONG_INPUT);
            }
        }while (ye);
    }

    /**
     * Handles the effect of the Herbalist Card and Junkdealer Card
     * Asks the player which color wants to select
     * @param color of the students to block in the influence calculation or to remove from the entry
     */
    public void askColorToBlock(CardEffect color){
        boolean ye;
        do{
            try{
                ye = false;
                if(color.equals(CardEffect.HERBALIST)) {
                    out.print("\nWhich color do you want to block? ");
                }
                else if(color.equals(CardEffect.JUNKDEALER)){
                    out.print("\nDo you want to remove the students of which color? ");
                }
                String colorChosen = readInput().toUpperCase(Locale.ROOT);
                notifyObserver(obs -> obs.chooseColorToBlock(colorChosen));
            }
            catch (Exception e){
                ye = true;
                out.println(WRONG_INPUT);
            }
        }while (ye);
    }

    /**
     * Asks the player how many steps wants to move Mother Earth
     * @param maxSteps max steps the player can choose
     * @param table of the match
     * @param difficulty of the match
     */
    @Override
    public void askMotherEarthSteps(int maxSteps, Table table, Difficulty difficulty) {
        boolean ye;
        do {
            try {
                ye = false;

                out.print("\nChoose how many steps do you want to move MotherEarth: (max " + maxSteps + ")\n");
                printIslandCards(table, difficulty);

                String stepsString = readInput().toUpperCase(Locale.ROOT);
                if(stepsString.equals("CHARACTER CARD"))
                    notifyObserver(obs -> obs.chooseMotherEarthSteps(-1, maxSteps, stepsString));
                else{
                    if (Integer.parseInt(stepsString)>0)
                        notifyObserver(obs -> obs.chooseMotherEarthSteps(Integer.parseInt(stepsString), maxSteps,""));
                    else
                        ye = true;
                }
            } catch (Exception e) {
                out.println(WRONG_INPUT);
                ye=true;
            }
        }while(ye);

    }

    /**
     * Asks the player which cloud wants to choose
     * @param table of the match
     */
    @Override
    public void askCloud(Table table) {
        boolean ye;
        do {
            try {
                ye = false;
                out.print("\nChoose a Cloud Card (id) ");
                printClouds(table.getCloudNumber());
                out.print("\n");

                String idString = readInput().toUpperCase(Locale.ROOT);
                if(idString.equals("CHARACTER CARD"))
                    notifyObserver(obs -> obs.chooseCloudCard(-1, idString));
                else
                    notifyObserver(obs -> obs.chooseCloudCard(Integer.parseInt(idString), ""));
            } catch (Exception e) {
                ye = true;
                out.println(WRONG_INPUT);
            }
        }while (ye);
    }

    /**
     * Asks the player which students he wants to move and where
     * @param entry of the players school
     */
    @Override
    public void askPlaceAndStudentForMove(ArrayList<Student> entry) {
        boolean ye;
        do {
            try {
                ye=false;
                out.print("\nACTION PHASE\nWhich student of your entry do you want to move? (id)");
                printEntry(entry);
                out.print("\n");
                String IDstring = readInput().toUpperCase(Locale.ROOT);
                    if(IDstring.equals("CHARACTER CARD"))
                        notifyObserver(obs -> obs.choosePlaceAndStudentForMove(IDstring, -1));
                   else{
                        out.print("\nWhere do you want to move the student? (Island,School)\n");
                        String place = readInput().toUpperCase(Locale.ROOT);
                        notifyObserver(obs -> obs.choosePlaceAndStudentForMove(place, Integer.parseInt(IDstring)));
                   }
            } catch (Exception e) {
                ye=true;
                out.println(WRONG_INPUT);
            }
        }while(ye);
    }

    /**
     * Asks the player which island he wants to select (or student on card for cards)
     * @param choice true for the selection of an island (that includes the effect of herald and curator) otherwise
     * @param characterCard played in the round
     * @param indexAcrobat index for the acrobat effect
     * @param school of current player
     */
    @Override
    public void askId(boolean choice, CharacterCard characterCard, int indexAcrobat, School school) {
        boolean ye;
        int marker = 0 ;
        do {
            try {
                ye=false;
                if (choice) {
                    if (characterCard != null) {
                        if (characterCard.getCardEffect().equals(CardEffect.HERALD))
                            out.print("\nHERALD EFFECT\nIn which island do you want to calculate influence? (id)\n");
                        if (characterCard.getCardEffect().equals(CardEffect.CURATOR))
                            out.print("\nCURATOR EFFECT\nIn which island do you want to place the forbidden card? (id)\n");
                    } else
                        out.print("\nIn which island do you want to move the student? (id)\n");
                }else {
                    if (characterCard.getCardEffect().equals(CardEffect.ACROBAT)) {
                        if(indexAcrobat %2==1) {
                            out.print("\nACROBAT EFFECT\nWhich student from the card do you want to switch? (id)\n");
                            printStudentsOnCard(characterCard);
                            out.println();
                        }else {
                            if (indexAcrobat <2)
                                out.print("\nACROBAT EFFECT\nWhich student from the entry do you want to switch? (id)\n");
                            else {
                                out.print("\nACROBAT EFFECT\nWhich student from the entry do you want to switch? (id or none)\n");
                                marker = -2;
                            }
                            printEntry(school.getEntry());
                            out.println();
                        }
                    }
                    else if (characterCard.getCardEffect().equals(CardEffect.BARD)) {
                        if (indexAcrobat % 2 == 1) {
                            out.print("\nBARD EFFECT\nWhich student from the hall do you want to switch? (Last id of the tables)\n");
                            printHall(school);
                            out.println();
                        } else {
                            if (indexAcrobat < 2)
                                out.print("\nBARD EFFECT\nWhich student from the entry do you want to switch? (id)\n");
                            else {
                                out.print("\nBARD EFFECT\nWhich student from the entry do you want to switch? (id or none)\n");
                                marker = -2;
                            }

                            printEntry(school.getEntry());
                            out.println();
                        }
                    }
                    else if (characterCard.getCardEffect().equals(CardEffect.COURTESAN)){
                        out.print("\nCOURTESAN EFFECT\nWhich student do you want to move in your hall?\n");
                        printStudentsOnCard(characterCard);
                        out.println();
                    }
                    else {
                        out.print("\nABBOT EFFECT\nWhich student do you want to move? (id)\n");
                        printStudentsOnCard(characterCard);
                        out.println();
                    }
                }
                String input = readInput().toUpperCase(Locale.ROOT);
                if(input.equals("NONE") && marker == -2) {
                    notifyObserver(obs -> obs.chooseId(-2, choice, indexAcrobat, true));
                }else {
                    int id = Integer.parseInt(input);
                    notifyObserver(obs -> obs.chooseId(id,choice, indexAcrobat, false));
                }
            } catch (Exception e) {
                ye=true;
                out.println(WRONG_INPUT);
            }
        }while (ye);
    }

    /**
     * Prints the schools hall
     * @param school of the player
     */
    private void printHall(School school) {
        out.print(ANSI_GREEN +"\n\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.GREEN)+ ANSI_GREEN + " Green Table: " + ANSI_RESET);
        for(Student s : school.getGTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }

        out.print(ANSI_RED +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.RED)+ ANSI_RED + " Red Table: " + ANSI_RESET);
        for(Student s : school.getRTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }

        out.print(ANSI_YELLOW +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.YELLOW)+ ANSI_YELLOW + " Yellow Table: " + ANSI_RESET);
        for(Student s : school.getYTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }

        out.print(ANSI_PINK +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.PINK)+ ANSI_PINK + " Pink Table: " + ANSI_RESET);
        for(Student s : school.getPTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }

        out.print(ANSI_BLUE +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.BLUE)+ ANSI_BLUE + " Blue Table: " + ANSI_RESET);
        for(Student s : school.getBTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }
    }

    /**
     * Prints the schools towerZone
     * @param towers of the school
     */
    private void printTowers(ArrayList<Tower> towers){
        out.print("\n\nTowers: ");
        for(Tower t : towers){
            out.print(getTowerAnsiColor(t) + "T" + ANSI_RESET + " | ");
        }
        out.print("(" + towers.size() + " towers remained)");
    }

    /**
     * Prints the school's entry
     * @param entry of the school
     */
    private void printEntry(ArrayList<Student> entry){
        out.print("\nEntry: ");
        for(Student s : entry){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | " );
        }
    }

    /**
     * Prints the trash card of the player
     * @param trash card of the player
     */
    private void printTrash(AssistantCard trash){
        out.print("\n\nTrash Card: ");
        if (trash!=null)
            out.print(trash.getAssistantName() +" (TurnValue: "+ trash.getTurnValue() + ", StepME: " + trash.getStepMotherEarth()+")");
    }

    /**
     * Prints the player's coinScore
     * @param difficulty mode of the match
     * @param coins of the player
     */
    private void printCoinScore(Difficulty difficulty, int coins){
        if(difficulty.equals(Difficulty.EXPERTMODE)){
            out.print("\nCoins: "+coins);
        }
    }

    /**
     * Prints the Cloud Cards on the table
     * @param cloudCards of match
     */
    private void printClouds(ArrayList<CloudCard> cloudCards){
        int i=1;

        out.print("\n**** CLOUDS ****");
        for(CloudCard c : cloudCards){
            out.print("\nCloud "+ i + ") ");
            if (c.getStudentOnCloud().size()>0) {
                out.print("Id Students: " );
                for (Student s : c.getStudentOnCloud()) {
                    out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
                }
            }else
                out.print("Empty");
            i++;
        }
    }

    /**
     * Prints the Character Cards on the table
     * @param table of the match
     */
    private void printCharacterCards(Table table) {
        out.print("\nCoins on table: " + table.getCoinsOnTable() + "\n");
        out.print("\n**** CHARACTER CARDS ****");
        for (CharacterCard characterCard : table.getCharacterCardsOnTable()) {
            int coins = 0, cost = characterCard.getCostCharacter();
            if (characterCard.getCoinOnCard()) {
                coins = 1;
                cost = cost + 1;
            }
            out.println();
            out.println(characterCard.getCardEffect().toString() + "(" + coins + ")" + " Cost: " + cost);
            switch (characterCard.getCardEffect()) {
                case COURTESAN:
                case ABBOT:
                case ACROBAT:
                    out.print("Students : ");
                    printStudentsOnCard(characterCard);
                    out.println();
                    break;
                case CURATOR:
                    out.println("Forbidden cards on curator: " + characterCard.getXCardOnCard());
                    break;
                default:
                    break;
            }
            out.println("Effect: " + characterCard.getDescription());
        }
    }

    /**
     * Prints the students on a specific Character Card
     * @param characterCard on table
     */
    private void printStudentsOnCard(CharacterCard characterCard){
        out.print(characterCard.getCardEffect().toString() + ": ");
        for (Student s : characterCard.getStudentsOnCard()) {
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }
    }

    /**
     * Prints the list of islands of the match
     * @param table of the match
     * @param difficulty mode of the match
     */
    private void printIslandCards(Table table, Difficulty difficulty){
        boolean forbidden = false;

        if (difficulty.equals(Difficulty.EXPERTMODE)) {
            for (CharacterCard card : table.getCharacterCardsOnTable()) {
                if (card.getCardEffect().equals(CardEffect.CURATOR)) {
                    forbidden = true;
                    break;
                }
            }
        }

        if(forbidden)
            out.print("\nIsland Id  | MotherEarth |   Tower (n)   | Forbidden | Students");
        else
            out.print("\nIsland Id  | MotherEarth |   Tower (n)   | Students");

        for(IslandCard islandCard : table.getListOfIsland()) {
            if (islandCard.towerIsOnIsland()) {
                if(forbidden)
                    out.print("\n" + islandCard.getIdIsland() + "          | " + islandCard.getMotherEarthOnIsland() + "       |    " + getTowerAnsiColor(islandCard.getTowerOnIsland()) + "T" +ANSI_RESET+ "(" + islandCard.getMergedIsland() + ")     | "+islandCard.isXCardOnIsland()+"("+islandCard.getXCardCounter()+")"+"  | ");
                else
                    out.print("\n" + islandCard.getIdIsland() + "          | " + islandCard.getMotherEarthOnIsland() + "       |    " + getTowerAnsiColor(islandCard.getTowerOnIsland()) + "T" +ANSI_RESET+ "(" + islandCard.getMergedIsland() + ")     | ");
                for (Student student: islandCard.getStudentOnIsland())
                    out.print(getStudentAnsiColor(student) + student.getIdStudent() + " " + ANSI_RESET);
            } else {
                if(forbidden)
                    out.print("\n" + islandCard.getIdIsland() + "          | " + islandCard.getMotherEarthOnIsland() + "       | " + " No Tower" +"(" + (islandCard.getMergedIsland()-1) + ") | "+islandCard.isXCardOnIsland()+"("+islandCard.getXCardCounter()+")"+"  | ");
                else
                    out.print("\n" + islandCard.getIdIsland() + "          | " + islandCard.getMotherEarthOnIsland() + "       | " + " No Tower" +"(" + (islandCard.getMergedIsland()-1) + ")" + "  | ");
                for (Student student: islandCard.getStudentOnIsland())
                    out.print(getStudentAnsiColor(student) + student.getIdStudent() + " " + ANSI_RESET);
            }
        }
        out.print("\n\n");
    }

    /**
     * Returns the correct string color
     * @param student to color
     * @return the Ansi color to use for the "Show methods"
     */
    private String getStudentAnsiColor(Student student) {
        switch (student.getSColor()) {
            case GREEN:
                return ANSI_GREEN;
            case YELLOW:
                return ANSI_YELLOW;
            case RED:
                return ANSI_RED;
            case PINK:
                return ANSI_PINK;
            case BLUE:
                return ANSI_BLUE;
            default:
                return ANSI_RESET;
        }
    }

    /**
     * Returns the correct string color
     * @param tower to color
     * @return the Ansi color to use for the "show methods"
     */
        private String getTowerAnsiColor(Tower tower) {
            switch (tower.getTColour()){
                case BLACK: return ANSI_BLACK;
                case GREY: return ANSI_GREY;
                default: return ANSI_RESET;
            }
    }
}
