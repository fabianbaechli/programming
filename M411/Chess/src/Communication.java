
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication {
    private String usernameOfClient = "";
    private String ipOfClient = "";
    private String secretOfClient = "";

    String getUsernameOfClient() {
        return usernameOfClient;
    }

    String getIpOfClient() {
        return ipOfClient;
    }

    String getSecretOfClient() {
        return secretOfClient;
    }

    private Communication(String usernameOfClient, String ipOfClient, String secretOfClient) {
        this.usernameOfClient = usernameOfClient;
        this.ipOfClient = ipOfClient;
        this.secretOfClient = secretOfClient;
    }

    static Communication handshakeSocket() throws Exception {
        int port = 4444;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);
        String usernameOfClient = "";
        String ipOfClient = "";
        String secretOfClient = "";

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
                    System.out.println("received string: " + s);
                    System.out.println("decrypted string to: " + s);

                    String[] infos = s.split("\n");

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
            handshake("Message", startPageController.ipOfClient);
            return new Communication(usernameOfClient, ipOfClient, secretOfClient);
        }
    }

    static void openSocket(TextArea textAreaToSetTextTo, String usernameOfClient) throws Exception {
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
                    System.out.println("decrypted string to: " + s);
                    textAreaToSetTextTo.setText(textAreaToSetTextTo.getText() + "\n" + usernameOfClient + ": " + s);
                    textAreaToSetTextTo.positionCaret(textAreaToSetTextTo.getLength());
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

    static void handshake(String message, String ipOfClient) throws Exception {
        Socket clientSocket = new Socket(ipOfClient, 4444);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(message + '\n');
        System.out.println("Sent: " + message + " to: " + ipOfClient);
        clientSocket.close();
    }

    static void sendAMessage(String message, String ipOfClient) throws Exception {
        Socket clientSocket = new Socket(ipOfClient, 4433);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(message);
        System.out.println("sent " + message + " to " + ipOfClient);
        clientSocket.close();
    }
}