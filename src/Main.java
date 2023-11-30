import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Game {

    private Deck deck;
    private Player player;
    private Player cpu;
    private ArrayList<Card> middle;
    private static Scanner s = new Scanner(System.in);



    public Game(Player player, Player cpu) {

        this.player = player;
        player.setPoints(100);
        this.cpu = cpu;
        cpu.setPoints(100);
        this.createDeck();
        deck.shuffle();
        middle = new ArrayList<Card>();

    }

    public void play()
    {
        //intro / instructions

        //game loop
        int counter = 0;
        while (player.getPoints() < 0 || cpu.getPoints() < 0) {
            //deal player cards
            player.setPoints(player.getPoints() - 5);
            this.deal();
            if(willFold())
            {
                break;
            }
            //fold or continue
            //deal flop
            //fold or continue
            // deal turn
            // fold or continue
            //deal river
            //fold or continue;
            // reveal
        }

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
    //prints board
    public void printBoard(int counter)
    {
        System.out.println( player );
        this.printMiddle(counter);
        System.out.println("CPU ___ ___" + "Dealer has " + cpu.getPoints() + " points");


    }
    //prints some of middle five cards
    public void printMiddle(int counter)
    {
        if (counter == 0) {
            System.out.println("___ ___ ___ ___ ___ ___");
        } else if (counter == 1) {
            for (int i = 0; i < 3; i++) {
                System.out.print(middle.get(i));
            }
            System.out.println(" ___ ___");
        }
        else if (counter == 2) {
            for (int i = 0; i < 4; i++) {
                System.out.print(middle.get(i));
            }

            System.out.println(" ___");
        }
        else  {
            for (int i = 0; i < 5; i++) {
                System.out.print(middle.get(i));
            }
            System.out.println();
        }
    }
    public void deal()
    {
        for (int i = 0; i < 2; i++) {
            player.addCard(deck.deal());
            cpu.addCard(deck.deal());
        }

    }

    // creates complete 52 card deck with ace being a high value
    public void createDeck()
    {
        String[] ranks = new String[13];
        int[] points = new int[13];
        String[] suits = {"Hearts", "Diamonds","Clubs","Spades"};
        for (int i = 2; i < 15; i++) {
            String rank = checkRoyal(i);
            ranks[i] = rank;
            points[i] = i;
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

    public static void main(String[] args) {

        Player player = new Player(s.nextLine());
        Player cpu = new Player("CPU");
        Game game = new Game(player, cpu);
        game.play();




    }
}