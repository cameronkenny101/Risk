package Online;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSideConnection implements Runnable {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private int playerID;
    private String playerName;
    private String playerColor;
    private ArrayList<Integer> countries;

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

    public void getArray() throws IOException {
        countries = new ArrayList<>();
        int size = dataIn.readInt();
        for(int i = 0; i < size; i++)
            countries.add(dataIn.readInt());
    }

    public void sendArray(ArrayList<Integer> countries) throws IOException {
        dataOut.writeInt(countries.size());
        for (Integer country : countries) {
            dataOut.writeInt(country);
        }
        dataOut.flush();
    }

    public int[] getIntArray() throws IOException {
        int size = dataIn.readInt();
        int[] array = new int[size];
        for(int i = 0; i < size; i++)
            array[i] = dataIn.readInt();
        return array;
    }

    public void sendIntArray(int[] array) throws IOException {
        dataOut.writeInt(array.length);
        for (int i : array) {
            dataOut.writeInt(i);
            System.out.println(i);
        }
        dataOut.flush();
    }

    public boolean getBoolean() throws IOException {
        return dataIn.readBoolean();
    }

    public void sendBoolean(boolean nextMove) throws IOException {
        dataOut.writeBoolean(nextMove);
        dataOut.flush();
    }

    public int getInt() throws IOException {
        return dataIn.readInt();
    }

    public void sendInt(int number) throws IOException {
        dataOut.writeInt(number);
        dataOut.flush();
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Integer> getCountries() {
        return countries;
    }
}