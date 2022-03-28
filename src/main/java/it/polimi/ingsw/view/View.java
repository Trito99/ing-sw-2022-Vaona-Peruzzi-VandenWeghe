package it.polimi.ingsw.view;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class View extends Observable implements Observer, Runnable {   //extends Observable mi dà già i metodi notifyAllObserver e addObserver setChanged

    private Scanner scanner;                //Per View fatta come CLI
    private PrintStream outPutStream;       //Per View fatta come CLI

    public View(){
        scanner= new Scanner(System.in);
        outPutStream= new PrintStream(System.out);
    }

    @Override
    public void run() {                                 // Metodo dell

    }

    @Override
    public void update(Observable o, Object arg) {       // Metodo "dell'osservatore" perchè implemento observer

    }
}
