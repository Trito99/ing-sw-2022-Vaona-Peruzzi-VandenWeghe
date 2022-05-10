package it.polimi.ingsw.view;

import it.polimi.ingsw.model.assistant.AssistantCard;
import it.polimi.ingsw.model.assistant.DeckAssistant;
import it.polimi.ingsw.model.character.CharacterCard;
import it.polimi.ingsw.model.game.Difficulty;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.island.IslandCard;
import it.polimi.ingsw.model.player.PlayerNumber;
import it.polimi.ingsw.model.school.School;
import it.polimi.ingsw.model.school.TColor;
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
    private static final String WRONG_INPUT = "Input error. Type again.";

    public Cli(){
        output = out;
        commandList=new ArrayList<String>();
        /**for(Command command: Command.values()) {
            commandList.add(command.getVal());
        }*/
    }

    public void start(){
        out.println("Welcome to Eriantys!! :) ");
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
    public void showPersonalSchool(School school) {

    }

    @Override
    public void showTable(Table table) {
        out.print("\nId  | MotherEarth |   Tower   | Students");
        for(IslandCard islandCard : table.getListOfIsland()) {
            if (islandCard.towerIsOnIsland()) {
                out.print("\n" + islandCard.getIdIsland() + "   | " + islandCard.getMotherEarthOnIsland() + "       | " + islandCard.getTowerOnIsland() + " ");
                for (Student student: islandCard.getStudentOnIsland())
                    out.print(student.getsColour()+" ");
            } else {
                out.print("\n" + islandCard.getIdIsland() + "   | " + islandCard.getMotherEarthOnIsland() + "       | " + " No Tower |" + " ");
                for (Student student: islandCard.getStudentOnIsland())
                    out.print(student.getsColour()+" ");
            }
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
                out.print("Wrong input.");
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
    public void askAssistantCardToPlay(ArrayList<AssistantCard> assistantDeck) {

    }

    @Override
    public void askStudentsToMove(ArrayList<Student> studentsInHall, School school, Table table) {

    }

    @Override
    public void askCharacterCardToPlay(ArrayList<CharacterCard> characterCard) {

    }

    @Override
    public void askMotherEarthSteps(Table table) {

    }

    @Override
    public void askChooseCloud(Table table) {

    }
}
