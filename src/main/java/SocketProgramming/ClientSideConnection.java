package SocketProgramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSideConnection {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private int playerID;

    public ClientSideConnection() {
        System.out.println("****  C L I E N T   ****");

        try {
            socket = new Socket("localhost", 3000);
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());
            playerID = dataIn.readInt();
            System.out.println("Connected in server as Player " + playerID);
        } catch (IOException ex) {
            System.out.println("Error in client side connection constructor");
        }
    }

    public void receiveGameStatus() {
        System.out.println("Receiving game");
    }
}
