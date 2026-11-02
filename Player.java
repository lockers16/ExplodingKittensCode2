import java.util.ArrayList;

public class Player {

    private String name;
    private int numInOrder;
    private int cardsNum;
    private ArrayList<Card> cards;

    public Player(String name, int cardsNum, int numInOrder, ArrayList<Card> lst) {
        this.name = name;
        this.numInOrder = numInOrder;
        this.cardsNum = cardsNum;
        this.cards = new ArrayList<Card>();
        for (int i = 0; i < lst.size(); i++) {
            this.cards.add(lst.get(i));
        }
    }

    public Player(String name, int numInOrder) {
        this.name = name;
        this.numInOrder = numInOrder;
        this.cardsNum = 0;
        this.cards = new ArrayList<Card>();
    }

    public void addCard(Card c) {
        this.cards.add(c);
    }

    public String allCards() {
        String str = "";
        for (int i = 0; i < this.cards.size()-1; i++) {
            str += this.cards.get(i).fullCard() + "  / ";
        }
        str += this.cards.get(this.cards.size()-1).fullCard();
        return str;
    }

    @Override
    public String toString() {
        return "Player no. " + this.numInOrder
                + ", " + this.name
                + ", cards: {" + allCards()
                + "}";
    }
}
