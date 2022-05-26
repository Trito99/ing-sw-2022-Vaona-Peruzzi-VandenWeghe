package it.polimi.ingsw.view;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CardEffect;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.observer.ObservableView;

import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.zip.DataFormatException;

import static java.lang.System.out;

public class CLI extends ObservableView implements View {
    private PrintStream output;
    private Thread inputThread;
    private List<String> commandList;
    private static final String WRONG_INPUT = "⚠️Wrong input, Type again  ⚠️";

    public static final  String ANSI_RESET = "\u001B[0m";
    public static final  String ANSI_GREEN = "\u001B[32m";
    public static final  String ANSI_YELLOW = "\u001B[33m";
    public static final  String ANSI_RED = "\u001B[31m";
    public static final  String ANSI_PINK = "\u001B[35m";
    public static final  String ANSI_BLUE = "\u001B[34m";
    public static final  String ANSI_GREY = "\u001B[37m";
    public static final  String ANSI_BLACK = "\u001B[30m";

    public CLI(){
        output = out;
        commandList=new ArrayList<String>();
        /**for(Command command: Command.values()) {
            commandList.add(command.getVal());
        }*/
    }

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
        out.println(ANSI_RESET + "😛  Welcome to Eriantys!! 😛 ");
        askConnect();
    }

    /** legge stringhe da input */
    public String readInput() throws ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new ReadFromInput());
        inputThread = new Thread(futureTask);
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

    @Override
    public void askConnect() {
        boolean succeded;
        do {
            try{
                succeded = false;
                out.print("Insert a valid ip address (127.0.0.1) : ");
                String ipAddress = readInput();
                out.print("Insert a valid port (4000) : ");
                int port = Integer.parseInt(readInput());
                notifyObserver(obs -> obs.updateConnect(ipAddress, port));
            }
            catch (Exception e){
                out.println("⚠️Wrong input. ⚠️");
                succeded = true;
            }
        }while(succeded);
    }

    @Override
    public void askLobby(){
        boolean ye;
        do {
            try {
                ye = false;
                out.print("Enter your nickname: ");
                String nickname = readInput();
                out.print("Enter your Birth Day (gg): ");
                int birthDay= Integer.parseInt(readInput());
                out.print("Enter your Birth Month (mm): ");
                int birthMonth= Integer.parseInt(readInput());
                out.print("Enter your Birth Year (aaaa): ");
                int birthYear= Integer.parseInt(readInput());
                out.print("Enter the gameID: ");
                String gameID = readInput();

                GregorianCalendar playerDate = new GregorianCalendar();
                playerDate.setLenient(false);
                playerDate.set(birthYear,birthMonth -1,birthDay,0,0,0);
                playerDate.getTime();

                notifyObserver(obs -> obs.updateLobby(nickname, playerDate, gameID));
            } catch (IllegalArgumentException exception){
                ye = true;
                out.print("Error in selecting the date of birth! Try again.\n");
            } catch (Exception exception){
                ye = true;
                out.println(WRONG_INPUT);
            }

        }while (ye);
    }



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


    @Override
    public void showLogin(String nickname, String gameId, GregorianCalendar playerDate, boolean wasJoined) {
        if (wasJoined){
            notifyObserver(obs -> obs.createNickname(nickname));
            notifyObserver(obs -> obs.createPlayerDate(playerDate));
            out.println("\nYou joined the game "+gameId+ " as "+ nickname);
        }
        else {
            out.println("\nGame "+gameId+ " not available.");
            askLobby();
        }
    }

    @Override
    public void showMessage(String message) {
        out.println("\n" + message);
    }

    @Override
    public void showPlayerInfluence(int influence) {

    }

    @Override
    public void showPlayerTurn(String activePlayer) {

    }

    @Override
    public void showPlayerList(ArrayList<String> playerOrder) {

    }

    @Override
    public void showWinMessage() {
        out.print("\n****YOU WIN****");
        notifyObserver(obs -> obs.endGame());
    }

    @Override
    public void showLoseMessage(String nickname) {
        out.print("\n****YOU LOSE****");
        out.print("\n"+nickname+" WINS");
    }


    @Override
    public void showPersonalSchool(School school, String nickname, AssistantCard trash, Difficulty difficulty,int coins) {

        out.print("\n****School of "+ nickname + "**** ");
        printEntry(school.getEntry());
        printHall(school);
        printTowers(school.getTower());
        printTrash(trash);
        printCoinScore(difficulty, coins);
        out.println("\n----------------------------------------------");
    }

    @Override
    public void showTable(Table table, Difficulty difficulty) {
        boolean forbidden = false;

        if (difficulty.equals(Difficulty.EXPERTMODE)) {
            for (CharacterCard card : table.getCharacterCardsOnTable()) {
                if (card.getCardEffect().equals(CardEffect.CURATOR))
                    forbidden = true;
            }
        }

        out.print("\n**** TABLE ****");
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
        out.println("\n----------------------------------------------");

        printClouds(table.getCloudNumber());

        if(difficulty.equals(Difficulty.EXPERTMODE)){
            out.println("\n----------------------------------------------");
            printCharacterCards(table);
        }
        out.println("\n----------------------------------------------");
    }

    @Override
    public void showDeckAssistant(DeckAssistant deckAssistant) {
        out.print("\n****Your Cards in hand **** ");
        for(AssistantCard card : deckAssistant.getCardsInHand() ){
            out.print("\n"+card.getAssistantName() + " -> Turn Value: " +card.getTurnValue()+ " MotherEarth Steps: "+card.getStepMotherEarth()+" | ");
        }
    }

    @Override
    public void showWinMessage(int numberOfTower) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void askAction() {

    }



    @Override
    public void askAssistantCardToPlay() {
        boolean ye;
        do {
            try {
                ye=false;
                out.print("\nChoose an Assistant Card from your Deck (name): ");
                String nickname = readInput();
                notifyObserver(obs -> obs.chooseAssistantCard(nickname));
            } catch (Exception e) {
                ye = true;
                out.println(WRONG_INPUT);
            }
        } while (ye);
    }

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

    public void askColorToBlock(CardEffect color){
        boolean ye;
        do{
            try{
                ye = false;
                if(color.equals(CardEffect.HERBALIST)) {
                    out.print("\nWhich color do you want to block? ");

                }
                else if(color.equals(CardEffect.JUNKDEALER)){
                    out.print("\nDo you want to remove the students of which color?");
                }
                String colorChosen = readInput().toUpperCase(Locale.ROOT);
                notifyObserver(obs -> obs.chooseColorToBlock(colorChosen));

            }
            catch (Exception e){
                ye = true;

            }

        }while (ye);
    }

    @Override
    public void askMotherEarthSteps(int maxSteps) {
        boolean ye;
        do {
            try {
                ye=false;
                out.print("\nChoose how many steps you want to make MotherEarth move: (max " + maxSteps + ")\n");
                int steps = Integer.parseInt(readInput());
                notifyObserver(obs -> obs.chooseMotherEarthSteps(steps,maxSteps));
            } catch (Exception e) {
                out.println(WRONG_INPUT);
                ye=true;
            }
        }while(ye);

    }

    @Override
    public void askCloud(Table table) {
        int i=1;
        boolean ye;
        do {
            try {
                ye=false;
                out.print("\nChoose a Cloud Card (id) ");
                printClouds(table.getCloudNumber());
                out.print("\n");

                int id = Integer.parseInt(readInput());
                notifyObserver(obs -> obs.chooseCloudCard(id));
            } catch (Exception e) {
                ye=true;
                out.println(WRONG_INPUT);
            }
        }while (ye);
    }

    @Override
    public void askPlaceAndStudentForMove(ArrayList<Student> entry) {
        boolean ye;
        do {
            try {
                ye=false;
                out.print("\nWhich student of your entry do you want to move? (id)");
                printEntry(entry);
                out.print("\n");

                int id = Integer.parseInt(readInput());
                out.print("\nWhere do you want to move the student? (Island,School)\n");
                String place = readInput().toUpperCase(Locale.ROOT);
                notifyObserver(obs -> obs.choosePlaceAndStudentForMove(place, id));
            } catch (Exception e) {
                ye=true;
                out.println(WRONG_INPUT);
            }
        }while(ye);
    }
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
                            out.print("\nIn which island do you want to calculate influence? (id)\n");
                        if (characterCard.getCardEffect().equals(CardEffect.CURATOR))
                            out.print("\nIn which island do you want to place the forbidden card? (id)\n");
                    } else
                        out.print("\nIn which island do you want to move the student? (id)\n");
                }else {
                    if (characterCard.getCardEffect().equals(CardEffect.ACROBAT)) {
                        if(indexAcrobat %2==1) {
                            out.print("\nWhich student from the card do you want to switch? (id)\n");
                            printStudentsOnCard(characterCard);
                            out.println();
                        }else {
                            if (indexAcrobat <2)
                                out.print("\nWhich student from the entry do you want to switch? (id)\n");
                            else {
                                out.print("\nWhich student from the entry do you want to switch? (id or none)\n");
                                marker = -2;
                            }
                            printEntry(school.getEntry());
                            out.println();
                        }
                    }
                    else if (characterCard.getCardEffect().equals(CardEffect.BARD)) {
                        if (indexAcrobat % 2 == 1) {
                            out.print("\nWhich student from the school do you want to switch? (id)\n");
                            printHall(school);
                            out.println();
                        } else {
                            if (indexAcrobat < 2)
                                out.print("\nWhich student from the entry do you want to switch? (id)\n");
                            else {
                                out.print("\nWhich student from the entry do you want to switch? (id or none)\n");
                                marker = -2;
                            }

                            printEntry(school.getEntry());
                            out.println();
                        }
                    }
                    else if (characterCard.getCardEffect().equals(CardEffect.COURTESAN)){
                        out.print("\nWhich student do you want to move in your hall?\n");
                        printStudentsOnCard(characterCard);
                        out.println();
                    }
                    else {
                        out.print("\nWhich student do you want to move? (id)\n");
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

    private void printTowers(ArrayList<Tower> towers){
        out.print("\n\nTowers: ");
        for(Tower t : towers){
            out.print(getTowerAnsiColor(t) + "T" + ANSI_RESET + " | ");
        }
        out.print("(" + towers.size() + " towers remained)");
    }

    private void printEntry(ArrayList<Student> entry){
        out.print("\nEntry: ");
        for(Student s : entry){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | " );
        }
    }

    private void printTrash(AssistantCard trash){
        out.print("\n\nTrash Card: ");
        if (trash!=null)
            out.print(trash.getAssistantName() +" (TurnValue: "+ trash.getTurnValue() + ", StepME: " + trash.getStepMotherEarth()+")");
    }

    private void printCoinScore(Difficulty difficulty, int coins){
        if(difficulty.equals(Difficulty.EXPERTMODE)){
            out.print("\nCoins: "+coins);
        }
    }

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

    private void printStudentsOnCard(CharacterCard characterCard){
        out.print(characterCard.getCardEffect().toString() + ": ");
        for (Student s : characterCard.getStudentsOnCard()) {
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");
        }
    }

    private String getStudentAnsiColor(Student student) {
        switch (student.getsColour()) {
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

        private String getTowerAnsiColor(Tower tower) {
            switch (tower.getTColour()){
                case BLACK: return ANSI_BLACK;
                case GREY: return ANSI_GREY;
                default: return ANSI_RESET;
            }
    }
}
