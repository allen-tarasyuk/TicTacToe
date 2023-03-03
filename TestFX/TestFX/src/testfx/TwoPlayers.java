package testfx;

public final class TwoPlayers {

    private final Player player1;
    private final Player player2;

    private static TwoPlayers INSTANCE = new TwoPlayers();

    private TwoPlayers() {
        this.player1 = new Player();
        this.player2 = new Player();

        this.player1.setGameSymbolText("X");
        this.player2.setGameSymbolText("O");
    }

    public static TwoPlayers getInstance() {
        return INSTANCE;
    }

    public static void resetPlayers() {
        TwoPlayers.INSTANCE = new TwoPlayers();
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
