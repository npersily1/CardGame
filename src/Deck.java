import java.util.ArrayList;

public class Deck {
   private ArrayList<Card> deck;
   private int cardsLeft;

    public Deck(int[] point, String[] suit, String[] rank) {
        deck = new ArrayList<Card>();
        for (int i = 0; i < point.length; i++)
        {
           deck.add(new Card(point[i], suit[i], rank[i]));
        }
        cardsLeft = point.length;
    }
    public boolean isEmpty()
    {
        return ! (cardsLeft > 0);
    }
    public int getCardsLeft() {
        return cardsLeft;
    }
}
