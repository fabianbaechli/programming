package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.*;

public class Controller implements Initializable {
    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Label Label1;
    @FXML
    private TextArea TextArea1;
    @FXML
    private TextArea TextArea2;

    public static String communicationKeyWord = "";

    private String nameOfClient = startPageController.nameOfClient;
    private String ipOfClient = startPageController.ipOfClient;
    private String secretOfClient = startPageController.secretOfClient;

    private String ownIp = startPageController.ownIp;
    private String ownUsername = startPageController.ownUsername;
    private String ownSecret = startPageController.ownSecret;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        //Appends text from text input area to text output area
        TextArea2.setText("Connected To : " + ipOfClient + " / " + nameOfClient);
        //Opens the socket 4433 to incoming messages
        Thread openSocketThread = new Thread() {
            public void run() {
                try {
                    Communication.openSocket(TextArea2, nameOfClient);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        openSocketThread.setDaemon(true);
        openSocketThread.start();

        // Choosing the keyword and generating the hash
        if (secretOfClient.length() > ownSecret.length()) {
            communicationKeyWord = Encryption.sha256(secretOfClient + ownSecret).substring(0, 16);
        } else if (secretOfClient.length() < ownSecret.length()) {
            communicationKeyWord = Encryption.sha256(ownSecret + secretOfClient).substring(0, 16);
        } else if (ownSecret.matches(secretOfClient)) {
            communicationKeyWord = Encryption.sha256(ownSecret + secretOfClient).substring(0, 16);
        } else {
            for (int i = 0; i < ownSecret.length(); i++) {
                if (ownSecret.charAt(i) > secretOfClient.charAt(i)) {
                    communicationKeyWord = Encryption.sha256(ownSecret + secretOfClient).substring(0, 16);
                    break;
                } else if (ownSecret.charAt(i) < secretOfClient.charAt(i)) {
                    communicationKeyWord = Encryption.sha256(secretOfClient + ownSecret).substring(0, 16);
                    break;
                }
            }
        }
        System.out.println("Chosen Keyword for further encryption: " + communicationKeyWord);

        Button1.setOnAction(event -> {
            try {
                //Encrypts the text of the text input area with the keyword which was chosen beforehand
                String sendMessage = Encryption.cipher(true, communicationKeyWord, TextArea1.getText());
                //Sends the encrypted message to the ip of your chat partner
                Communication.sendAMessage(sendMessage, ipOfClient);
                //Checks if you're chatting with yourself
                //If that is the case, only the received messages are displayed in the chat window (not the sent ones)
                if (!ipOfClient.equals(ownIp)) {
                    TextArea2.setText(TextArea2.getText() + "\n" + ownUsername + ": " + TextArea1.getText());
                    TextArea2.positionCaret(TextArea2.getLength());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //Clears text output area
        Button2.setOnAction(event -> {
            TextArea2.setText("Connected To : " + ipOfClient + " / " + nameOfClient);
            TextArea2.positionCaret(TextArea2.getLength());
        });
    }
}
