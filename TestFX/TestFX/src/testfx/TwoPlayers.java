package testfx;

public final class TwoPlayers {

    private Player player1;
    private Player player2;

    private final static TwoPlayers INSTANCE = new TwoPlayers();

    private TwoPlayers() {
        this.player1 = new Player();
        this.player2 = new Player();
    }

    public static TwoPlayers getInstance() {
        return INSTANCE;
    }

    public void resetPlayers() {
        this.player1 = new Player();
        this.player2 = new Player();
    }

    public Player getPlayer1() {
        return player1;
    }

//    public void setPlayer1(Player player1) {
//        this.player1 = player1;
//    }

    public Player getPlayer2() {
        return player2;
    }

//    public void setPlayer2(Player player2) {
//        this.player2 = player2;
//    }
}
