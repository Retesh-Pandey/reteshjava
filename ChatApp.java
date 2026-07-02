import java.io.*;
import java.net.*;

public class ChatApp {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("server")) {
            runServer();
        } else {
            runClient();
        }
    }

    // Server mode
    private static void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Client: " + msg);
                System.out.print("You: ");
                String response = console.readLine();
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Client mode
    private static void runClient() {
        try (Socket socket = new Socket("localhost", 1234)) {
            System.out.println("Connected to server!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            String msg;
            while (true) {
                System.out.print("You: ");
                msg = console.readLine();
                out.println(msg);

                String response = in.readLine();
                if (response == null) break;
                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
