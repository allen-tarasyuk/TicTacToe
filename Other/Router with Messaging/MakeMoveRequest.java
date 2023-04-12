public class MakeMoveRequest extends ApplicationMessage {
    // The name of the game in which the player is making a move.
    private String gameName;
    // The name of the player making the move.
    private String playerName;
    // The move to be made.
    private String move;

    // Constructor that sets the channel, gameName, playerName, and move fields.
    public MakeMoveRequest(String channel, String gameName, String playerName, String move) {
        super(channel);
        this.gameName = gameName;
        this.playerName = playerName;
        this.move = move;
    }

    // Getter for the gameName field.
    public String getGameName() {
        return gameName;
    }

    // Getter for the playerName field.
    public String getPlayerName() {
        return playerName;
    }

    // Getter for the move field.
    public String getMove() {
        return move;
    }
}