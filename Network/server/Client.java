import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;


public class Client {

    private Socket socket;
    private DataInputStream bufferedReader;
    private DataOutputStream bufferedWriter;
    private String name;
    private byte[] channels;
    
    public Client (Socket socket, String name, byte[] channels) {
        try {
            this.socket = socket;
            this.bufferedWriter = new DataOutputStream(socket.getOutputStream());
            this.bufferedReader = new DataInputStream(socket.getInputStream());
            this.name = name;
            this.channels = channels;
        }
        catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            // name
            bufferedWriter.writeInt(name.getBytes().length);
            bufferedWriter.write(name.getBytes());
            bufferedWriter.flush();

            //channels
            bufferedWriter.writeInt(channels.length);
            bufferedWriter.write(channels);
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String[] messageToSend = scanner.nextLine().split(" ", 2);
                ApplicationMessage message = new ApplicationMessage(messageToSend[0], messageToSend[1]);
                byte[] s = Router.serializeMessage(message);
                bufferedWriter.writeInt(s.length);
                bufferedWriter.write(s);
                bufferedWriter.flush();
            }
        }
        catch (IOException e ) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run(){
                String msgFromChat;

                while (socket.isConnected()) {
                    try {
                        byte[] b = new byte[bufferedReader.readInt()];
                        bufferedReader.readFully(b, 0, b.length);
                        ApplicationMessage message = Router.deserializeMessage(b);
                        System.out.println(message.getMessage());
                    }
                    catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, DataInputStream bufferedReader, DataOutputStream bufferedWriter) {
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
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting a client...");
        System.out.println("Enter your name for the chat: ");
        String name = scanner.nextLine();
        System.out.println("Enter two channels: ");
        ArrayList<String> channels = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            channels.add(scanner.nextLine());
        }
        try {
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream);
            objectOutStream.writeObject(channels);
            objectOutStream.flush();
            System.out.println("made it");
            byte[] bytes = byteOutStream.toByteArray();
            // System.out.println("Serialized ArrayList to bytes: " + bytes.length + " bytes");
            Socket socket = new Socket("localhost", 1024);
            Client client = new Client(socket, name, bytes);
            client.listenForMessage();
            client.sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
  
        
    }
}
