package Game;

public class Sets {

    public static final int SET_SIZE = 3;

    public static final int[][] POSSIBLE_SETS = {
            {3, 0, 0, 0},
            {0, 3, 0, 0},
            {0, 0, 3, 0},
            {1, 1, 1, 0},
            {2, 0, 0, 1},
            {1, 0, 0, 2},
            {0, 2, 0, 1},
            {0, 1, 0, 2},
            {0, 0, 2, 1},
            {0, 0, 1, 2},
            {0, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 0, 1}};

    public static boolean isValidSet(int[] insigniasID) {
        for (int[] possibleSet : POSSIBLE_SETS) {
            if ((insigniasID[0] == possibleSet[0]) && (insigniasID[1] == possibleSet[1]) && (insigniasID[2] == possibleSet[2]))
                return true;
        }

        return false;
    }

}
