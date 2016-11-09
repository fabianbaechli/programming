
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            /*
            StackPane page = FXMLLoader.load(Main.class.getResource("Chessfield.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);

            primaryStage.setTitle("Chess Game // Fabian Bächli 2016");
            primaryStage.setMinHeight(770);
            primaryStage.setMinWidth(950);
            primaryStage.setMaxHeight(770);
            primaryStage.setMaxWidth(950);
            primaryStage.show();
            */
            StackPane page = FXMLLoader.load(Main.class.getResource("ConnectWindow.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Connection Window");

            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
