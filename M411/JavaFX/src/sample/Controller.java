package sample;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class Controller
        implements Initializable {

    @FXML private Button button1;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private TextArea textArea1;

    @Override public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        // Code Goes Here
        button1.setOnAction(event -> {

            String userInput = textArea1.getText();
            HashMap<Character, Integer> hashMap = new HashMap<>();

            label1.setText("");
            label2.setText("");
            label3.setText("");
            label4.setText("");

            for (int i = 0; i < userInput.length(); i++) {
                Character reference = userInput.charAt(i);
                Integer check = hashMap.get(reference);
                if (check == null) {
                    int count = 0;
                    for (int p = 0; p < userInput.length(); p++) {
                        if (userInput.charAt(p) == reference) {
                            count++;
                        }
                    }
                    hashMap.put(reference, count);
                }
            }
            System.out.println(hashMap);
            String[] keyValues = new String[hashMap.size()];
            int temp = 0;
            for (Map.Entry< Character, Integer > e : hashMap.entrySet()) {
                keyValues[temp] = e.getKey() + " : " + String.valueOf(e.getValue()) + "\n";
                temp++;
            }
            if (keyValues.length < 8){
                String labelText = "";
                for (String keyValue : keyValues) {
                    labelText = labelText + keyValue;
                }
                label2.setText(labelText);
            }
            else if (keyValues.length >= 8 && keyValues.length < 17){
                System.out.println(keyValues.length);
                String labelSet = "";
                for (int i = 0; i < 8; i++){
                    labelSet = labelSet + keyValues[i];
                }
                label2.setText(labelSet);
                labelSet = "";
                for (int i = 8; i < keyValues.length; i++){
                    labelSet = labelSet + keyValues[i];
                }
                label3.setText(labelSet);
            }
            else if (keyValues.length >= 17 && keyValues.length < 25){
                System.out.println(keyValues.length);
                String labelSet = "";
                for (int i = 0; i < 8; i++){
                    labelSet = labelSet + keyValues[i];
                }
                label2.setText(labelSet);
                labelSet = "";
                for (int i = 8; i < 16; i++){
                    labelSet = labelSet + keyValues[i];
                }
                label3.setText(labelSet);
                labelSet = "";
                for (int i = 16; i < keyValues.length; i++){
                    labelSet = labelSet + keyValues[i];
                }
                label4.setText(labelSet);
            }
            else if (keyValues.length >= 25 && keyValues.length < 33){
                System.out.println(keyValues.length);
                String labelSet = "";
                for (int i = 0; i < 8; i++){
                    labelSet = labelSet + keyValues[i];
                }
                label2.setText(labelSet);
                labelSet = "";
                for (int i = 8; i < 16; i++){
                    labelSet = labelSet + keyValues[i];
                }
                label3.setText(labelSet);
                labelSet = "";
                for (int i = 16; i < 24; i++){
                    labelSet = labelSet + keyValues[i];
                }
                label4.setText(labelSet);
                labelSet = "";
                for (int i = 24; i < keyValues.length; i++){
                    labelSet = labelSet + keyValues[i];
                }
                label1.setText(labelSet);
            }
            hashMap.clear();
        });
    }
    public static int[] radixSortLSD(int[] old) {
        long startTime = System.currentTimeMillis();
        for (int shift = Integer.SIZE - 1; shift > -1; shift--) {
            int[] tmp = new int[old.length];
            int j = 0;

            for (int i = 0; i < old.length; i++) {
                boolean move = old[i] << shift >= 0;

                if ((shift == 0) != move) {
                    tmp[j] = old[i];
                    j++;
                } else {
                    old[i - j] = old[i];
                }
            }

            System.arraycopy(old, 0, tmp, j, tmp.length - j);

            old = tmp;
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.print("Elapsed Time " + estimatedTime + "ms");
        System.out.println();
        return old;
    }
}