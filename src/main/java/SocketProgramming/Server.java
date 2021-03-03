package SocketProgramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket ss;
    private int numPlayers;
    private ServerSideConnection player1;
    private ServerSideConnection player2;

    public Server() {
        System.out.println("*******  G A M E  S E R V E R  *******");
        numPlayers = 0;

        try {
            ss = new ServerSocket(3000);
        } catch (IOException ex) {
            System.out.println("Error in Server constructor");
        }
    }

    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections");
            while(numPlayers < 2) {
                Socket s = ss.accept();
                numPlayers++;
                System.out.println("Player has joined");
                ServerSideConnection ssc = new ServerSideConnection(s, numPlayers);
                if(numPlayers == 1) {
                    player1 = ssc;
                } else {
                    player2 = ssc;
                }
                Thread t = new Thread(ssc);
                t.start();
            }
            player1.getPlayer();
            player2.sendPlayerInfo(player1.getPlayerName(), player1.getPlayerColor());
            System.out.println("Game lobby full. There are two players in game");

            while (true) {

            }

        } catch (IOException e) {
            System.out.println("Error in acceptConnections method");
            e.printStackTrace();
        }
        // successfully
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.acceptConnections();
    }


}
