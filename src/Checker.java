import java.util.Arrays;

public class Checker {
    public final static String[] COMBOS = {"High Card", "Pair", "Three of a Kind", "Full House", "Four of a Kind", "Flush", "Straight", "Straight Flush"};
    public static final int PAIR = 100;
    public static final int THREE = 200;
    public static final int FULLHOUSE = 300;
    public static final int FOUR = 400;
    public static final int FLUSH = 500;
    public static final int STRAIGHT = 600;
    public static final int STRAIGHTFLUSH = 700;

    Card[] p1;
    int p1score;
    Card[] p2;
    private  Player p1ref;
    private Player p2ref;
    int p2score;

    public Checker(Card[] middle, Player p1, Player p2)
    {
        p1score = 0;
        p2score = 0;
        this.p1 = new Card[5];
        this.p2 = new Card[5];
        p1ref = p1;
        p2ref = p2;
        for (int i = 0; i < 2; i++) {

            this.p1[i] = p1.getHand()[i];
            this.p2[i] = p2.getHand()[i];

        }

        for (int i = 2; i < 5; i++) {
            this.p1[i] = middle[i -2];
            this.p2[i] = middle[i -2];
        }
    }
    public Player won()
    {
        p1score = assign(p1);
        p2score = assign(p2);
        p1ref.setHandName(this.getHandName(p1score));
        p2ref.setHandName(this.getHandName(p2score));
        if (p1score > p2score)
        {
            return p1ref;
        } else if(p1score < p2score) {
            return p2ref;
        }
        else {
             tie();
        }
    }
    public Player tie()
    {

        while(getHighCard(p1) == getHighCard(p2))
        {
            p1[indexOf(p1,getHighCard(p1))] = null;
            p2[indexOf(p2,getHighCard(p2))] = null;
        }
        if(getHighCard(p1) > getHighCard(p2))
        {
            return p1ref;
        }
        return p2ref;

    }
    public int indexOf(Card[] hand, int point)
    {
        for (int i = 0; i < 5; i++) {
            if (hand[i].getPoint() == point)
                return i;
        }
    }
    public String getHandName(int score)
    {
        if(COMBOS[score / 100].equals("High Card"))
        {
            return "High Card" + Game.checkRoyal(score % 100);
        }

        return  COMBOS[score / 100]+ " with a high card " + Game.checkRoyal(score % 100);
    }
    public int assign(Card[] hand)
    {
        int temp = getStraightFlush(hand);
        if(temp / 100 != 0)
        {
            return temp;
        }
        temp = straight(hand);
        if(temp / 100 != 0)
        {
            return temp;
        }
        temp = flush(hand);
        if(temp / 100 != 0)
        {
            return temp;
        }


        temp = getFullHouse(hand);
        if(temp / 100 != 0)        {
            return temp;
        }
        temp = numPair(hand);
        if(temp / 100 != 0)        {
            return temp;
        }
        temp = getPair(hand);
        if(temp / 100 != 0)        {
            return temp;
        }
        else
        {
          return getHighCard(hand);
        }
    }
    public  int flush(Card[] hand)
    {
        for (int i = 0; i < 4; i++) {

            if(!(hand[i].getSuit().equals(hand[i + 1].getSuit())))
            {
                return 0;
            }
        }
        return FLUSH + getHighCard(hand) ;
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
        return max;
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
        return STRAIGHT + getHighCard(hand);
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
                return 500 + hand[i].getPoint();
            }
            if(counter == 3)
            {
                return 200 + hand[i].getPoint();
            }

        }
        return 0;
    }
    public int getPair(Card[] hand)
    {
        int pair1 = 0;
        int pair2 = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
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
        if (pair1 != 0) {
            return PAIR + Math.max(pair1, pair2) ;
        }
        return 0;
    }
    public int getFullPair(Card[] hand, int other)
    {
        int pair1 = 0;
        int pair2 = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                if(pair1 == 0 && hand[i].getPoint() == hand[j].getPoint() && hand[j].getPoint() != other)
                {
                    pair1 = hand[j].getPoint();
                    break;
                }
                if (hand[i].getPoint() == hand[j].getPoint() && hand[j].getPoint() != other)
                {
                    pair2 = hand[j].getPoint();
                    break;
                }
            }
        }
        if (pair1 != 0) {
            return PAIR + Math.max(pair1, pair2) ;
        }
        return 0;
    }
    public int getFullHouse(Card[] hand)
    {
        int three = numPair(hand);
        int two = getFullPair(hand, three % 100);

        if (two / 100 == 1)
            if (three / 100 == 2)
            {
                return FULLHOUSE + three % 100;
            }
        return 0;
    }
    public int getStraightFlush(Card[] hand)
    {
        int flush = flush(hand);
        int straight = straight(hand);
        if (flush / 100 == 5)
            if (straight / 100 == 6)
            {
                //not redundant because of truncation
                return STRAIGHTFLUSH + straight % 100;
            }
        return 0;
    }
}
