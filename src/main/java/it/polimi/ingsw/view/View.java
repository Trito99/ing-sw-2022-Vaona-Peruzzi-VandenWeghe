package it.polimi.ingsw.view;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class View {

    void askPlayersNumber();

    void askAction();

    void showMessage(String message);

    void askStudentToMoveToIsland();

    void askStudentToMoveToHall();

    void askAssistantCardToPlay();

    void askStepsOfMotherEarth();

    void askCloudCard();

    void showCloudCards();

    void showIslands();

    void showSchool();

    void showTable();

    void showErrorMsg(String message);

    void showWinMessage();

    void showLoseMessage();

    void showPlayerList();

    void showPlayerTurn(String activePlayer);
}

