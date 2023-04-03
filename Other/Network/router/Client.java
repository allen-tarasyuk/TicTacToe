package router;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    private int roomID;

    public Client(String name) {
        try {
            this.socket = new Socket("localhost", 8000);
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = name;
            this.roomID = -1;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void createRoom(String roomName) {
        JSONObject jsonMsg = new JSONObject();

        jsonMsg.put("action", "createRoom");
        jsonMsg.put("roomName", roomName);

        sendMessage(jsonMsg);
    }

    public void joinRoom(String roomName, int roomID) {

        JSONObject jsonMsg = new JSONObject();

        jsonMsg.put("action", "joinRoom");
        jsonMsg.put("roomName", roomName);
        jsonMsg.put("roomID", roomID);

        sendMessage(jsonMsg);

    }

    // public void makeMove() {

    // }

    public void sendMessage(JSONObject jsonMsg) {
        String stringMsg = jsonMsg.toString();

        try {
            while (socket.isConnected()) {
                bufferedWriter.write(stringMsg);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void handleMessage(String msg) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject jsonMsg = (JSONObject) parser.parse(msg);

        String action = jsonMsg.get("action").toString();

        switch (action) {
            // case "createRoom":
            // break;
            default:
                break;
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromChat;

                while (socket.isConnected()) {
                    try {
                        msgFromChat = bufferedReader.readLine();
                        handleMessage(msgFromChat);
                    } catch (Exception e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static void main(String[] args) throws UnknownHostException,
    // IOException {

    // Scanner scanner = new Scanner(System.in);
    // System.out.println("Starting a client...");
    // System.out.println("Enter your name for the chat: ");
    // String name = scanner.nextLine();
    // Socket socket = new Socket("localhost", 8000);
    // Client client = new Client(socket, name);
    // client.listenForMessage();
    // client.sendMessage();

    // }
}
