public class Action {

    private Player player;
    private String Action;
    private Card card;

    public Action(Player player, String action, Card card) {
        this.player = player;
        Action = action;
        this.card = card;
    }

    public Player getPlayer() {
        return player;
    }

    public String getAction() {
        return Action;
    }

    public Card getCard() {
        return card;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setAction(String action) {
        Action = action;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
