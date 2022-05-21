package it.polimi.ingsw;

import it.polimi.ingsw.network.StartClient;
import it.polimi.ingsw.network.StartServer;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static Selection choice = null;

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("type \"C\" to start the Client-app");
        System.out.println("type \"S\" to start the Server-app");
        System.out.print("--> ");
        String input = in.nextLine();

        if (input.equals("c")) {
            choice = Selection.CLIENT;
        }
        else if(input.equals("s")) {
            choice = Selection.SERVER;
        }

        if(choice == Selection.SERVER) StartServer.main(args);
        else if(choice == Selection.CLIENT) StartClient.main(args);
    }

    private enum Selection {
        SERVER,
        CLIENT
    }
}
