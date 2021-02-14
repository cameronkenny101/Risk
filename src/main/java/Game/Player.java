package Game;

public class Player {
    private String name;
    private Constants.PLAYER_COLOUR colour;
    private int commanderID;
    private int diceRollNumber = 0;

    public Player(String name, Constants.PLAYER_COLOUR colour, int commanderID) {
        setName(name);
        setColour(colour);
        setCommanderID(commanderID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public Constants.PLAYER_COLOUR getColour() {
        return colour;
    }

    public void setColour(Constants.PLAYER_COLOUR colour) {
        this.colour = colour;
    }

    public int getCommanderID() {
        return commanderID;
    }

    public void setCommanderID(int commanderID) {
        this.commanderID = commanderID;
    }

    public int getDiceRollNumber() {
        return diceRollNumber;
    }

    public void setDiceRollNumber(int diceRollNumber) {
        this.diceRollNumber = diceRollNumber;
    }
}
