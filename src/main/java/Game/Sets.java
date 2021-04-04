package Game;

import java.util.Arrays;

public class Sets {

    public static final int SET_SIZE = 3;

    public static final String[][] STRING_SETS = {
            {"INFANTRY ", "INFANTRY", "INFANTRY"},
            {"CAVALRY ", "CAVALRY ", "CAVALRY"},
            {"ARTILLERY ", "ARTILLERY ", "ARTILLERY"},
            {"INFANTRY ", "CAVALRY ", "ARTILLERY"},
            {"INFANTRY ", "INFANTRY ", "WILD_CARD"},
            {"INFANTRY ", "WILD_CARD ", "WILD_CARD"}, //5
            {"CAVALRY ", "CAVALRY ", "WILD_CARD"},
            {"CAVALRY ", "WILD_CARD ", "WILD_CARD"},
            {"ARTILLERY ", "ARTILLERY ", "WILD_CARD"},
            {"ARTILLERY ", "WILD_CARD ", "WILD_CARD"},
            {"CAVALRY ", "ARTILLERY ", "WILD_CARD"}, //10
            {"INFANTRY ", "ARTILLERY ", "WILD_CARD"},
            {"INFANTRY ", "CAVALRY ", "WILD_CARD"}}; //12

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

    public static int setsValue = 4;
    public static int validSet = 1;

    public static int getSetsValue() {
        return setsValue;
    }

    public static void updateSetsValue() {
        if(setsValue >= 10)
            setsValue += 5;
        else
            setsValue += 2;
    }

    public static boolean isValidSet(int[] insigniasID) {
        for (int[] possibleSet : POSSIBLE_SETS) {
            if ((insigniasID[0] == possibleSet[0]) && (insigniasID[1] == possibleSet[1]) && (insigniasID[2] == possibleSet[2]) && (insigniasID[3] == possibleSet[3]))
                return true;
        }
        return false;
    }

    public static String setsToPlay(int[] insigniasID) {
        StringBuilder builder = new StringBuilder("> You can exchange the following: \n");
        int set = 0;
        for (int[] possibleSet : POSSIBLE_SETS) {
            if ((insigniasID[0] == possibleSet[0]) && (insigniasID[1] == possibleSet[1]) && (insigniasID[2] == possibleSet[2])) {
                builder.append(validSet).append(". ").append(Arrays.toString(STRING_SETS[set]));
                validSet++;
            }
            set++;
        }
        return builder.toString();
    }

}
