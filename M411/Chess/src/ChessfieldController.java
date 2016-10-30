
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.jetbrains.annotations.Contract;

public class ChessfieldController implements Initializable {
    @FXML
    private Pane a1, a2, a3, a4, a5, a6, a7, a8, b1, b2, b3, b4, b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c8, d1, d2, d3, d4, d5, d6, d7, d8, e1, e2, e3, e4, e5, e6, e7, e8, f1, f2, f3, f4, f5, f6, f7, f8, g1, g2, g3, g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h7, h8;

    @FXML
    private Label Label1, Label2, Label3, Label4, Label5, Label6, Label7, Label8, Label9, Label10, Label11, Label12, Label13, Label14, Label15, Label16, Label17, Label18, Label19, Label20, Label21, Label22, Label23, Label24, Label25, Label26, Label27, Label28, Label29, Label30, Label31, Label32;

    private Pane fromPane;
    private Pane toPane;

    private String colorOfRound;
    private String colorOfPlayer;

    private int roundCount = 0;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        colorOfPlayer = "White";
        colorOfRound = "White";
        Pane[] allPanes = firstDraw();
    }

    private Pane[] firstDraw() {
        Pane[] allPanes = {a1, a2, a3, a4, a5, a6, a7, a8,
                b1, b2, b3, b4, b5, b6, b7, b8,
                c1, c2, c3, c4, c5, c6, c7, c8,
                d1, d2, d3, d4, d5, d6, d7, d8,
                e1, e2, e3, e4, e5, e6, e7, e8,
                f1, f2, f3, f4, f5, f6, f7, f8,
                g1, g2, g3, g4, g5, g6, g7, g8,
                h1, h2, h3, h4, h5, h6, h7, h8
        };

        Label[] allLabels = {
                Label1, Label2, Label3, Label4,
                Label5, Label6, Label7, Label8, Label9,
                Label10, Label11, Label12, Label13,
                Label14, Label15, Label16, Label17,
                Label18, Label19, Label20, Label21,
                Label22, Label23, Label24, Label25,
                Label26, Label27, Label28, Label29,
                Label30, Label31, Label32
        };

        if (colorOfPlayer.equals("White")) {
            //The Board is designed from the view of a player who plays as black
            //This changes the positions of the queens and kings
            for (Pane aPane : allPanes) {
                if (aPane.getId().contains("Black-King")) {
                    String replaceString = aPane.getId().replaceAll("Black-King", "White-Queen");
                    aPane.setId(replaceString);
                } else if (aPane.getId().contains("Black-Queen")) {
                    String replaceString = aPane.getId().replaceAll("Black-Queen", "White-King");
                    aPane.setId(replaceString);
                } else if (aPane.getId().contains("White-King")) {
                    String replaceString = aPane.getId().replaceAll("White-King", "Black-Queen");
                    aPane.setId(replaceString);
                } else if (aPane.getId().contains("White-Queen")) {
                    String replaceString = aPane.getId().replaceAll("White-Queen", "Black-King");
                    aPane.setId(replaceString);
                } else {
                    //This changes white figures to black ones and the other way around
                    if (aPane.getId().contains("White")) {
                        String replaceString = aPane.getId().replaceAll("White", "Black");
                        aPane.setId(replaceString);
                    } else if (aPane.getId().contains("Black")) {
                        String replaceString = aPane.getId().replaceAll("Black", "White");
                        aPane.setId(replaceString);
                    }
                }
            }
            //This sets the correct id's
            for (Pane aPane : allPanes) {
                if (aPane.getId().contains("White") || aPane.getId().contains("Black")) {
                    if (aPane.getId().split("-")[0].contains("a")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("a", "h");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("b")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("b", "g");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("c")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("c", "f");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("d")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("d", "e");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("e")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("e", "d");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("f")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("f", "c");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("g")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("g", "b");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("h")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("h", "a");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    }
                    if (aPane.getId().split("-")[0].contains("1")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("1", "8");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("2")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("2", "7");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("3")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("3", "6");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("3")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("3", "6");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("4")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("4", "5");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("5")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("5", "4");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("6")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("6", "3");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("7")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("7", "2");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    } else if (aPane.getId().split("-")[0].contains("8")) {
                        String replaceString = aPane.getId().split("-")[0].replaceAll("8", "1");
                        aPane.setId(replaceString + "-" + aPane.getId().split("-")[1] + "-" + aPane.getId().split("-")[2]);
                    }
                }
            }
            for (int i = 16; i < 32; i++) {
                allLabels[i].setVisible(false);
            }

        } else {
            int count = 0;
            for (int i = 8; i > 0; i--) {
                count++;
                if (!allPanes[i - 1].getId().contains("White") &&
                        !allPanes[i - 1].getId().contains("Black")) {
                    allPanes[i - 1].setId("h" + count);
                }
            }
            count = 0;
            for (int i = 16; i > 8; i--) {
                count++;
                if (!allPanes[i - 1].getId().contains("White") &&
                        !allPanes[i - 1].getId().contains("Black")) {
                    allPanes[i - 1].setId("g" + count);
                }
            }
            count = 0;
            for (int i = 24; i > 16; i--) {
                count++;
                if (!allPanes[i - 1].getId().contains("White") &&
                        !allPanes[i - 1].getId().contains("Black")) {
                    allPanes[i - 1].setId("f" + count);
                }
            }
            count = 0;
            for (int i = 32; i > 24; i--) {
                count++;
                if (!allPanes[i - 1].getId().contains("White") &&
                        !allPanes[i - 1].getId().contains("Black")) {
                    allPanes[i - 1].setId("e" + count);
                }
            }
            count = 0;
            for (int i = 40; i > 32; i--) {
                count++;
                if (!allPanes[i - 1].getId().contains("White") &&
                        !allPanes[i - 1].getId().contains("Black")) {
                    allPanes[i - 1].setId("d" + count);
                }
            }
            count = 0;
            for (int i = 48; i > 40; i--) {
                count++;
                if (!allPanes[i - 1].getId().contains("White") &&
                        !allPanes[i - 1].getId().contains("Black")) {
                    allPanes[i - 1].setId("c" + count);
                }
            }
            count = 0;
            for (int i = 56; i > 48; i--) {
                count++;
                if (!allPanes[i - 1].getId().contains("White") &&
                        !allPanes[i - 1].getId().contains("Black")) {
                    allPanes[i - 1].setId("b" + count);
                }
            }
            count = 0;
            for (int i = 64; i > 56; i--) {
                count++;
                if (!allPanes[i - 1].getId().contains("White") &&
                        !allPanes[i - 1].getId().contains("Black")) {
                    allPanes[i - 1].setId("a" + count);
                }
            }
            //Sets the correct raster numbers and chars for black
            for (int i = 0; i < 16; i++) {
                allLabels[i].setVisible(false);
            }
        }

        //Sets a Mouse Click Handler for every tile
        for (Pane aPane : allPanes) {
            aPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                System.out.println(aPane.getId());
                if ((aPane.getId().contains("White") ||
                        aPane.getId().contains("Black")) &&
                        fromPane == null) {
                    fromPane = aPane;
                } else if (fromPane != null) {
                    toPane = aPane;
                    makeMove(fromPane, toPane, allPanes);
                    fromPane = null;
                    toPane = null;
                }
            });
        }
        setImages(allPanes);
        return allPanes;
    }

    private void makeMove(Pane from, Pane to, Pane[] allPanes) {
        if (moveIsPossible(from, to, allPanes)) {
            System.out.println("from: " + from.getId() + " to: " + to.getId());
            if (to.getId().contains("-")) {
                to.setId(to.getId().split("-")[0] + "-" + from.getId().split("-")[1] + "-" + from.getId().split("-")[2]);
                from.setId(from.getId().split("-")[0]);
                setImages(allPanes);
            } else {
                to.setId(to.getId() + "-" + from.getId().split("-")[1] + "-" + from.getId().split("-")[2]);
                from.setId(from.getId().split("-")[0]);
                setImages(allPanes);
            }
            if (colorOfRound.equals("Black")) {
                colorOfRound = "White";
                //Remove later, boi
                colorOfPlayer = "White";
            } else {
                colorOfRound = "Black";
                //This also
                colorOfPlayer = "Black";
            }
            roundCount++;
        }
    }

    private Boolean moveIsPossible(Pane from, Pane to, Pane[] allPanes) {
        String fromColor = from.getId().split("-")[1];
        String toColor;
        int fromNumber = Character.getNumericValue(from.getId().charAt(1));
        int toNumber = Character.getNumericValue(to.getId().charAt(1));
        String fromChar = String.valueOf(from.getId().charAt(0));
        String toChar = String.valueOf(to.getId().charAt(0));
        if (to.getId().contains("White") || to.getId().contains("Black")) {
            toColor = to.getId().split("-")[1];
        } else {
            toColor = "noColor";
        }

        if (colorOfPlayer.matches(colorOfRound)) {
            if (fromColor.equals(colorOfPlayer)) {
                //Have to change this in future for rochade
                if (!fromColor.matches(toColor)) {
                    //Logic for pawn
                    if (from.getId().contains("Pawn")) {
                        if (fromColor.equals("White")) {
                            if (fromNumber + 1 == toNumber &&
                                    roundCount > 1) {
                                if (fromChar.equals(toChar) &&
                                        toColor.equals("noColor")) {
                                    return true;
                                } else if (((int) fromChar.charAt(0) + 1 == (int) toChar.charAt(0) ||
                                        (int) fromChar.charAt(0) - 1 == (int) toChar.charAt(0)) &&
                                        toColor.equals("Black")) {
                                    return true;
                                }
                            } else if ((fromNumber + 1 == toNumber ||
                                    fromNumber + 2 == toNumber) &&
                                    roundCount < 2) {
                                if (fromChar.equals(toChar) &&
                                        toColor.equals("noColor")) {
                                    return true;
                                }
                            }
                        } else if (fromColor.equals("Black")) {
                            if (fromNumber - 1 == toNumber &&
                                    roundCount > 1) {
                                if (fromChar.equals(toChar) &&
                                        toColor.equals("noColor")) {
                                    return true;
                                } else if (((int) fromChar.charAt(0) + 1 == (int) toChar.charAt(0) ||
                                        (int) fromChar.charAt(0) - 1 == (int) toChar.charAt(0)) &&
                                        toColor.equals("White")) {
                                    return true;
                                }
                            } else if ((fromNumber - 1 == toNumber ||
                                    fromNumber - 2 == toNumber) &&
                                    roundCount < 2) {
                                if (fromChar.equals(toChar) &&
                                        toColor.equals("noColor")) {
                                    return true;
                                }
                            }
                        }
                    } else if (from.getId().contains("Rook")) {
                        int fromPosition = 0;
                        int toPosition = 0;

                        for (int i = 0; i < allPanes.length; i++) {
                            if (allPanes[i].getId().equals(from.getId())) {
                                fromPosition = i;
                            } else if (allPanes[i].getId().equals(to.getId())) {
                                toPosition = i;
                            }
                        }
                        if (fromChar.equals(toChar)) {
                            if (fromPosition < toPosition) {
                                for (int i = fromPosition + 1; i <= toPosition - 1; i++) {
                                    if (allPanes[i].getId().contains("White") ||
                                            allPanes[i].getId().contains("Black")) {
                                        System.out.println(allPanes[i].getId() + " In way");
                                        return false;
                                    }
                                }
                                return true;
                            } else if (fromPosition > toPosition) {
                                for (int i = toPosition + 1; i <= fromPosition - 1; i++) {
                                    if (allPanes[i].getId().contains("White") ||
                                            allPanes[i].getId().contains("Black")) {
                                        System.out.println(allPanes[i].getId() + " In way");
                                        return false;
                                    }
                                }
                            }
                            return true;
                        } else if (fromNumber == toNumber) {
                            if (fromPosition < toPosition) {
                                for (int i = fromPosition + 8; i <= toPosition - 8; i += 8) {
                                    if (allPanes[i].getId().contains("White") ||
                                            allPanes[i].getId().contains("Black")) {
                                        System.out.println(allPanes[i].getId() + " In way");
                                        return false;
                                    }
                                }
                                return true;
                            } else if (fromPosition > toPosition) {
                                for (int i = toPosition + 8; i <= fromPosition - 8; i += 8) {
                                    if (allPanes[i].getId().contains("White") ||
                                            allPanes[i].getId().contains("Black")) {
                                        System.out.println(allPanes[i].getId() + " In way");
                                        return false;
                                    }
                                }
                            }
                            return true;
                        }
                    } else if (from.getId().contains("Knight")) {
                        if ((fromChar.charAt(0) + 2 == toChar.charAt(0) ||
                                toChar.charAt(0) + 2 == fromChar.charAt(0)) &&
                                (fromNumber + 1 == toNumber ||
                                toNumber + 1 == fromNumber)) {
                            return true;
                        } else if ((fromChar.charAt(0) + 1 == toChar.charAt(0) ||
                                toChar.charAt(0) + 1 == fromChar.charAt(0)) &&
                                (fromNumber + 2 == toNumber ||
                                toNumber + 2 == fromNumber)) {
                            return true;
                        }
                    }
                } else {
                    System.out.println("You cannot eat one of your own, traitor");
                }
            } else {
                System.out.println("You can't move this figure");
            }
        } else {
            System.out.println("Your partner is supposed to make a move");
        }
        return false;
    }

    private void setImages(Pane[] allPanes) {
        for (Pane aPane : allPanes) {
            if (aPane.getId().contains("Black-Pawn")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/pawn-black.png")));
            } else if (aPane.getId().contains("White-Pawn")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/pawn-white.png")));
            } else if (aPane.getId().contains("Black-Checkmate")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/checkmate-black.png")));
            } else if (aPane.getId().contains("White-Checkmate")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/checkmate-white.png")));
            } else if (aPane.getId().contains("Black-King")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/king-black.png")));
            } else if (aPane.getId().contains("White-King")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/king-white.png")));
            } else if (aPane.getId().contains("Black-Knight")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/knight-black.png")));
            } else if (aPane.getId().contains("White-Knight")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/knight-white.png")));
            } else if (aPane.getId().contains("Black-Queen")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/queen-black.png")));
            } else if (aPane.getId().contains("White-Queen")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/queen-white.png")));
            } else if (aPane.getId().contains("Black-Rook")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/rook-black.png")));
            } else if (aPane.getId().contains("White-Rook")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icons/rook-white.png")));
            } else {
                aPane.setBackground(null);
            }
        }
    }

    @Contract("_ -> !null")
    private BackgroundImage backgroundImageBuilder(String path) {
        return new BackgroundImage(new Image(path, 65, 65, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
    }
}