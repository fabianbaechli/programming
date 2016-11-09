
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


        });
    }
}