package Online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerSideConnection implements Runnable {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private int playerID;
    private String playerName;
    private String playerColor;

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
            sendPlayerID();
        } catch (IOException e) {
            System.out.println("Error in run method in SSC");
            e.printStackTrace();
        }
    }

    private void sendPlayerID() throws IOException {
        dataOut.writeInt(playerID);
        dataOut.flush();
    }

    public void getPlayer() throws IOException {
        playerName = dataIn.readUTF();
        playerColor = dataIn.readUTF();
    }

    public void sendPlayerInfo(String playerName, String playerColor) throws IOException {
        dataOut.writeUTF(playerName);
        dataOut.writeUTF(playerColor);
        dataOut.flush();
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getPlayerName() {
        return playerName;
    }
}
