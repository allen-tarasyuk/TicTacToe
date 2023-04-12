import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Router {
    private static final int PORT = 8000;
    private ServerSocket serverSocket;
    // A map of channel names to listeners.
    public static final Map<String, ArrayList<ClientConnection>> channelListeners = new HashMap<>();

    // Constructor that initializes the channelListeners map.
    public Router(ServerSocket serverSocket) {

        // Creating room for clients without channel yet
        channelListeners.put("/unknown", new ArrayList<>());

        this.serverSocket = serverSocket;
    }

    public void startRouter() {

        System.out.println("Starting router on " + this.serverSocket.getLocalSocketAddress().toString());

        try {

            System.out.println("Ready to accept clients!");

            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");
                // Adding client connection thread to unknown channel
                ClientConnection clientConnection = new ClientConnection(socket);
                channelListeners.get("/unknown").add(clientConnection);
                clientConnection.start();
            }
        } catch (IOException e) {
            System.out.println("Unable to start router on " + this.serverSocket.getLocalSocketAddress().toString());
        }
    }

    public static void handleMessage(String serializedString) {
        Packet packet = deserializePacket(serializedString);
        String messageType = packet.getMessageType();
        ApplicationMessage message = packet.getMessage();

        // TODO send responses

        switch (messageType) {
            case "create-room":
                CreateGameRequest createGameMessage = (CreateGameRequest) message;
                String newGameRoom = createGameMessage.getGameName();
                String newGameClientName = createGameMessage.getClientName();
                ArrayList<ClientConnection> newChannelClientConnections = new ArrayList<>();
                ClientConnection newGameClientConnection = getClientConnectionByChannel("/unknown", newGameClientName);
                newChannelClientConnections.add(newGameClientConnection);
                removeClientConnectionByChannel("/unknown", newGameClientName);
                Router.channelListeners.put(newGameRoom, newChannelClientConnections);
                // TODO send response to client
                break;
            case "join-room":
                JoinGameRequest joinGameMessage = (JoinGameRequest) message;
                String gameToJoin = joinGameMessage.getGameName();
                String joinGameClientName = joinGameMessage.getClientName();
                ClientConnection joinGameClientConnection = getClientConnectionByChannel("/unknown", clientName);
                Router.channelListeners.get(gameToJoin).add(joinGameClientConnection);
                removeClientConnectionByChannel("/unknown", joinGameClientName);
                // TODO send response to client
                break;
            case "list-games":
                break;
            case "make-move":
                String channel = packet.getChannel();
                break;
            default:
                System.out.println("Unknown message type");
        }
    }

    // Deserializes a String into a Packet object.
    public static Packet deserializePacket(String serializedMessage) {
        byte[] serializedByteArray = serializedMessage.getBytes();

        try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedByteArray);
                ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (Packet) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
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

    // Sends a message to the listener registered for its channel.
    public void sendMessage(String channel, ApplicationMessage message, String name) {
        // Serialize the message into a byte array.
        String serializedMessage = new String(serializeMessage(message), StandardCharsets.UTF_8);
        // Get the listener for the message's channel.
        ArrayList<ClientConnection> channelClientConnections = channelListeners.get(channel);

        if (name != null) {
            // TODO send to specific name
        } else {
            for (ClientConnection clientConnection : channelClientConnections) {
                try {
                    clientConnection.bufferedWriter.write(serializedMessage);
                    clientConnection.bufferedWriter.newLine();
                    clientConnection.bufferedWriter.flush();
                } catch (IOException e) {
                    System.out.println("Unable to send message to channel");
                }
            }
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientConnection getClientConnectionByChannel(String channel, String clientName) {
        for (ClientConnection clientConnection : channelListeners.get(channel)) {
            if (clientConnection.clientName.equals(clientName)) {
                return clientConnection;
            }
        }

        return null;
    }

    public static boolean removeClientConnectionByChannel(String channel, String clientName) {
        ArrayList<ClientConnection> channelClientConnections = channelListeners.get(channel);
        for (ClientConnection clientConnection : channelClientConnections) {
            if (clientConnection.clientName.equals(clientName)) {
                channelClientConnections.remove(clientConnection);
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Router router = new Router(serverSocket);
        router.startRouter();
    }
}