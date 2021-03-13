package Online;

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
            System.out.println("Game lobby full. There are two players in game");
            getPlayers();
            sendPlayerInfo();
            getAndSendMap();
            waitForMove(player1, player2);
            waitForMove(player2, player1);
            waitForMove(player1, player2);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error in acceptConnections method");
            e.printStackTrace();
        }
    }

    public void getPlayers() throws IOException {
        player1.getPlayer();
        player2.getPlayer();
    }

    public void sendPlayerInfo() throws IOException {
        player2.sendPlayerInfo(player1.getPlayerName(), player1.getPlayerColor());
        player1.sendPlayerInfo(player2.getPlayerName(), player2.getPlayerColor());
    }

    public void getAndSendMap() throws IOException, ClassNotFoundException {
        player1.getArray();
        player2.sendArray(player1.getCountries());
    }

    public void waitForMove(ServerSideConnection player, ServerSideConnection nextPlayer) throws IOException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nextPlayer.sendBoolean(player.getBoolean());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.acceptConnections();
    }


}
