package it.polimi.ingsw.network;


import it.polimi.ingsw.view.CLI;
//import javafx.application.Application;

import java.util.Locale;
import java.util.Scanner;

public class StartClient {

    /** Fa partire il client e chiede all'utente se vuole giocare con CLI o GUI */

    public static void main(String[] args){
        System.out.println("Hi! Digit \"CLI\" to play from the command line, or \"GUI\" to play " +
                "with the graphic interface mode.");
        Scanner input = new Scanner(System.in);
        String mode;
        do{
            mode = input.nextLine().toUpperCase(Locale.ROOT);
            if(!(mode.equals("CLI"))&&!(mode.equals("GUI"))){
                System.out.println("Mode not recognised. Please insert \"CLI\" o \"GUI\".");
            }
            else{
                if(mode.equals("CLI")){
                    CLI cli = new CLI();
                    ClientMessanger clientMessenger = new ClientMessanger(cli);
                    cli.addObserver(clientMessenger);
                    cli.start();
                }
                else{
                    /**Application.launch(MainApp.class);*/
                }
            }
        }
        while(!(mode.equals("CLI")) && !(mode.equals("GUI")));
    }
}
