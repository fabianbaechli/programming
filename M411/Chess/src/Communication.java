
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication {
    private String ipOfClient = "";
    private long timeOfFirstPackageOfClient;

    String getIpOfClient() {
        return ipOfClient;
    }
    long gettimeOfFirstPackageOfClient() {
        return timeOfFirstPackageOfClient;
    }

    private Communication(String ipOfClient, long timeOfFirstPackageOfClient) {
        this.ipOfClient = ipOfClient;
        this.timeOfFirstPackageOfClient = timeOfFirstPackageOfClient;
    }

    static Communication handshakeSocket() throws Exception {
        int port = 4444;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);
        String ipOfClient = "";
        long timeOfFirstPackageOfClient = 0;

        // repeatedly wait for connections, and process
        while (true) {
            // a "blocking" call which waits until a connection is requested
            Socket clientSocket = serverSocket.accept();
            System.err.println("Accepted connection from client");

            // open up IO streams
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            // waits for data and reads it in until connection dies
            // readLine() blocks until the server receives a new line from client
            String s;
            try {
                while ((s = in.readLine()) != null) {
                    System.out.println("received handshake package: " + s);

                    timeOfFirstPackageOfClient = Long.parseLong(s.split(",")[1]);

                    out.println(s);
                    out.flush();
                }
            } catch (Exception e) {
                System.out.println("-- End Of Communication --");
            }

            // close IO streams, then socket
            System.err.println("Closing connection with client");
            out.close();
            in.close();
            clientSocket.close();
            startPageController.canContinue = true;
            return new Communication(ipOfClient, timeOfFirstPackageOfClient);
        }
    }

    static void openSocket() throws Exception {
        int port = 4433;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);

        // repeatedly wait for connections, and process
        while (true) {
            // a "blocking" call which waits until a connection is requested
            Socket clientSocket = serverSocket.accept();
            System.err.println("Accepted connection from client");

            // open up IO streams
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            // waits for data and reads it in until connection dies
            // readLine() blocks until the server receives a new line from
            // client
            String s;
            try {
                while ((s = in.readLine()) != null) {
                    out.println(s);
                    System.out.println("received string: " + s);

                    String from;
                    String to;
                    String temp[] = s.split(" ");
                    from = temp[0];
                    to = temp[1];
                    ChessfieldController.receivedMove(from, to);

                    out.flush();
                }
            } catch (Exception e) {
                System.out.println("-- End Of Communication --");
            }
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        out.close();
                        in.close();
                        clientSocket.close();
                        System.out.println("The server is shut down!");
                    } catch (IOException e) { /* failed */ }
                }
            });
        }
    }

    static void handshake(String ipOfClient) throws Exception {
        Socket clientSocket = new Socket(ipOfClient, 4444);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        String message = startPageController.ownIp + "," + startPageController.timeOfFirstPackage;
        outToServer.writeBytes(message + "\n");
        System.out.println("Sent: " + message + " to: " + ipOfClient);
        clientSocket.close();
    }

    static void sendAMessage(String message, String ipOfClient) throws Exception {
        Socket clientSocket = new Socket(ipOfClient, 4433);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(message);
        System.out.println("Sent " + message + " to: " + ipOfClient);
        clientSocket.close();
    }
}