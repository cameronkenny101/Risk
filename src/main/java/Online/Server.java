package Online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket ss;
    protected int numPlayers;
    public ServerSideConnection player1;
    public ServerSideConnection player2;
    boolean player1Turn;
    boolean successfulAttack;

    public Server() {
        System.out.println("*******  G A M E  S E R V E R  *******");
        numPlayers = 0;

        try {
            ss = new ServerSocket(30000);
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
        } catch (IOException e) {
            System.out.println("Error in acceptConnections method");
            e.printStackTrace();
        }
    }

    public void playGame() {
        try {
            getPlayers();
            sendPlayerInfo();
            getAndSendMap();
            waitForMove(player1, player2);
            waitForMove(player2, player1);
            waitForMove(player1, player2);
            System.out.println("Waiting for dice winner");
            waitForDiceWinner();
            setPlayerTurn();
            initPhase();
            System.out.println("Init phase finished");
            waitForDiceWinner();
            setPlayerTurn();
            while (getAttackStatus()) {
                System.out.println("here1");
                if (sendAttackingData())
                    sendNumDefenders();
                sendAttackResults();
                if(successfulAttack) {
                    if (ifPlacingTroops()) {
                        sendPostAttackData();
                    }
                }
                System.out.println("here2");
            }
        } catch (IOException | ClassNotFoundException e) {
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
        nextPlayer.sendBoolean(player.getBoolean());
    }

    public void receiveDiceRoll(ServerSideConnection player, ServerSideConnection nextPlayer) throws IOException {
        nextPlayer.sendInt(player.getInt());
    }

    public void waitForDiceWinner() throws IOException {
        boolean noWinner = true;
        while(noWinner) {
            receiveDiceRoll(player1, player2);
            receiveDiceRoll(player2, player1);
            noWinner = player1.getBoolean();
        }
        System.out.println("Dice wait is over");
    }

    public void setPlayerTurn() throws IOException {
        if(player1.getBoolean()) {
            player1Turn = true;
            System.out.println("Player 1 won the dice roll");
        } else {
            player1Turn = false;
            System.out.println("Player 2 has won the dice roll");
        }
    }

    private void sendPostAttackData() throws IOException {
        if(player1Turn) {
            player2.sendInt(player1.getInt());
            player2.sendInt(player1.getInt());
        } else {
            player1.sendInt(player2.getInt());
            player1.sendInt(player2.getInt());
        }
    }

    public boolean sendAttackingData() throws IOException {
        boolean getData;
        if(player1Turn) {
            getData = player1.getBoolean();
            player2.sendBoolean(getData);
            player2.sendInt(player1.getInt());
            player2.sendInt(player1.getInt());
            player2.sendInt(player1.getInt());
        } else {
            getData = player2.getBoolean();
            player1.sendBoolean(getData);
            player1.sendInt(player2.getInt());
            player1.sendInt(player2.getInt());
            player1.sendInt(player2.getInt());
        }
        return getData;
    }

    protected boolean ifPlacingTroops() throws IOException {
        boolean isFortifying;
        if(player1Turn) {
            isFortifying = player1.getBoolean();
            player2.sendBoolean(isFortifying);
        } else {
            isFortifying = player2.getBoolean();
            player1.sendBoolean(isFortifying);
        }
        return isFortifying;
    }

    private boolean getAttackStatus() throws IOException {
        boolean isAttack;
        if(player1Turn) {
            isAttack = player1.getBoolean();
            player2.sendBoolean(isAttack);
        } else {
            isAttack = player2.getBoolean();
            player1.sendBoolean(isAttack);
        }
        return isAttack;
    }

    private void sendAttackResults() throws IOException {
        if(player1Turn) {
            player2.sendInt(player1.getInt());
            player2.sendInt(player1.getInt());
            player2.sendBoolean(player1.getBoolean());
            successfulAttack = player1.getBoolean();
            player2.sendBoolean(successfulAttack);
        } else {
            player1.sendInt(player2.getInt());
            player1.sendInt(player2.getInt());
            player1.sendBoolean(player2.getBoolean());
            successfulAttack = player2.getBoolean();
            player1.sendBoolean(successfulAttack);
        }
    }

    public void sendNumDefenders() throws IOException {
        if(player1Turn) {
            int troops = player2.getInt();
            System.out.println(troops);
            player1.sendInt(troops);
        } else {
            int troops = player1.getInt();
            System.out.println(troops);
            player2.sendInt(troops);
        }
    }

    public void initPhase() throws IOException {
        while (true) {
            boolean cont;
            if (player1Turn) {
                cont = player1.getBoolean();
                player2.sendBoolean(cont);
                player2.sendInt(player1.getInt());
                player2.sendInt(player1.getInt());
                player2.sendIntArray(player1.getIntArray());
            } else {
                cont = player2.getBoolean();
                player1.sendBoolean(cont);
                player1.sendInt(player2.getInt());
                player1.sendInt(player2.getInt());
                player1.sendIntArray(player2.getIntArray());
            }
            player1Turn = !player1Turn;
            if(!cont)
                break;
        }
    }

    public static class ServerSideConnection implements Runnable {

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
            int integer = dataIn.readInt();
            System.out.println(integer);
            return integer;
        }

        public void sendInt(int number) throws IOException {
            System.out.println("Sending: " + number);
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

    public static void main(String[] args) {
        Server server = new Server();
        server.acceptConnections();
        server.playGame();
    }


}
