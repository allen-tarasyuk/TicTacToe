package router;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Router {

    private int socket = 8000;
    private Server server;
    private ArrayList<Room> rooms = new ArrayList<>();

    public Router() throws IOException {
        ServerSocket serverSocket = new ServerSocket(socket);
        this.server = new Server(serverSocket);

    }
}
