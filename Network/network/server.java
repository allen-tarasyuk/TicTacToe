import java.net.*;
import java.io.*;

public class server {
    public static void main(String[] args)throws IOException{

       ServerSocket ss = new ServerSocket(80);

       // Verifies a connection
       Socket s = ss.accept();
       System.out.println("Client Connected");

       // Reads in Data from the client
       InputStreamReader in = new InputStreamReader(s.getInputStream());
       BufferedReader bf = new BufferedReader(in);

       // Outputs the data on the terminal
       String str = bf.readLine();
       System.out.println("client: " + str);

       // Returns a response
       PrintWriter pr = new PrintWriter(s.getOutputStream());
       pr.println("Yes!");
       pr.flush();

    }
}
