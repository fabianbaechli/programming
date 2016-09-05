package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;



public class Controller
        implements Initializable {

    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;

    @FXML private Label label1;
    @FXML private TextArea textArea1;

    @Override public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        // Code Goes Here
        button1.setOnAction(event -> {
            label1.setText(textArea1.getText());
        });
        button2.setOnAction(event -> {
            label1.setText("2");
        });
        button3.setOnAction(event -> {
            label1.setText("3");
        });
    }
}