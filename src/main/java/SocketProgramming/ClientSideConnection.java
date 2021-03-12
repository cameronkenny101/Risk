package SocketProgramming;

import Game.Constants;

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

    public void writePlayerInfo(String name, Constants.PLAYER_COLOUR colour) {
        System.out.println("Receiving game");
        try {
            dataOut.writeUTF(name);
            dataOut.writeUTF(colour.toString());
            dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] receivePlayerInfo() {
        String[] playerInfo = new String[2];
        try {
            playerInfo[0] = dataIn.readUTF();
            playerInfo[1] = dataIn.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerInfo;
    }

    public int getPlayerID() {
        return playerID;
    }
}
