package Game;

public class Player {
    private String name;
    private Constants.PLAYER_COLOUR colour;

    public Player(String name, Constants.PLAYER_COLOUR colour) {
        setName(name);
        this.colour = colour;
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
}
