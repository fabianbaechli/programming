
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
    private Button button1;
    @FXML
    private Label Label1;

    static String ipOfClient = "";
    static long timeOfFirstPackageOfClient;

    static long timeOfFirstPackage;
    static String ownIp = "";
    static Boolean canContinue = false;


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        button1.setOnAction(event -> {
            if (textField1.getText().equals("")) {
                Label1.setText("Set Ip");
            } else {
                try {
                    ownIp = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

                //Opens Port 4444. Receives handshake package and sets 3 variables according to infos of said package
                Thread openSocketThread = new Thread() {
                    public void run() {
                        try {
                            Communication communication = Communication.handshakeSocket();
                            ipOfClient = textField1.getText();
                            timeOfFirstPackageOfClient = communication.gettimeOfFirstPackageOfClient();
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

                int count = 0;
                while (!canContinue) {
                    try {
                        if (count == 0) {
                            timeOfFirstPackage = System.currentTimeMillis();
                        }
                        Thread.sleep(1000);
                        Communication.handshake(textField1.getText());
                        count++;
                    } catch (Exception e) {
                        System.out.println("Package not delivered");
                    }
                }

                //Shows the actual communication window
                Parent root;
                try {
                    root = FXMLLoader.load(Main.class.getResource("Chessfield.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Chess Application // 2016 Fabian Bächli");
                    stage.setScene(new Scene(root));
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