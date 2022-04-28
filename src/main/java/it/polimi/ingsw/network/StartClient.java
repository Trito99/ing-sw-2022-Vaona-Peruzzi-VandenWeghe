package it.polimi.ingsw.network;


import it.polimi.ingsw.view.Cli;
//import javafx.application.Application;

import java.util.Locale;
import java.util.Scanner;

public class StartClient {

    /** Fa partire il client e chiede all'utente se vuole giocare con CLI o GUI */

    public static void main(String[] args){
        System.out.println("Benvenuto! Digita \"CLI\" per giocare da linea di comando, o \"GUI\" per giocare " +
                "in modalità con interfaccia grafica.");
        Scanner input = new Scanner(System.in);
        String mode;
        do{
            mode = input.nextLine().toUpperCase(Locale.ROOT);
            if(!(mode.equals("CLI"))&&!(mode.equals("GUI"))){
                System.out.println("Modalità non riconosciuta. Per favore inserire \"CLI\" o \"GUI\".");
            }
            else{
                if(mode.equals("CLI")){
                    Cli cli = new Cli();
                    ClientMessanger clientMessenger= new ClientMessanger(cli);
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
