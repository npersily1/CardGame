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
        cardsLeft = deck.size();
    }
    public boolean isEmpty()
    {
        return ! (cardsLeft > 0);
    }
    public int getCardsLeft() {
        return cardsLeft;
    }

//    deal — This method “deals” a card by selecting a card from the deck and returning it.
//            ● It returns null if the deck is empty.
//            ● While we could remove cards from our ArrayList of cards, it would actually be
//    easier to keep all of the cards in the ArrayList and track the index for the card that
//    should be dealt next.
//            ○ We can accomplish this by decrementing the cardsLeft instance
//    variable and returning the card at index cardsLeft.
//            ○ In this algorithm, the cardsLeft instance variable does double duty; it
//    determines which card to “deal” and it also represents how many cards in
//    the deck are left to be dealt.

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
            deck.get(i) = deck.get(r);
            deck.get(r) = temp;

        }


    }

}
