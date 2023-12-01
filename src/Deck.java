import java.util.ArrayList;

public class Deck {
   private final ArrayList<Card> deck;
   private int cardsLeft;

    public Deck(int[] point, String[] suit, String[] rank) {
        deck = new ArrayList<Card>();
        for (int i = 0; i < 13; i++)
        {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card(point[i], suit[j], rank[i]));
            }
        }
        cardsLeft = deck.size();
    }
    public boolean isEmpty()
    {
        return ! (cardsLeft > 0);
    }
    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal()
    {
        if(deck.isEmpty())
        {
            return null;
        }
        if(cardsLeft == 0)
        {
            return null;
        }
        return deck.get(--cardsLeft);

    }
    public void shuffle()
    {
        for (int i = cardsLeft -1; i >= 0 ; i--) {
            int r = (int) (Math.random() * i);
            Card temp = deck.get(i);
            deck.set(i,deck.get(r));
            deck.set(r, temp);

        }


    }

}
