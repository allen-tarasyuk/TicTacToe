public class CreateGameResponse extends ApplicationMessage {
    // Whether or not the game creation was successful.
    private boolean success;
    // A message indicating the result of the game creation attempt.
    private String message;

    // Constructor that sets the channel, success, and message fields.
    public CreateGameResponse(String channel, boolean success, String message) {
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
