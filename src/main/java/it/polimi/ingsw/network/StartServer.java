package it.polimi.ingsw.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {

    public static void main(String[] args) {
        int port;
        ServerSocket socket;

        String address;
        if (args.length < 2) {
            //System.out.println("Errore: Usa la porta di default 4000 e localhost di default.");
            System.out.println("Usa localhost di default e la porta di default 4000.");
            port = 4000;
            address = "localhost";
        }
        else {
            port = Integer.parseInt(args[0]);
            address = args[1];
        }

        try {
            socket = new ServerSocket(port,50, InetAddress.getByName(address));
            System.out.println(InetAddress.getByName(address));
        } catch (IOException exception) {
            System.out.println("impossibile aprire la server socket");
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
                System.out.println("connection dropped");
            }
        }

    }
}
