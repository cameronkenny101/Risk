package SocketProgramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerSideConnection implements Runnable {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private int playerID;

    public ServerSideConnection(Socket s, int id) {
        socket = s;
        playerID = id;
        try {
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error in SSC method");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            dataOut.writeInt(playerID);
            dataOut.flush();
        } catch (IOException e) {
            System.out.println("Error in run method in SSC");
            e.printStackTrace();
        }
    }
}
