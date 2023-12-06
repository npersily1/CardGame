import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Game {

    private Deck deck;
    private Player player;
    private Player dealer;
    private Card[] middle;
    private static Scanner s = new Scanner(System.in);



    public Game(Player player, Player dealer) {

        this.player = player;
        player.setPoints(100);
        this.dealer = dealer;
        dealer.setPoints(100);
        this.createDeck();
        deck.shuffle();
        middle = new Card[3];

    }

    public void play()
    {
        //intro / instructions
       printInstructions();
        //game loop
        boolean didFold = false;

        while (player.getPoints() > 0 || dealer.getPoints() > 0) {
            //deal player cards
            int counter = 0;
            player.setPoints(player.getPoints() - 5);
            this.deal();
            for(int i = 0 ; i < 4; i++) {
                player.setPoints(player.getPoints() - 5);
                this.printBoard(++counter);
                didFold = willFold();
                if (didFold) {
                    break;
                }

            }

            boolean win = win();
            printBoard(++counter);
            if(!didFold && win)
            {
                player.setPoints(player.getPoints() + 50);
                System.out.println("You win");
            }
            else if(win)
            {
                System.out.println("You would would have won");
            }
            else
            {
                System.out.println("You lost");
            }


            if(Game.willContinue())
            {
                break;
            }
            reset();

        }

    }
    public static boolean willContinue()
    {
        System.out.println("Do you want to continue playing (press 1 to do so)");
        String willContinue = s.nextLine();
        if (willContinue.equals("1"))
        {
            return true;
        }
        return false;
    }
    public boolean win()
    {
        Checker c = new Checker(middle, player ,dealer);
        return c.won() == player;
    }
    //returns true if they want to fold false otherwise
    public boolean willFold()
    {
        System.out.println("Do you fold (press 1 to do so)");
        String fold = s.nextLine();
        if (fold.equals("1"))
        {
            return true;
        }
        return false;

    }
    //prints current board
    public void printBoard(int counter)
    {


        if(counter == 5)
        {
            System.out.println(player + "  " + player.getHandName());

            this.printMiddle(counter);
            System.out.println("dealer's cards   " + dealer.getHand()[0] + "  " + dealer.getHand()[1] + " " + dealer.getHandName());
            return;
        }
        System.out.println(player);
        this.printMiddle(counter);
        System.out.println("dealer's cards  ___ ___");


    }
    //prints some of middle five cards
    public void printMiddle(int counter)
    {
        System.out.println("Center Cards");
        if (counter == 1) {
            System.out.println("___    ___    ___");
        } else if (counter == 2) {
            for (int i = 0; i < 1; i++) {
                System.out.print(middle[i] + "  ");
            }
            System.out.println("    ___     ___");
        }
        else if (counter == 3) {
            for (int i = 0; i < 2; i++) {
                System.out.print(middle[i] + "   ");
            }

            System.out.println("___");
        }
        else  {
            for (int i = 0; i < 3; i++) {
                System.out.print(middle[i] + "    ");
            }
            System.out.println();
        }
    }
    public void deal()
    {
        for (int i = 0; i < 2; i++) {
            player.getHand()[i] = deck.deal();
            dealer.getHand()[i] = deck.deal();

            middle[i] = deck.deal();
        }
        middle[2] = deck.deal();


    }

    // creates complete 52 card deck with ace being a high value
    public void createDeck()
    {
        String[] ranks = new String[13];
        int[] points = new int[13];
        String[] suits = {"Hearts", "Diamonds","Clubs","Spades"};
        for (int i = 0; i < 13; i++) {
            String rank = checkRoyal(i + 2);
            ranks[i] = rank;
            points[i] = i + 2;
        }
        deck = new Deck(points, suits, ranks);
    }
    // checks if a number is greater than 10 and then returns corresponding special card
    public static String checkRoyal(int num)
    {
        if(num < 11)
            return "" + num;
        if (num == 11)
            return "Jack";
        if (num == 12)
            return "Queen";
        if (num == 13)
            return "King";
        else
            return "Ace";

    }

    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
    public void reset()
    {
        for (int i = 0; i < 2; i++) {
            deck.add(player.getHand()[i]);
            deck.add(dealer.getHand()[i]);
        }
        for (int i = 0; i < 3; i++) {
            deck.add(middle[i]);
        }
        deck.shuffle();
    }
    public void printInstructions() {
        System.out.println("Welcome to Noah's version of Holdem");
        System.out.println("The main difference is that there is only three cards in the center");
        System.out.println("Each round if you do not fold then five points will be deducted from your total");
        System.out.println("I you win you will make double what is put in");
        System.out.println("Good Luck");
    }


    public static void main(String[] args) {
        System.out.println("What is your name");
        Player player = new Player(s.nextLine());
        Player dealer = new Player("dealer");
        Game game = new Game(player, dealer);
        game.play();

    }

}