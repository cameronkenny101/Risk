package SocketProgramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerSideConnection implements Runnable {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private int playerID;

    public ServerSideConnection(Socket s, int id) {
        socket = s;
        playerID = id;
    }

    @Override
    public void run() {

    }
}
