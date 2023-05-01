import java.io.Serializable;

public abstract class ApplicationMessage implements Serializable {
    // The channel on which the message will be sent.
    private String channel;
    private String clientName;

    public ApplicationMessage() {
        this.channel = "";
        this.clientName = "";
    }

    // Constructor that sets the channel field.
    public ApplicationMessage(String channel) {
        this.channel = channel;
        this.clientName = "";
    }

    public ApplicationMessage(String channel, String clientName) {
        this.channel = channel;
        this.clientName = clientName;
    }

    // Getter for the channel field.
    public String getChannel() {
        return channel;
    }

    public String getClientName() {
        return clientName;
    }
}
