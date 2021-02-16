package Game;

import java.util.Random;

public class Dice {
    /**
     * used to simulate two die rolling
     * @return the result of two die rolling
     */
    public int rollDice() {
        Random random = new Random();
        return random.nextInt(11)+2;
    }

    /**
     *
     * @param roll1 the integer value of a player's dice roll
     * @param roll2 the integer value of another player's dice roll
     * @return the result of the comparison of who had the bigger number
     */
    public int bestRoll(int roll1, int roll2) {
        return Integer.compare(roll1, roll2);
    }


}
