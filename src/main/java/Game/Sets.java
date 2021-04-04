package Game;

import java.util.Arrays;

public class Sets {

    // String representation of every set
    public static final String[][] STRING_SETS = {
            {"INFANTRY", "INFANTRY", "INFANTRY"},
            {"CAVALRY", "CAVALRY", "CAVALRY"},
            {"ARTILLERY", "ARTILLERY", "ARTILLERY"},
            {"INFANTRY", "CAVALRY", "ARTILLERY"},
            {"INFANTRY", "INFANTRY", "WILD CARD"},
            {"INFANTRY", "WILD CARD", "WILD CARD"}, //5
            {"CAVALRY", "CAVALRY", "WILD CARD"},
            {"CAVALRY", "WILD CARD", "WILD CARD"},
            {"ARTILLERY", "ARTILLERY", "WILD CARD"},
            {"ARTILLERY", "WILD CARD", "WILD CARD"},
            {"CAVALRY", "ARTILLERY", "WILD CARD"}, //10
            {"INFANTRY", "ARTILLERY", "WILD CARD"},
            {"INFANTRY", "CAVALRY", "WILD CARD"}}; //12

    // Array used to compare the users cards too
    public static final int[][] POSSIBLE_SETS = {
            {3, 0, 0, 0},
            {0, 3, 0, 0},
            {0, 0, 3, 0},
            {1, 1, 1, 0},
            {2, 0, 0, 1},
            {1, 0, 0, 2},//5
            {0, 2, 0, 1},
            {0, 1, 0, 2},
            {0, 0, 2, 1},
            {0, 0, 1, 2},
            {0, 1, 1, 1}, //10
            {1, 0, 1, 1},
            {1, 1, 0, 1}}; //12

    // The number of troops you get for trading a set
    public static int setsValue = 4;
    // Number representing the amount of valid sets you can trade
    public static int validSet = 0;

    /**
     * Used to update the troops given from trading in a set.
     * Sets valid sets back to zero and increments the value according to risk rules.
     */
    public static void updateSetsValue() {
        validSet = 0;
        if(setsValue >= 10)
            setsValue += 5;
        else
            setsValue += 2;
    }

    /**
     * Checks if a player has a set of cards they can trade.
     * @param insigniasID An array containing information about the number of insignias they own.
     * @return true if there is a valid set of cards to trade, otherwise false.
     */
    public static boolean isValidSet(int[] insigniasID) {
        for (int[] possibleSet : POSSIBLE_SETS) {
            if ((insigniasID[0] >= possibleSet[0]) && (insigniasID[1] >= possibleSet[1]) && (insigniasID[2] >= possibleSet[2]) && (insigniasID[3] >= possibleSet[3]))
                return true;
        }
        return false;
    }

    /**
     * Builds a string on what cards the player can play.
     * @param insigniasID An array containing information about the number of insignias they own.
     * @return String representing the set of cards a user can trade.
     */
    public static String setsToPlay(int[] insigniasID) {
        StringBuilder builder = new StringBuilder("> You can exchange the following: \n");
        int set = 0;
        for (int[] possibleSet : POSSIBLE_SETS) {
            if ((insigniasID[0] >= possibleSet[0]) && (insigniasID[1] >= possibleSet[1]) && (insigniasID[2] >= possibleSet[2]) && (insigniasID[3] >= possibleSet[3])) {
                validSet++;
                builder.append(validSet).append(". ").append(Arrays.toString(STRING_SETS[set])).append("\n");
            }
            set++;
        }
        return builder.toString();
    }

    public static int getSetsValue() {
        return setsValue;
    }

}
