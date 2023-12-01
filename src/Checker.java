import java.util.ArrayList;
import java.util.Arrays;

public class Checker {
    public final static String[] combos = {"High Card", "Pair", "Three", "Four", "Full House", "Flush", "Straight", "Straight Flush"};
    Card[] p1;
    int p1score;
    Card[] p2;
    int p2score;

    public Checker(Card[] middle, Player p1, Player p2)
    {
        p1score = 0;
        p2score = 0;
        this.p1 = new Card[5];
        this.p2 = new Card[5];

        for (int i = 0; i < 3; i++) {

            this.p1[i] = p1.getHand().get(i);
            this.p2[i] = p2.getHand().get(i);

        }

        for (int i = 3; i < 5; i++) {
            this.p1[i] = middle[i];
            this.p2[i] = middle[i];
        }
    }
    public Player won()
    {
        p1score = assign(p1);
        p2score = assign(p2);
        if ()
    }
    public int assign(Card[] hand)
    {
        int temp = getStraightFlush(hand);
        if(temp % 10 != 0)
        {
            return temp % 10 * 1000 + temp / 10;
        }
        temp = straight(hand);
        if(temp % 10 != 0)
        {
            return  temp % 10 * 1000 + temp / 10;
        }
        temp = flush(hand);
        if(temp % 10 != 0)
        {
            return  temp % 10 * 1000 + temp / 10;
        }
        temp = getFullHouse(hand);
        if(temp % 10 != 0)
        {
            return  temp % 10 * 1000 + temp / 10;
        }
        temp = numPair(hand);
        if(temp % 10 != 0)
        {
            return  temp % 10 * 1000 + temp / 10;
        }
        temp = getPair(hand);
        if(temp % 10 != 0)
        {
            return  temp % 10 * 1000 + temp / 10;
        }
        else
        {
          return getHighCard(hand);
        }
    }
    public  int flush(Card[] hand)
    {
        for (int i = 0; i < 5; i++) {

            if(!(hand[i].getSuit().equals(hand[i].getSuit())))
            {
                return 0;
            }
        }
        return getHighCard(hand) + 5;
    }
    public int getHighCard(Card[] hand)
    {
        int max = hand[0].getPoint();
        for (int i = 1; i < 5; i++) {
            if (max < hand[i].getPoint())
            {
                max = hand[i].getPoint();
            }
        }
        return max * 10;
    }
    public int straight(Card[] hand)
    {
        int[] vals = new int[5];
        for (int i = 0; i < 5; i++) {
            vals[i] = hand[i].getPoint();
        }
        Arrays.sort(vals);
        for (int i = 0; i < 4; i++) {
            if (vals[i] + 1 != vals[i+1])
            {
                return 0;
            }
        }
        return getHighCard(hand) + 6;
    }
    public int numPair(Card[] hand){
        for (int i = 0; i < 3; i++) {
            int counter = 0;
            for (int j = i; j < 5; j++) {
                if(hand[i].getPoint() ==  hand[j].getPoint())
                {
                    counter++;
                }
            }
            if(counter == 4)
            {
                return hand[i].getPoint() * 10 + 3;
            }
            if(counter == 3)
            {
                return hand[i].getPoint() * 10 + 2;
            }

        }
        return 0;
    }
    public int getPair(Card[] hand)
    {
        int pair1 = 0;
        int pair2 = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                if(pair1 == 0 && hand[i].getPoint() == hand[j].getPoint())
                {
                    pair1 = hand[j].getPoint();
                    break;
                }
                if (hand[i].getPoint() == hand[j].getPoint())
                {
                    pair2 = hand[j].getPoint();
                    break;
                }
            }
        }
        return Math.max(pair1, pair2) * 10 + 1;
    }
    public int getFullHouse(Card[] hand)
    {
        int two = getPair(hand);
        int three = numPair(hand);
        if (two % 10 == 1)
            if (three % 10 == 3)
            {
                if((two / 10) != (three / 10))
                {
                    //not redundant because of truncation
                    return three / 10 * 10 + 4;
                }
            }
        return 0;
    }
    public int getStraightFlush(Card[] hand)
    {
        int flush = flush(hand);
        int straight = straight(hand);
        if (flush % 10 == 5)
            if (straight % 10 == 6)
            {
                //not redundant because of truncation
                return straight / 10 * 10 + 7;
            }
        return 0;
    }
}
