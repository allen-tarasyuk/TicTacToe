public class CreateGameRequest extends ApplicationMessage {
    // The name of the game to be created.
    private String gameName;

    // Constructor that sets the channel and gameName fields.
    public CreateGameRequest(String channel, String gameName) {
        super(channel);
        this.gameName = gameName;
    }

    // Getter for the gameName field.
    public String getGameName() {
        return gameName;
    }
}