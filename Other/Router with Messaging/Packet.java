import java.io.Serializable;

public class Packet implements Serializable {
    // The channel on which the message will be sent.
    private String channel;
    private String messageType;
    private ApplicationMessage message;

    public Packet(String channel, String messageType, ApplicationMessage message) {
        this.channel = channel;
        this.messageType = messageType;
        this.message = message;
    }

    // Getter for message type field
    public String getMessageType() {
        return this.messageType;
    }

    // Getter for the channel field.
    public String getChannel() {
        return this.channel;
    }

    // Getter for the message field.
    public ApplicationMessage getMessage() {
        return this.message;
    }
}
