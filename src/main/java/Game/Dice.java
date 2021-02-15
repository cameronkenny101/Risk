package Game;

import java.util.Random;

public class Dice {

    public int rollDice() {
        Random random = new Random();
        return random.nextInt(11)+2;
    }

    public int bestRoll(int roll1, int roll2) {
        return Integer.compare(roll1, roll2);
    }


}
