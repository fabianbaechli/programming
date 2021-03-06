import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


public class ChessfieldController implements Initializable {
    @FXML
    private Pane a1, a2, a3, a4, a5, a6, a7, a8, b1, b2, b3, b4,
            b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c8, d1,
            d2, d3, d4, d5, d6, d7, d8, e1, e2, e3, e4, e5, e6,
            e7, e8, f1, f2, f3, f4, f5, f6, f7, f8, g1, g2, g3,
            g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h7, h8;

    @FXML
    private Label Label1, Label2, Label3, Label4, Label5, Label6,
            Label7, Label8, Label9, Label10, Label11, Label12,
            Label13, Label14, Label15, Label16, Label17, Label18,
            Label19, Label20, Label21, Label22, Label23, Label24,
            Label25, Label26, Label27, Label28, Label29, Label30,
            Label31, Label32, Label33, Label34;

    private static Pane fromPane;
    private static Pane toPane;
    private static Pane[] allThePanes;

    private static String colorOfRound = "White";
    private static String colorOfPlayer;
    private static String fromColor;
    private static String toColor;
    private static String fromChar;
    private static String toChar;

    private static int roundCount = 0;
    private static int fromNumber;
    private static int toNumber;

    private static Boolean playsWithItself = false;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //Opens the socket 4433 to incoming messages
        Thread openSocketThread = new Thread() {
            public void run() {
                try {
                    Communication.openSocket();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        openSocketThread.setDaemon(true);
        openSocketThread.start();

        if (startPageController.timeOfFirstPackage > startPageController.timeOfFirstPackageOfClient) {
            colorOfPlayer = "White";
        } else if ((startPageController.timeOfFirstPackage < startPageController.timeOfFirstPackageOfClient)) {
            colorOfPlayer = "Black";
        } else if (startPageController.timeOfFirstPackage == startPageController.timeOfFirstPackageOfClient) {
            playsWithItself = true;
            colorOfPlayer = "White";
        }

        allThePanes = firstDraw();
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

        Label[] allLabels = {Label1, Label2, Label3, Label4,
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
            Character setChar = 'a';
            for (int i = 64; i > 0; i--) {
                count++;
                if (i == 57 ||
                        i == 49 ||
                        i == 41 ||
                        i == 33 ||
                        i == 25 ||
                        i == 17 ||
                        i == 9) {
                    count = 0;
                    setChar = (char)(setChar + 1);
                    System.out.println((int)setChar);
                }
                if (!allPanes[i - 1].getId().contains("White") &&
                        !allPanes[i - 1].getId().contains("Black")) {
                    allPanes[i - 1].setId(Character.toString(setChar) + count);
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
                    fromPane.setEffect(new GaussianBlur(5));
                } else if (fromPane != null) {
                    toPane = aPane;
                    fromPane.setEffect(null);
                    makeMove(fromPane, toPane, allPanes, true);
                    fromPane = null;
                    toPane = null;
                }
            });
        }
        setImages(allPanes);
        return allPanes;
    }

    private void makeMove(Pane from, Pane to, Pane[] allPanes, Boolean sendMove) {

        if (moveIsPossible(from, to, allPanes) || !sendMove) {
            System.out.println("from: " + from.getId() + " to: " + to.getId());
            if (sendMove) {
                try {
                    Communication.sendAMessage(from.getId() + " " + to.getId(), startPageController.ipOfClient);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
                Label33.setText("White's turn");
                if (playsWithItself) {
                    colorOfPlayer = "White";
                }
            } else {
                colorOfRound = "Black";
                Label33.setText("Black's turn");
                if (playsWithItself) {
                    colorOfPlayer = "Black";
                }
            }
            roundCount++;
            Label34.setText(Label34.getText().split(" ")[0] + " " + roundCount);
            System.out.println("Made move");
        }
    }

    private Boolean moveIsPossible(Pane from, Pane to, Pane[] allPanes) {
        fromColor = from.getId().split("-")[1];
        fromNumber = Character.getNumericValue(from.getId().charAt(1));
        toNumber = Character.getNumericValue(to.getId().charAt(1));
        fromChar = String.valueOf(from.getId().charAt(0));
        toChar = String.valueOf(to.getId().charAt(0));
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
                    if (from.getId().contains("Pawn") && pawnLogic()) {
                        return true;
                    } else if (from.getId().contains("Rook") && rookLogic(from, to, allPanes)) {
                        return true;
                    } else if (from.getId().contains("Knight") && knightLogic()) {
                        return true;
                    } else if (from.getId().contains("Checkmate") && bishopLogic(from, to, allPanes)) {
                        return true;
                    } else if (from.getId().contains("Queen") && queenLogic(from, to, allPanes)) {
                        return true;
                    } else if (from.getId().contains("King") && kingLogic()) {
                        return true;
                    }
                } else {
                    System.out.println("You cannot eat one of your own");
                }
            } else {
                System.out.println("You can't move this figure");
            }
        } else {
            System.out.println("Your partner is supposed to make a move");
        }
        return false;
    }

    private static Boolean pawnLogic() {
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
                    fromNumber == 2) {
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
                    fromNumber == 7) {
                if (fromChar.equals(toChar) &&
                        toColor.equals("noColor")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Boolean rookLogic(Pane from, Pane to, Pane[] allPanes) {
        int fromPosition = 0;
        int toPosition = 0;

        for (int i = 0; i < allPanes.length; i++) {
            if (allPanes[i].getId().equals(from.getId())) {
                fromPosition = i;
            } else if (allPanes[i].getId().equals(to.getId())) {
                toPosition = i;
            }
        }
        int max = Math.max(fromPosition, toPosition);
        int min = Math.min(fromPosition, toPosition);

        if (fromChar.equals(toChar)) {
            for (int i = min + 1; i <= max - 1; i++) {
                System.out.println("Checked Panel: " + allPanes[i].getId());
                if (allPanes[i].getId().contains("White") ||
                        allPanes[i].getId().contains("Black")) {
                    System.out.println(allPanes[i].getId() + " In way");
                    return false;
                }
            }
            return true;
        } else if (fromNumber == toNumber) {
            for (int i = min + 8; i <= max - 8; i += 8) {
                System.out.println("Checked Panel: " + allPanes[i].getId());
                if (allPanes[i].getId().contains("White") ||
                        allPanes[i].getId().contains("Black")) {
                    System.out.println(allPanes[i].getId() + " In way");
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static Boolean knightLogic() {
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
        return false;
    }

    private static Boolean bishopLogic(Pane from, Pane to, Pane[] allPanes) {
        int charDif = Math.max((int) fromChar.charAt(0), (int) toChar.charAt(0)) - Math.min((int) fromChar.charAt(0), (int) toChar.charAt(0));
        int numberDif = Math.max(fromNumber, toNumber) - Math.min(fromNumber, toNumber);
        if (charDif == numberDif) {
            for (int fromNumberInArray = 0; fromNumberInArray < allPanes.length; fromNumberInArray++) {
                if (allPanes[fromNumberInArray].getId().equals(from.getId())) {
                    for (int toNumberInArray = 0; toNumberInArray < allPanes.length; toNumberInArray++) {
                        if (allPanes[toNumberInArray].getId().equals(to.getId())) {

                            int max = Math.max(fromNumberInArray, toNumberInArray);
                            int min = Math.min(fromNumberInArray, toNumberInArray);
                            double seven = 7;
                            double nine = 9;
                            double sevenResult = (max - min) / seven;
                            double nineResult = (max - min) / nine;

                            if ((sevenResult % 1 == 0)) {
                                for (int i = min + 7; i < max; i += 7) {
                                    if (allPanes[i].getId().contains("Black") || allPanes[i].getId().contains("White")) {
                                        System.out.println("Couldn't make move because: " + allPanes[i].getId());
                                        return false;
                                    }
                                }
                            } else if ((nineResult % 1 == 0)) {
                                for (int i = min + 9; i < max; i += 9) {
                                    if (allPanes[i].getId().contains("Black") || allPanes[i].getId().contains("White")) {
                                        System.out.println("Couldn't make move because: " + allPanes[i].getId());
                                        return false;
                                    }
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        } else {
            System.out.println("Not a valid move");
            return false;
        }
        return false;
    }

    private static Boolean queenLogic(Pane from, Pane to, Pane[] allPanes) {
        if (fromChar.equals(toChar) || fromNumber == toNumber) {
            if (rookLogic(from, to, allPanes)) {
                return true;
            }
        } else {
            if (bishopLogic(from, to, allPanes)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean kingLogic() {
        int charDif = Math.max((int) fromChar.charAt(0), (int) toChar.charAt(0)) - Math.min((int) fromChar.charAt(0), (int) toChar.charAt(0));
        int numberDif = Math.max(fromNumber, toNumber) - Math.min(fromNumber, toNumber);

        return charDif <= 1 && numberDif <= 1;
    }

    private void setImages(Pane[] allPanes) {
        for (Pane aPane : allPanes) {
            if (aPane.getId().contains("Black-Pawn")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/pawn-black.png")));
            } else if (aPane.getId().contains("White-Pawn")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/pawn-white.png")));
            } else if (aPane.getId().contains("Black-Checkmate")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/checkmate-black.png")));
            } else if (aPane.getId().contains("White-Checkmate")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/checkmate-white.png")));
            } else if (aPane.getId().contains("Black-King")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/king-black.png")));
            } else if (aPane.getId().contains("White-King")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/king-white.png")));
            } else if (aPane.getId().contains("Black-Knight")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/knight-black.png")));
            } else if (aPane.getId().contains("White-Knight")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/knight-white.png")));
            } else if (aPane.getId().contains("Black-Queen")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/queen-black.png")));
            } else if (aPane.getId().contains("White-Queen")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/queen-white.png")));
            } else if (aPane.getId().contains("Black-Rook")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/rook-black.png")));
            } else if (aPane.getId().contains("White-Rook")) {
                aPane.setBackground(new Background(backgroundImageBuilder("icon/rook-white.png")));
            } else {
                aPane.setBackground(null);
            }
        }
    }

    private BackgroundImage backgroundImageBuilder(String path) {
        return new BackgroundImage(new Image(this.getClass().getResourceAsStream(path), 65, 65, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
    }

    static void receivedMove(String from, String to) {
        Pane aFromPane = fromPane;
        Pane aToPane = toPane;

        for (Pane aPane : allThePanes) {
            if (aPane.getId().equals(from)) {
                aFromPane = aPane;
            } else if (aPane.getId().equals(to)) {
                aToPane = aPane;
            }
        }

        if (aFromPane != null && aToPane != null) {
            ChessfieldController chessfieldController = new ChessfieldController();
            chessfieldController.makeMove(aFromPane, aToPane, allThePanes, false);
        }
    }
}