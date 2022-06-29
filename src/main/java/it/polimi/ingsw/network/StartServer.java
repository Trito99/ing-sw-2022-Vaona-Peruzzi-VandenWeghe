package it.polimi.ingsw.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Starts the Server
 */
public class StartServer {

    /**
     * Starts the server
     * @param args ip address and port number of the server
     */
    public static void main(String[] args) {
        int port;
        ServerSocket socket;

        String address;
        if (args.length < 2) {
            System.out.println("Use default localhost and port number 4000.");
            address = "localhost";
            port = 4000;
        }
        else {
            port = Integer.parseInt(args[0]);
            address = args[1];
        }

        try {
            socket = new ServerSocket(port,50, InetAddress.getByName(address));
            System.out.println(InetAddress.getByName(address));
        } catch (IOException exception) {
            System.out.println("Error! Unable to open server socket.");
            exception.printStackTrace();
            System.exit(1);
            return;
        }
        LobbyServer lobbyServer= new LobbyServer();
        while (true) {
            try {
                Socket client = socket.accept();
                ClientHandler clientHandler = new ClientHandler(client, lobbyServer);
                Thread thread = new Thread(clientHandler, "server_" + client.getInetAddress());
                thread.start();
            } catch (IOException e) {
                System.out.println("Connection dropped.");
            }
        }

    }
}
