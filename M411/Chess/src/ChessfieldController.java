
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.sun.javafx.beans.IDProperty;
import com.sun.javafx.collections.MappingChange;
import com.sun.javafx.runtime.SystemProperties;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ChessfieldController implements Initializable {
    @FXML
    private Pane a1, a2, a3, a4, a5, a6, a7, a8, b1, b2, b3, b4, b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c8, d1, d2, d3, d4, d5, d6, d7, d8, e1, e2, e3, e4, e5, e6, e7,
            e8, f1, f2, f3, f4, f5, f6, f7, f8, g1, g2, g3, g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h7, h8;
    @FXML
    private Label Label1, Label2, Label3, Label4, Label5, Label6, Label7, Label8, Label9, Label10, Label11, Label12, Label13, Label14, Label15, Label16, Label17, Label18,
            Label19, Label20, Label21, Label22, Label23, Label24, Label25, Label26, Label27, Label28, Label29, Label30, Label31, Label32, Label33, Label34;
    private static Pane fromPane;
    private static Pane toPane;
    private static String colorOfRound = "White";
    private static int roundCount = 0;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        Pane[] allPanes = drawField(false);
        makeMove(allPanes);

    }

    private Pane[] drawField(Boolean blackOrWhite) {
        //Player is Black when true and white when false
        Pane[] allPanes = {a1, a2, a3, a4, a5, a6, a7, a8, b1, b2, b3, b4, b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c8, d1, d2, d3, d4, d5, d6, d7, d8, e1, e2, e3, e4, e5, e6, e7,
                e8, f1, f2, f3, f4, f5, f6, f7, f8, g1, g2, g3, g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h7, h8};
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
            for (int i = 16; i < 32; i++) {
                allLabels[i].setVisible(false);
            }
        } else {
            //Sets the correct raster numbers and chars for black
            for (int i = 0; i < 16; i++) {
                allLabels[i].setVisible(false);
            }
        }
        return allPanes;
    }

    private void makeMove(Pane[] allPanes) {
        int count = 0;
        final int[] fieldNumberSetPane = {0};
        final int[] fieldNumberStartPane = {0};

        for (Pane aPane : allPanes) {
            count++;
            int finalCount = count;
            aPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                //Character selection
                if ((aPane.getId().startsWith("White") && colorOfRound.equals("White")) ||
                        aPane.getId().startsWith("Black") && colorOfRound.equals("Black")) {
                    fromPane = aPane;
                    fieldNumberStartPane[0] = finalCount;
                    //Empty space selection
                } else if (fromPane != null) {
                    toPane = aPane;
                    fieldNumberSetPane[0] = finalCount;
                    if (moveIsPossible(fromPane, fieldNumberStartPane[0], toPane, fieldNumberSetPane[0], allPanes)) {
                        if (colorOfRound.equals("White")) {
                            colorOfRound = "Black";
                            fromPane = null;
                            toPane = null;
                            roundCount++;
                            System.out.println("White moved, it's black's turn");

                            Label33.setText(Label33.getText().replaceAll("White", "Black"));
                            Label34.setText("Round: " + roundCount);
                        } else if (colorOfRound.equals("Black")) {
                            colorOfRound = "White";
                            fromPane = null;
                            toPane = null;
                            roundCount++;
                            System.out.println("Black moved, it's white's turn");

                            Label33.setText(Label33.getText().replaceAll("Black", "White"));
                            Label34.setText("Round: " + roundCount);
                        }
                    }
                }
            });
        }
    }

    private static boolean moveIsPossible(Pane startPane, int from, Pane setPane, int to, Pane[] allFields) {
        System.out.println("From:   " + fromPane.getId());
        System.out.println("To:     " + toPane.getId());
        if ((startPane.getId().startsWith("White") && colorOfRound.equals("White")) ||
                (startPane.getId().startsWith("Black") && colorOfRound.equals("Black"))) {
            //-- PAWN --
            //Pawn Double Move at start of game
            if (startPane.getId().endsWith("Pawn") && roundCount <= 1) {
                if (Math.max(to, from) - Math.min(to, from) == 2 ||
                        Math.max(to, from) - Math.min(to, from) == 1) {
                    setPane.setId(startPane.getId());
                    startPane.setId("");
                    return true;
                }
            } //Pawn Normal Move
            if (startPane.getId().endsWith("Pawn")) {
                if (from - to == -1) {
                    if ((!setPane.getId().startsWith("White")) && (!setPane.getId().startsWith("Black"))) {
                        setPane.setId(startPane.getId());
                        startPane.setId("");
                        return true;
                    }
                } else if (from - to == 7 ||        //Pawn eating move
                        from - to == -9) {
                    if (setPane.getId().startsWith("White") || setPane.getId().startsWith("Black")) {
                        setPane.setId(startPane.getId());
                        startPane.setId("");
                        return true;
                    }
                }
            }
            //-- Tower
            if (startPane.getId().endsWith("Rook")) {
                //If move is on y axis
                if (Math.max(to, from) - Math.min(to, from) >= 1 &&
                        Math.max(to, from) - Math.min(to, from) < 8) {
                    //If move is from top to bottom
                    if (from > to) {
                        //Move on opponent
                        if ((colorOfRound.equals("White") && setPane.getId().startsWith("Black")) ||
                                colorOfRound.equals("Black") && setPane.getId().startsWith("White")) {
                            for (int i = from - 2; i > to - 1; i--) {
                                System.out.println(allFields[i].getId());
                                if (allFields[i].getId().startsWith("White") ||
                                        allFields[i].getId().startsWith("Black")) {
                                    System.out.println("Move on opponent but " + allFields[i].getId() + " in way");
                                    return false;
                                }
                            }
                        }
                        //Move on empty tile
                        else if (!setPane.getId().startsWith("White") && !setPane.getId().startsWith("Black")) {
                            for (int i = from - 2; i > to - 1; i--) {
                                if (allFields[i].getId().startsWith("White") ||
                                        allFields[i].getId().startsWith("Black")) {
                                    System.out.println("Move on empty tile but " + allFields[i].getId() + " in way");
                                    return false;
                                }
                            }
                        }
                    } else if (from < to) {
                        //Move on opponent
                        if ((colorOfRound.equals("White") && setPane.getId().startsWith("Black")) ||
                                colorOfRound.equals("Black") && setPane.getId().startsWith("White")) {
                            for (int i = to - 2; i > from - 2; i--) {
                                if (allFields[i].getId().startsWith("White") && colorOfRound.equals("White") ||
                                        allFields[i].getId().startsWith("Black") && colorOfRound.equals("Black")) {
                                    System.out.println("Move on opponent but " + allFields[i].getId() + " in way");
                                    return false;
                                }
                            }
                        }
                        //Move on empty tile
                        else if (!setPane.getId().startsWith("Black") && !setPane.getId().startsWith("White")) {
                            for (int i = to - 2; i > from - 1; i--) {
                                if (allFields[i].getId().startsWith("White") ||
                                        allFields[i].getId().startsWith("Black")) {
                                    System.out.println("Move on empty tile but " + allFields[i].getId() + " in way");
                                    return false;
                                }
                            }
                        }
                    }
                    setPane.setId(startPane.getId());
                    startPane.setId("");
                    return true;
                }
                //Move on X Axis
                if (Math.max(to, from) - Math.min(to, from) == 8) {
                    setPane.setId(startPane.getId());
                    startPane.setId("");
                    return true;
                } else if (Math.max(to, from) - Math.min(to, from) == 16 ||
                        Math.max(to, from) - Math.min(to, from) == 24 ||
                        Math.max(to, from) - Math.min(to, from) == 32 ||
                        Math.max(to, from) - Math.min(to, from) == 40 ||
                        Math.max(to, from) - Math.min(to, from) == 48 ||
                        Math.max(to, from) - Math.min(to, from) == 56) {
                    
                }
            }
        }
        return false;
    }
}
