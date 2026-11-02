import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class GameManager {

    private Player[] players;
    private int playersNum;
    private ArrayList<Card> deck;
    private ArrayList<Card> bombs;
    private ArrayList<Card> defuses;
    private ArrayList<Card> discard;
    private Stack<Action> lastActions;
    private int currentTurn;

    public GameManager() {
        this.deck = new ArrayList<Card>();
        this.defuses = new ArrayList<Card>();
        this.bombs = new ArrayList<Card>();
        this.discard = new ArrayList<Card>();
        this.lastActions = new Stack<Action>();
        this.currentTurn = 1;
        createCards();
        addPlayers();
    }

    public void createCards() { // יצירת עצמים לכל הקלפים במשחק
        Card card;
        for (int i = 0; i < 4; i++) {
            card = new Card(CardType.EXPLODE,i+1);
            bombs.add(card);
        }

        for (int i = 0; i < 6; i++) {
            card = new Card(CardType.DEFUSE,i+1);
            defuses.add(card);
        }

        for (int i = 0 ; i < 4; i++) {
            card = new Card(CardType.ATTACK,i+1);
            deck.add(card);
        }

        for (int i = 0 ; i < 4; i++) {
            card = new Card(CardType.FAVOR,i+1);
            deck.add(card);
        }

        for (int i = 0 ; i < 5; i++) {
            card = new Card(CardType.NOPE,i+1);
            deck.add(card);
        }

        for (int i = 0 ; i < 4; i++) {
            card = new Card(CardType.SHUFFLE,i+1);
            deck.add(card);
        }

        for (int i = 0 ; i < 4; i++) {
            card = new Card(CardType.SKIP,i+1);
            deck.add(card);
        }

        for (int i = 0 ; i < 5; i++) {
            card = new Card(CardType.SEE_THE_FUTURE,i+1);
            deck.add(card);
        }

        for (int i = 0 ; i < 4; i++) {
            card = new Card(CardType.CAT_TACO,i+1);
            deck.add(card);
        }

        for (int i = 0 ; i < 4; i++) {
            card = new Card(CardType.CAT_WATERMELON,i+1);
            deck.add(card);
        }

        for (int i = 0 ; i < 4; i++) {
            card = new Card(CardType.CAT_POTATO,i+1);
            deck.add(card);
        }

        for (int i = 0 ; i < 4; i++) {
            card = new Card(CardType.CAT_BEARD,i+1);
            deck.add(card);
        }

        for (int i = 0 ; i < 4; i++) {
            card = new Card(CardType.CAT_RAINBOW,i+1);
            deck.add(card);
        }

        System.out.println("All Cards, after initiallization: \n" + printLst(this.deck));
    }

    public void addPlayers() { // הוספת שחקנים למשחק
        Scanner reader = new Scanner(System.in);
        System.out.println("How many Players do you want to add? (2-4)");
        int num = 0;
        while (num < 2 || num > 4) {
            System.out.print("Insert a number between 2 and 4: ");
            num = reader.nextInt();
        }
        this.playersNum = num;
        players = new Player[num];
        System.out.println("For each player, insert his/her name: ");
        for (int i = 0; i < num; i++) {
            System.out.print("Player no. " + (i+1) + ": ");
            players[i] = new Player(reader.next(),i+1);
        }
        System.out.print("type \"start\" to start dealing the cards: ");
        while (!reader.next().equals("start")) {
            System.out.print("type \"start\" to start dealing the cards: ");
        }
        dealCards();
    }

    public void shuffle() {
        Random rnd = new Random();
        int num1, num2;
        Card c;
        for (int i = 0; i < 100; i++) {
            num1 = rnd.nextInt(this.deck.size());
            num2 = rnd.nextInt(this.deck.size());
            while (num2 == num1)
                num2 = rnd.nextInt(this.deck.size());
            c = this.deck.set(num1,this.deck.get(num2));
            c = this.deck.set(num2,c);
        }
    }

    public void dealCards() {
        // ערבוב הקופה לראשונה - לא עורבבה ביצירת הקלפים
        shuffle();
        System.out.println("All Cards, after shuffle 1:  " + printLst(this.deck));
        // חלוקת שבעה קלפים לכל שחקן
        for (int i = 0; i < this.players.length; i++) {
            for (int  j = 0; j < 7; j++) {
                this.players[i].addCard(this.deck.getFirst());
                this.deck.remove(0);
            }
        }

        // חלוקת קלף נטרול אחד לכל שחקן
        for (int i = 0; i < players.length; i++) {
            players[i].addCard(getDefuse());
        }
        // העברת שאר קלפי הנטרול לקופה
        int def; // מספר הנטרולים להעברה לקופה
        if (this.players.length == 2 || this.players.length == 3)
            def = 2;
        else
            def = this.defuses.size();
        for (int i = 0; i < def; i++) {
            this.deck.add(this.defuses.get(0));
            this.defuses.remove(0);
        }
        // הוספת קלפי פצצות כמספר השחקנים פחות אחד
        for (int i = 0; i < this.players.length-1; i++) {
            this.deck.add(this.bombs.getFirst());
            this.bombs.removeFirst();
        }
        // ערבוב הקופה מחדש לאחר הוספת הנטרולים והפצצות לקופה
        shuffle();

        System.out.println("All Cards, after shuffle 1:  " + printLst(this.deck));

        printAll();
    }

    public Card getDefuse() {
        Random rnd = new Random();
        int num = rnd.nextInt(this.defuses.size());
        Card c = this.defuses.get(num);
        this.defuses.remove(num);
        return c;
    }

    public void printAll() {
        for (int i = 0; i < this.players.length; i++) {
            System.out.println(this.players[i]);
        }
    }

    public String printLst(ArrayList<Card> lst) {
        String str = "";
        int num = 0;
        for (int i = 0; i < lst.size()-1; i++) {
            str += (i+1) + ". " + lst.get(i).fullCard() + "\n";
            num = i+1;
        }
        str += (num+1) + ". " +  lst.get(lst.size()-1).fullCard();
        return str;
    }




}
