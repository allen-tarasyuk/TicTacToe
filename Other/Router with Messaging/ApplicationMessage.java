import java.io.Serializable;

public abstract class ApplicationMessage implements Serializable {
    // The channel on which the message will be sent.
    private String channel;
    private String clientName;

    // Constructor that sets the channel field.
    public ApplicationMessage(String channel) {
        this.channel = channel;
    }

    // Getter for the channel field.
    public String getChannel() {
        return channel;
    }

    public String getClientName() {
        return clientName;
    }
}
