
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class startPageController implements Initializable {

    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private Button button1;
    @FXML
    private Label Label1;

    static String nameOfClient = "";
    static String ipOfClient = "";
    static String secretOfClient = "";

    static String ownUsername = "";
    static String ownIp = "";
    static String ownSecret = "";
    static Boolean canContinue = false;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        button1.setOnAction(event -> {

            //Collecting of local attributes
            ownUsername = textField2.getText();
            try {
                ownIp = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            ownSecret = passwordField1.getText();

            //Checks if all 3 fields are filled in
            if (passwordField1.getText().equals("") || textField1.getText().equals("") || textField2.getText().equals("")) {
                Label1.setText("Fill in fields");
            } else {
                //Opens Port 4444. Receives handshake package and sets 3 variables according to infos of said package
                Thread openSocketThread = new Thread() {
                    public void run() {
                        try {
                            Communication communication = Communication.handshakeSocket();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                //So that the thread also ends when application closes
                openSocketThread.setDaemon(true);
                openSocketThread.start();

                //Sends a handshake package every second
                //Until canContinue is true
                //canContinue is set to true, as soon as a handshake package is received
                while (!canContinue) {
                    try {
                        Thread.sleep(1000);
                        Communication.handshake("Message", "ipOfClient");
                    } catch (Exception e) {
                        System.out.println("Package not delivered");
                        Label1.setText("Package not delivered");
                    }
                }

                //Shows the actual communication window
                Parent root;
                try {
                    root = FXMLLoader.load(Main.class.getResource("Chessfield.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Chess Game // Fabian Bächli 2016");
                    stage.setMinHeight(770);
                    stage.setMinWidth(950);
                    stage.setMaxHeight(770);
                    stage.setMaxWidth(950);
                    stage.show();

                    //hides this current window
                    ((Node) (event.getSource())).getScene().getWindow().hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}