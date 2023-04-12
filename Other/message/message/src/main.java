import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* 
 This section defines an abstract class ApplicationMessage that all message types will extend. 
 The ApplicationMessage class has a channel field, which indicates which channel the message should 
 be sent on. The getChannel() method provides access to this field.
*/

// Parent class ApplicationMessage
// Abstract class that represents the common properties of all message types.
abstract class ApplicationMessage implements Serializable {
    // The channel on which the message will be sent.
    private String channel;

    // Constructor that sets the channel field.
    public ApplicationMessage(String channel) {
        this.channel = channel;
    }

    // Getter for the channel field.
    public String getChannel() {
        return channel;
    }
}


/*
 The code below defines the various message types for the application. 
 Each message type extends the ApplicationMessage class and contains fields 
 relevant to that message type, as well as getter methods for accessing those fields.
 */

// Derived classes for the new message types
// Concrete classes that extend ApplicationMessage and define specific message types.
class CreateGameRequest extends ApplicationMessage {
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

class CreateGameResponse extends ApplicationMessage {
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

class JoinGameRequest extends ApplicationMessage {
    // The name of the game to join.
    private String gameName;
    // The name of the player joining the game.
    private String playerName;

    // Constructor that sets the channel, gameName, and playerName fields.
    public JoinGameRequest(String channel, String gameName, String playerName) {
        super(channel);
        this.gameName = gameName;
        this.playerName = playerName;
    }

    // Getter for the gameName field.
    public String getGameName() {
        return gameName;
    }

    // Getter for the playerName field.
    public String getPlayerName() {
        return playerName;
    }
}

class JoinGameResponse extends ApplicationMessage {
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

class MakeMoveRequest extends ApplicationMessage {
    // The name of the game in which the player is making a move.
    private String gameName;
    // The name of the player making the move.
    private String playerName;
    // The move to be made.
    private String move;

    // Constructor that sets the channel, gameName, playerName, and move fields.
    public MakeMoveRequest(String channel, String gameName, String playerName, String move) {
        super(channel);
        this.gameName = gameName;
        this.playerName = playerName;
        this.move = move;
    }

      // Getter for the gameName field.
      public String getGameName() {
        return gameName;
    }

    // Getter for the playerName field.
    public String getPlayerName() {
        return playerName;
    }

    // Getter for the move field.
    public String getMove() {
        return move;
    }
}

class MakeMoveResponse extends ApplicationMessage {
    // Whether or not the move was made successfully.
    private boolean success;
    // A message indicating the result of the move.
    private String message;

    // Constructor that sets the channel, success, and message fields.
    public MakeMoveResponse(String channel, boolean success, String message) {
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

class ListGamesRequest extends ApplicationMessage {
    // Constructor that sets the channel field.
    public ListGamesRequest(String channel) {
        super(channel);
    }
}

class ListGamesResponse extends ApplicationMessage {
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


/* This section defines the Router class, which is responsible for managing message 
sending and receiving. The Router class has a channelListeners map that maps channel 
names to listeners that will handle messages sent on those channels
 */

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
    private byte[] serializeMessage(ApplicationMessage message) {
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
    ApplicationMessage deserializeMessage(byte[] serializedMessage) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMessage);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (ApplicationMessage) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

/*
Finally, the main method defines the entry point for the application. It creates a new Router
instance and defines a server-side listener and a client-side listener. It registers these 
listeners with the router and sends a CreateGameRequest and a JoinGameRequest to the server
via the router. The server-side listener processes these requests and sends appropriate
responses back to the client-side listener, which prints the response details to the console.
*/

public class main {
  
    public static void main(String[] args) {
        // Create a new Router instance.
        Router router = new Router();

        // Define a server-side listener that processes requests and sends responses.
        Consumer<byte[]> serverListener = serializedMessage -> {
            // Deserialize the incoming message.
            ApplicationMessage deserializedMessage = router.deserializeMessage(serializedMessage);
            // Handle the message based on its type.
            if (deserializedMessage instanceof CreateGameRequest) {
                System.out.println("Create Game Request received.");
                // Create a response indicating that the game was successfully created.
                CreateGameResponse response = new CreateGameResponse("clientChannel", true, "Game created successfully!");
                // Send the response.
                router.sendMessage(response);
            } else if (deserializedMessage instanceof JoinGameRequest) {
                System.out.println("Join Game Request received.");
                // Create a response indicating that the player successfully joined the game.
                JoinGameResponse response = new JoinGameResponse("clientChannel", true, "Player joined the game!");
                // Send the response.
                router.sendMessage(response);
            }
        };

        // Define a client-side listener that processes responses.
        Consumer<byte[]> clientListener = serializedMessage -> {
            // Deserialize the incoming message.
            ApplicationMessage deserializedMessage = router.deserializeMessage(serializedMessage);
            // Handle the message based on its type.
            if (deserializedMessage instanceof CreateGameResponse) {
                // Cast the message to CreateGameResponse.
                CreateGameResponse response = (CreateGameResponse) deserializedMessage;
                System.out.println("Create Game Response:");
                System.out.println("Success: " + response.isSuccess());
                System.out.println("Message: " + response.getMessage());
            } else if (deserializedMessage instanceof JoinGameResponse) {
                // Cast the message to JoinGameResponse.
                JoinGameResponse response = (JoinGameResponse) deserializedMessage;
                System.out.println("Join Game Response:");
                System.out.println("Success: " + response.isSuccess());
                System.out.println("Message: " + response.getMessage());
            }
        };

        // Register the listeners with the router.
        router.registerChannel("serverChannel", serverListener);
        router.registerChannel("clientChannel", clientListener);

        // Create a CreateGameRequest and send it to the server.
        CreateGameRequest createGameRequest = new CreateGameRequest("serverChannel", "New Game!");
        router.sendMessage(createGameRequest);

        // Create a JoinGameRequest and send it to the server.
        JoinGameRequest joinGameRequest = new JoinGameRequest("serverChannel", "New Game!", "Player1");
        router.sendMessage(joinGameRequest);
    }
}

// OUTPUT
/* 

Create Game Request received.
Create Game Response:
Success: true
Message: Game created successfully!
Join Game Request received.
Join Game Response:
Success: true
Message: Player joined the game!


This output demonstrates the successful communication flow between the client-side 
and server-side components of the system using the Router and ApplicationMessage hierarchy.

Here's a breakdown of what the output shows:

1. The client sends a CreateGameRequest to the server-side listener through the router.


Create Game Request received.


2. The server-side listener receives the request, processes it (in this case, 
just simulating the process), and sends a CreateGameResponse to the client-side listener.


Create Game Response:
Success: true
Message: Game created successfully!


3. The client-side listener receives the response, and the program displays the result, 
indicating that the game creation was successful.


4. The client sends a JoinGameRequest to the server-side listener through the router.


Join Game Request received.


5. The server-side listener receives the request, processes it, and sends a 
JoinGameResponse to the client-side listener.


Join Game Response:
Success: true
Message: Player joined the game!


6. The client-side listener receives the response, and the program displays 
the result, indicating that the player successfully joined the game.


This output showcases the effective communication between client and server components 
using the Router and the ApplicationMessage class hierarchy, including serialization and 
deserialization of the messages while keeping the router agnostic to the specifics of each message type.












 */




















