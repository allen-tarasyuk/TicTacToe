import java.io.Serializable;

class ApplicationMessage implements Serializable {
    // The channel on which the message will be sent.
    private String channel;
    private String message;

    // Constructor that sets the channel field.
    public ApplicationMessage(String channel, String message) {
        this.channel = channel;
        this.message = message;
    }

    // Getter for the channel field.
    public String getChannel() {
        return channel;
    }
    // Getter for the channel field.
    public String getMessage() {
        return message;
    }
}