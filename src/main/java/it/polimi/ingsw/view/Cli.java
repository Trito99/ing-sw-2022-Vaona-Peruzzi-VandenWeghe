package it.polimi.ingsw.view;

import it.polimi.ingsw.message.ChoosePlaceAndStudentForMove;
import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.cloud.CloudCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColor;
import it.polimi.ingsw.model.school.Tower;
import it.polimi.ingsw.model.student.SColor;
import it.polimi.ingsw.model.student.Student;
import it.polimi.ingsw.model.table.Table;
import it.polimi.ingsw.observer.ObservableView;

import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.System.out;

public class Cli extends ObservableView implements View {
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

    public Cli(){
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

    }

    @Override
    public void showLoseMessage() {

    }

    @Override
    public void showPlayer(String nickname, PlayerNumber playerNumber, TColor tColor, int influenceOnIsland, School personalSchool, DeckAssistant deckOfPlayer, AssistantCard trash, int coinscore, String player) {

    }

    @Override
    public void showPersonalSchool(School school, String nickname, AssistantCard trash) {

        out.print("\n****School of "+ nickname + "**** ");
        out.print("\nEntry: ");
       for(Student s : school.getEntry()){
           out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | " );
       }
       out.println(ANSI_GREEN +"\n\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.GREEN)+ ANSI_GREEN + " Green Table: " + ANSI_RESET);
       for(Student s : school.getGTable()){
           out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " ");
       }

        out.println(ANSI_RED +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.RED)+ ANSI_RED + " Red Table: " + ANSI_RESET);
       for(Student s : school.getRTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " ");
       }

        out.println(ANSI_YELLOW +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.YELLOW)+ ANSI_YELLOW + " Yellow Table: " + ANSI_RESET);
       for(Student s : school.getYTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " ");
       }

        out.println(ANSI_PINK +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.PINK)+ ANSI_PINK + " Pink Table: " + ANSI_RESET);
       for(Student s : school.getPTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " ");
       }

        out.println(ANSI_BLUE +"\nProf: "+ ANSI_RESET +school.getProfInHall(SColor.BLUE)+ ANSI_BLUE + " Blue Table: " + ANSI_RESET);
       for(Student s : school.getBTable()){
            out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " ");
       }

        out.print("\n\nTowers: ");
        for(Tower t : school.getTower()){
            out.print(getTowerAnsiColor(t) + "T" + ANSI_RESET + " | ");
        }

        out.print("\n\nTrash Card: ");
        if (trash!=null)
            out.print(trash.getAssistantName() +" (TurnValue: "+ trash.getTurnValue() + ", StepME: " + trash.getStepMotherEarth()+")");

       out.println("\n----------------------------------------------");
    }

    @Override
    public void showTable(Table table) {
        int i=1;

        out.print("\n**** TABLE ****");
        out.print("\nIsland Id  | MotherEarth |   Tower   | Students");
        for(IslandCard islandCard : table.getListOfIsland()) {
            if (islandCard.towerIsOnIsland()) {
                out.print("\n" + islandCard.getIdIsland() + "          | " + islandCard.getMotherEarthOnIsland() + "       |    " + getTowerAnsiColor(islandCard.getTowerOnIsland()) + "T" +ANSI_RESET+"    | ");
                for (Student student: islandCard.getStudentOnIsland())
                    out.print(getStudentAnsiColor(student) + student.getIdStudent() + " " + ANSI_RESET);
            } else {
                out.print("\n" + islandCard.getIdIsland() + "          | " + islandCard.getMotherEarthOnIsland() + "       | " + " No Tower | ");
                for (Student student: islandCard.getStudentOnIsland())
                    out.print(getStudentAnsiColor(student) + student.getIdStudent() + " " + ANSI_RESET);
            }
        }
        out.println("\n----------------------------------------------");
        out.print("\n**** CLOUDS ****");
        for(CloudCard c : table.getCloudNumber()){
            out.print("\nCloud "+ i + ") ");
            if (table.getCloudNumber().get(i-1).getStudentOnCloud().size()>0) {
                out.print("Id Students: " );
                for (Student s : table.getCloudNumber().get(i - 1).getStudentOnCloud()) {
                    out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | ");

                }
            }else
                out.print("Empty");
            i++;
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
    public void askLobby() {
        try {
            out.print("Enter your nickname: ");
            String nickname = readInput();
            out.print("Enter your Birth Day (gg): ");
            int birthDay= Integer.parseInt(readInput());
            out.print("Enter your Birth Month (mm): ");
            int birthMonth= Integer.parseInt(readInput());
            out.print("Enter your Birth Year (aaaa): ");
            int birthYear= Integer.parseInt(readInput());
            GregorianCalendar playerDate = new GregorianCalendar(birthYear, birthMonth, birthDay);

            out.print("Enter the gameID: ");
            String gameID = readInput();

            notifyObserver(obs -> obs.updateLobby(nickname, playerDate, gameID));
        } catch (ExecutionException e) {
            out.println(WRONG_INPUT);
        }
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
    public void askAssistantCardToPlay() {
        try {
            out.print("\nChoose an Assistant Card from your Deck (nickname): ");
            String nickname = readInput();
            notifyObserver(obs -> obs.chooseAssistantCard(nickname));
        } catch (ExecutionException e) {
            out.println(WRONG_INPUT);
        }
    }

    @Override
    public void askStudentsToMove(ArrayList<Student> studentsInHall, School school, Table table) {

    }

    @Override
    public void askCharacterCardToPlay(ArrayList<CharacterCard> characterCard) {

    }

    @Override
    public void askMotherEarthSteps(AssistantCard trash) {
        try{
            out.print("\nChoose how many steps you want to make MotherEarth move: (max "+trash.getStepMotherEarth()+")\n");
            int steps = Integer.parseInt(readInput());
            notifyObserver(obs -> obs.chooseMotherEarthSteps(steps));
        } catch (ExecutionException e) {
            out.println(WRONG_INPUT);
        }

    }

    @Override
    public void askCloud() {
        try {
            out.print("\nChoose a Cloud Card (id) ");
            int id = Integer.parseInt(readInput());
            notifyObserver(obs -> obs.chooseCloudCard(id));
        } catch (ExecutionException e) {
            out.println(WRONG_INPUT);
        }
    }

    @Override
    public void askPlaceAndStudentForMove(ArrayList<Student> entry) {
        try {
            out.print("\nWhich student of your entry do you want to move? (id)");
            out.print("\nEntry: ");
            for(Student s : entry){
                out.print(getStudentAnsiColor(s) + s.getIdStudent() + ANSI_RESET + " | " );
            }
            out.print("\n");
            int id = Integer.parseInt(readInput());
            out.print("\nWhere do you want to move the student? (Island,School)\n");
            String place = readInput().toUpperCase(Locale.ROOT);
            notifyObserver(obs -> obs.choosePlaceAndStudentForMove(place,id));
        } catch (ExecutionException e) {
            out.println(WRONG_INPUT);
        }
    }
    @Override
    public void askIdIsland() {
        try {
            out.print("\nIn which island do you want to move the student? (id)\n");
            int id = Integer.parseInt(readInput());
            notifyObserver(obs -> obs.chooseIdIsland(id));
        } catch (ExecutionException e) {
            out.println(WRONG_INPUT);
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
