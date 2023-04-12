import java.util.List;

public class ListGamesResponse extends ApplicationMessage {
    // A list of game names that can be listed for the client.
    private List<String> gameNames;

    // Constructor that sets the channel and gameNames fields.
    public ListGamesResponse(String channel, List<String> gameNames) {
        super(channel);
        this.gameNames = gameNames;
    }

    // Getter for the gameNames field.
    public List<String> getGameNames() {
        return gameNames;
    }
}