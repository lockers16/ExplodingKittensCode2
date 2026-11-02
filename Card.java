public class Card {

    private CardType type;
    private int num;
    private String nickname;

    public Card(CardType type, int num) {
        this.type = type;
        this.num = num;
        this.nickname = "" + type.getFormatName() + "_" + num;
    }


    public String fullCard() {
        return "" + type + ", " + num;
    }
}
