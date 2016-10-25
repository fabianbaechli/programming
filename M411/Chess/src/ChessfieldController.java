
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ChessfieldController implements Initializable {
    @FXML
    private Pane Pane1, Pane2, Pane3, Pane4, Pane5, Pane6, Pane7, Pane8, Pane9, Pane10, Pane11, Pane12, Pane13, Pane14, Pane15, Pane16, Pane17, Pane18, Pane19, Pane20,
            Pane21, Pane22, Pane23, Pane24, Pane25, Pane26, Pane27, Pane28, Pane29, Pane30, Pane31, Pane32, Pane33, Pane34, Pane35, Pane36, Pane37, Pane38, Pane39, Pane40,
            Pane41, Pane42, Pane43, Pane44, Pane45, Pane46, Pane47, Pane48, Pane49, Pane51, Pane52, Pane53, Pane54, Pane55, Pane56, Pane57, Pane58, Pane59, Pane60, Pane61,
            Pane62, Pane63, Pane64, Pane65;
    @FXML
    private Label Label1, Label2, Label3, Label4, Label5, Label6, Label7, Label8, Label9, Label10, Label11, Label12, Label13, Label14, Label15, Label16, Label17, Label18,
            Label19, Label20, Label21, Label22, Label23, Label24, Label25, Label26, Label27, Label28, Label29, Label30, Label31, Label32;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        drawField(true);
    }

    private void drawField(Boolean blackOrWhite) {
        //Player is Black when true and white when false
        Pane[] allPanes = {Pane1, Pane2, Pane3, Pane4, Pane5, Pane6, Pane7, Pane8, Pane9, Pane10, Pane11, Pane12, Pane13, Pane14, Pane15, Pane16, Pane17, Pane18, Pane19, Pane20,
                Pane21, Pane22, Pane23, Pane24, Pane25, Pane26, Pane27, Pane28, Pane29, Pane30, Pane31, Pane32, Pane33, Pane34, Pane35, Pane36, Pane37, Pane38, Pane39, Pane40,
                Pane41, Pane42, Pane43, Pane44, Pane45, Pane46, Pane47, Pane48, Pane49, Pane51, Pane52, Pane53, Pane54, Pane55, Pane56, Pane57, Pane58, Pane59, Pane60, Pane61,
                Pane62, Pane63, Pane64};
        Label[] allLabels = {Label1, Label2, Label3, Label4, Label5, Label6, Label7, Label8, Label9, Label10, Label11, Label12, Label13, Label14, Label15, Label16, Label17, Label18,
                Label19, Label20, Label21, Label22, Label23, Label24, Label25, Label26, Label27, Label28, Label29, Label30, Label31, Label32};

        System.out.println(Arrays.toString(allPanes));
        System.out.println(Arrays.toString(allLabels));
        //If the player is white
        if (!blackOrWhite) {
            //Sets the
            for (Pane aPane : allPanes) {
                if (aPane.getId().startsWith("Black-King")) {
                    String replaceString = aPane.getId().replaceAll("Black-King", "White-Queen");
                    aPane.setId(replaceString);
                } else if (aPane.getId().startsWith("Black-Queen")) {
                    String replaceString = aPane.getId().replaceAll("Black-Queen", "White-King");
                    aPane.setId(replaceString);
                } else if (aPane.getId().startsWith("White-King")) {
                    String replaceString = aPane.getId().replaceAll("White-King", "Black-Queen");
                    aPane.setId(replaceString);
                } else if (aPane.getId().startsWith("White-Queen")) {
                    String replaceString = aPane.getId().replaceAll("White-Queen", "Black-King");
                    aPane.setId(replaceString);
                } else {
                    if (aPane.getId().startsWith("White")) {
                        String replaceString = aPane.getId().replaceAll("White", "Black");
                        aPane.setId(replaceString);
                    } else if (aPane.getId().startsWith("Black")) {
                        String replaceString = aPane.getId().replaceAll("Black", "White");
                        aPane.setId(replaceString);
                    }
                }
            }
            //Sets the correct raster numbers and chars for white
            for (int i = 16; i < 32; i++){
                allLabels[i].setVisible(false);
            }
        } else {
            //Sets the correct raster numbers and chars for black
            for (int i = 0; i < 16; i++){
                allLabels[i].setVisible(false);
            }
        }
        //Sets a Mouse Click Handler for every tile
        for (Pane aPane : allPanes) {
            aPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                System.out.println("Tile " + aPane.getId() + " pressed");
            });
        }
    }
}
