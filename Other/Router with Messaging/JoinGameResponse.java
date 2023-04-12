public class JoinGameResponse extends ApplicationMessage {
    // Whether or not the player joined the game successfully.
    private boolean success;
    // A message indicating the result of the player joining attempt.
    private String message;

    // Constructor that sets the channel, success, and message fields.
    public JoinGameResponse(String channel, boolean success, String message) {
        super(channel);
        this.success = success;
        this.message = message;
    }

    // Getter for the success field.
    public boolean isSuccess() {
        return success;
    }

    // Getter for the message field.
    public String getMessage() {
        return message;
    }
}