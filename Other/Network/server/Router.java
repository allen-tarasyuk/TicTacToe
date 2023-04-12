import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Router {
    // A map of channel names to listeners.
    private Map<String, Consumer<byte[]>> channelListeners;

    // Constructor that initializes the channelListeners map.
    public Router() {
        channelListeners = new HashMap<>();
    }

    // Registers a listener for a given channel.
    public void registerChannel(String channel, Consumer<byte[]> listener) {
        channelListeners.put(channel, listener);
    }

    // Sends a message to the listener registered for its channel.
    public void sendMessage(ApplicationMessage message) {
        // Serialize the message into a byte array.
        byte[] serializedMessage = serializeMessage(message);
        // Get the listener for the message's channel.
        Consumer<byte[]> listener = channelListeners.get(message.getChannel());
        // If there is a listener, send the message to it.
        if (listener != null) {
            listener.accept(serializedMessage);
        }
    }

    // Serializes an ApplicationMessage object into a byte array.
    public static byte[] serializeMessage(ApplicationMessage message) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(message);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Deserializes a byte array into an ApplicationMessage object.
    public static ApplicationMessage deserializeMessage(byte[] serializedMessage) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMessage);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (ApplicationMessage) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}