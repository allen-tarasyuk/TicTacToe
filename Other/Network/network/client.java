import java.net.*;
import java.io.*;

public class client {
    public static void main(String[] args)throws IOException{

        Socket s = new Socket("localHost", 80);

        // Sends Data to the server (Request)
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("Is it working?");
        pr.flush();

        // Reading Data from the server
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
 
        // Outputs data to the terminal
        String str = bf.readLine();
        System.out.println("server: " + str);

    }
}
