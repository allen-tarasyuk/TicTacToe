import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public ArrayList<String> channels = new ArrayList<>();
    private Socket socket;
    private DataInputStream bufferedReader;
    private DataOutputStream bufferedWriter;
    private String clientName;

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.bufferedWriter = new DataOutputStream(socket.getOutputStream());
            this.bufferedReader = new DataInputStream(socket.getInputStream());

            byte[] b = new byte[bufferedReader.readInt()];
            bufferedReader.readFully(b, 0, b.length);
            clientName = new String(b);

            try {
                b = new byte[bufferedReader.readInt()];
                bufferedReader.readFully(b, 0, b.length);
                ByteArrayInputStream byteInStream = new ByteArrayInputStream(b);
                ObjectInputStream objectInStream = new ObjectInputStream(byteInStream);
                this.channels = (ArrayList<String>) objectInStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }


            clientHandlers.add(this);

            broadcastMessage(new ApplicationMessage("channel", "Server: " + clientName + " has entered the chat!"));
        }  
        catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    @Override
    public void run() {
        while (socket.isConnected()) {
            try{
                byte[] b = new byte[bufferedReader.readInt()];
                bufferedReader.readFully(b, 0, b.length);
                ApplicationMessage message = Router.deserializeMessage(b);
                broadcastMessage(message);
            } 
            catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
    
    public void broadcastMessage(ApplicationMessage message) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientName.equals(clientName)) {
                    byte[] b = Router.serializeMessage(message);

                    if (clientHandler.channels.contains(message.getChannel())) {
                        clientHandler.bufferedWriter.writeInt(b.length);
                        clientHandler.bufferedWriter.write(b);
                        clientHandler.bufferedWriter.flush();
                    }
                }
            }
            catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    // public void removeClientHandler() {
    //     clientHandlers.remove(this);
    //     broadcastMessage("Server: " + clientName + " has left the chat");
    // }

    public void closeEverything(Socket socket, DataInputStream bufferedReader, DataOutputStream bufferedWriter) {
        // removeClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
