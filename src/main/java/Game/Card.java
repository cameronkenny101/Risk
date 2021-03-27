package Game;

public class Card {

    private int insignia;
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

    public String printCard(int cardType) {
        if(cardType == 0)
            return "Infantry";
        else if(cardType == 1)
            return "Cavalry";
        else if(cardType == 2)
            return "Battalion";
        else
            return "Wildcard";
    }
}
