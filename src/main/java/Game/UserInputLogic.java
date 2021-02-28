package Game;

public class UserInputLogic {

    /**
     * Sets the next turn
     * @param player current player instance
     * @param nextPlayer next player instance
     */
    protected void nextTurn(Player player,Player nextPlayer){
        player.setTurn(false);
        player.setInitTroops(3);
        nextPlayer.setTurn(true);

    }

    /**
     * Converts user input into a country based on levenshtein distance algorithm
     * @param country user input
     * @return index of the country
     */
    protected int shortCountryName(String country){
        int smallestNum = Integer.MAX_VALUE;
        int index = -1;
        int count = 0;

        for(String countries : Constants.COUNTRY_NAMES) {
            int levenshtein = LevenshteinDistance(country, countries);
            if(smallestNum > levenshtein) {
                smallestNum = levenshtein;
                index = count;
            }
            count++;
        }
        return index;
    }

    /**
     * The levenshtein distance algorithm
     * @param a User inputted string
     * @param b Territory on the map
     * @return The number of operations to make a and b the same string
     */
    protected int LevenshteinDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        int[] prev = new int[b.length() + 1];
        for(int i = 0; i < b.length() + 1; i++)
            prev[i] = i;

        for(int i = 1; i <= a.length(); i++) {
            prev[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(prev[j], prev[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = prev[j];
                prev[j] = cj;
            }
        }
        return prev[b.length()];
    }



}
