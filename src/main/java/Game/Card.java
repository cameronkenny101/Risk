package Game;

public class Card {

    private int insignia;// 0 for infantry, 1 for cavalry, 2 for battalion, 3 for wildcard
    private int cardIndex;
    private boolean wildCard;

    public static final int INFANTRY = 0;
    public static final int CAVALRY = 1;
    public static final int ARTILLERY = 2;
    public static final int WILD_CARD = 3;
    public static final int[][] POSSIBLE_SETS = {
            {INFANTRY, INFANTRY, INFANTRY},
            {CAVALRY, CAVALRY, CAVALRY},
            {ARTILLERY, ARTILLERY, ARTILLERY},
            {INFANTRY, CAVALRY, ARTILLERY},
            {INFANTRY, INFANTRY, WILD_CARD},
            {INFANTRY, WILD_CARD, WILD_CARD},
            {CAVALRY, CAVALRY, WILD_CARD},
            {CAVALRY, WILD_CARD, WILD_CARD},
            {ARTILLERY, ARTILLERY, WILD_CARD},
            {ARTILLERY, WILD_CARD, WILD_CARD},
            {CAVALRY, ARTILLERY, WILD_CARD},
            {INFANTRY, ARTILLERY, WILD_CARD},
            {INFANTRY, CAVALRY, WILD_CARD}};
    public static final int SET_SIZE = 3;
    public Card(int insignia, int cardIndex) {
        setInsignia(insignia);
        setCardIndex(cardIndex);
        setWildCard(cardIndex >= 52);
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int countryIndex) {
        this.cardIndex = countryIndex;
    }

    public int getInsignia() {
        return insignia;
    }

    public void setInsignia(int insignia) {
        this.insignia = insignia;
    }

    public boolean isWildCard() {
        return wildCard;
    }

    public void setWildCard(boolean wildCard) {
        this.wildCard = wildCard;
    }

    public static boolean isValidSet(int[] insigniasID) {
        for (int[] possibleSet : POSSIBLE_SETS) {
            if ((insigniasID[0] == possibleSet[0]) && (insigniasID[1] == possibleSet[1]) && (insigniasID[2] == possibleSet[2]))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (insignia == 0)
            return "Infantry";
        else if (insignia == 1)
            return "Cavalry";
        else if (insignia == 2)
            return "Battalion";
        else
            return "Wildcard";
    }
}
