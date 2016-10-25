
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            StackPane page = FXMLLoader.load(Main.class.getResource("Chessfield.fxml"));
            Scene scene = new Scene(page);
            scene.getStylesheets().add("style.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("Chess Game // Fabian Bächli 2016");

            primaryStage.setMinHeight(770);
            primaryStage.setMinWidth(750);
            primaryStage.setMaxHeight(770);
            primaryStage.setMaxWidth(750);

            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
