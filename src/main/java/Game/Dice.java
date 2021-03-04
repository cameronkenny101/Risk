package Game;

import java.util.Random;

public class Dice {
    /**
     * used to simulate two die rolling
     *
     * @return the result of two die rolling
     */
    public static int rollDice() {
        Random random = new Random();
        return random.nextInt(11) + 2;
    }

    /**
     * @param roll1 the integer value of a player's dice roll
     * @param roll2 the integer value of another player's dice roll
     * @return the result of the comparison of who had the bigger number
     */
    public static int bestRoll(int roll1, int roll2) {
        return Integer.compare(roll1, roll2);
    }

    /**
     * Rolls a dice numOfdice times and returns the highest dice rolled
     *
     * @param numOfDice number of dice to roll
     * @return highest roll of teh dice
     */
    public static int highRoll(int numOfDice) {
        int maxDice = rollOneDice();
        for (int i = 0; i < numOfDice - 1; i++) {
            maxDice = Math.max(maxDice, rollOneDice());
        }
        return maxDice;
    }

    /**
     * Rolls a dice
     *
     * @return number between 1 and 6
     */
    private static int rollOneDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
