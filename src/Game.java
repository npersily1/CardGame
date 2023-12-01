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
        //deck.shuffle();
        middle = new Card[3];

    }

    public void play()
    {
        //intro / instructions
        System.out.println("Weklcome to Holdem");
        clearScreen();
        //game loop
        boolean didFold = false;
        int counter = 0;
        while (player.getPoints() < 0 || dealer.getPoints() < 0) {
            //deal player cards
            player.setPoints(player.getPoints() - 5);
            this.deal();
            for(int i = 0 ; i < 4; i++) {
                didFold = willFold();
                if (didFold) {
                    break;
                }
                player.setPoints(player.getPoints() - 5);
                this.printHiddenBoard(counter++);
            }
            if(!didFold && win())
            {
                player.setPoints(5 * counter);
            }
            this.printFinalBoard();
            clearScreen();
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
    public void printFinalBoard()
    {
        System.out.println( player + player.getHandName());
        this.printMiddle(3);
        System.out.println(dealer + dealer.getHandName());


    }
    //prints current board
    public void printHiddenBoard(int counter)
    {
        System.out.println( player );
        this.printMiddle(counter);
        System.out.println("dealer ___ ___" + "Dealer has " + dealer.getPoints() + " points");


    }
    //prints some of middle five cards
    public void printMiddle(int counter)
    {
        if (counter == 0) {
            System.out.println("___ ___ ___ ___ ___ ___");
        } else if (counter == 1) {
            for (int i = 0; i < 1; i++) {
                System.out.print(middle[i]);
            }
            System.out.println(" ___ ___");
        }
        else if (counter == 2) {
            for (int i = 0; i < 2; i++) {
                System.out.print(middle[i]);
            }

            System.out.println(" ___");
        }
        else  {
            for (int i = 0; i < 3; i++) {
                System.out.print(middle[i]);
            }
            System.out.println();
        }
    }
    public void deal()
    {
        for (int i = 0; i < 2; i++) {
            player.getHand()[i] = deck.deal();

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
    // source https://replit.com/talk/ask/How-do-you-clear-terminal-in-Java/46341
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public void reset()
    {
        for (int i = 0; i < 2; i++) {
            player.getHand()
        }
    }

    public static void main(String[] args) {
        System.out.println("What is your name");
        Player player = new Player(s.nextLine());
        Player dealer = new Player("dealer");
        Game game = new Game(player, dealer);
        game.play();




    }

}