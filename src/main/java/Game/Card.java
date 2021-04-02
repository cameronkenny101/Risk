package Game;

public class Card {

    private int insignia;// 0 for infantry, 1 for cavalry, 2 for battalion, 3 for wildcard
    private int cardIndex;
    private boolean wildCard;

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

    @Override
    public String toString() {

        if (insignia == 0)
            return Constants.COUNTRY_NAMES.get(cardIndex) + " Infantry";
        else if (insignia == 1)
            return Constants.COUNTRY_NAMES.get(cardIndex) + " Cavalry";
        else if (insignia == 2)
            return Constants.COUNTRY_NAMES.get(cardIndex) + " Battalion";
        else
            return "Wildcard";
    }
}
