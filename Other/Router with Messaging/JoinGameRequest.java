public class JoinGameRequest extends ApplicationMessage {
    // The name of the game to join.
    private String gameName;
    // The name of the player joining the game.
    private String playerName;

    // Constructor that sets the channel, gameName, and playerName fields.
    public JoinGameRequest(String channel, String gameName) {
        super(channel);
        this.gameName = gameName;
    }

    // Getter for the gameName field.
    public String getGameName() {
        return gameName;
    }

}