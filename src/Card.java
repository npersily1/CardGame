public class Card {
    private int point;
    private String suit;
    private String rank;
    public Card(int point, String suit, String rank)
    {
        this.point = point;
        this.suit = suit;
        this.rank = rank;
    }
    //getters
    public int getPoint() {
        return point;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    //setters
    public void setPoint(int point) {
        this.point = point;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }


    public String toString() {
        return rank + " of " + suit;
    }
}
