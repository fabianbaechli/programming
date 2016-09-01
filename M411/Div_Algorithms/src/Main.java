import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    private static String inputFilePath = "/Users/Fabian/Documents/GitHub/school/M411/Div_Algorithms/out/production/Div_Algorithms/inputfile.txt";
    private static String outputFilePath = "/Users/Fabian/Documents/GitHub/school/M411/Div_Algorithms/out/production/Div_Algorithms/outputfile.txt";

    public static void main(String[] args) {
        /*TODO
         * More sorting algorithms
         * Correct text change of JLabel
         */
        GUI.mainGui();
    }

    static void bubbleSort(JButton[] buttonsToDisable, JLabel progressLabel) {
        for (JButton buttons : buttonsToDisable)
            buttons.setEnabled(false);
        String userEingabe = readFile(inputFilePath);
        int[] userInput = new int[userEingabe.length() + 1];
        int var;
        for (int i = 0; i < userEingabe.length(); i++){
            userInput[i] = userEingabe.charAt(i);
        }
        int temp = 0;
        String labelString = ("<html><div style='text-align: center;'>"
                + "--In Progress--" + "<br> Chars: "
                + userEingabe.length()
                +"<br> Chars Sorted: " + temp + "</html>");
        progressLabel.setText(labelString);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < userEingabe.length(); i++) {
            for (int c = 0; c < userEingabe.length() - i; c++) {
                if (userInput[c] > userInput[c + 1]) {
                    var = userInput[c];
                    userInput[c] = userInput[c + 1];
                    userInput[c + 1] = var;
                }
                temp = i;
                labelString = ("<html><div style='text-align: center;'>"
                        + "--In Progress--" + "<br> Chars: "
                        + userEingabe.length()
                        +"<br> Chars Sorted: " + temp + "</html>");
                progressLabel.setText(labelString);
            }
        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        writeFile(outputFilePath, userInput);
        for (JButton buttons : buttonsToDisable)
            buttons.setEnabled(true);

        labelTime(estimatedTime, progressLabel);
    }

    static void radixSortLSD(JButton[] buttonsToDisable, JLabel progressLabel) {
        for (JButton buttons : buttonsToDisable)
            buttons.setEnabled(false);
        String userInput = readFile(inputFilePath);
        int[] old = new int[userInput.length()];
        for (int i = 0; i < userInput.length(); i++){
            old[i] = (int)userInput.charAt(i);
        }
        int temp = 0;
        String labelString = ("<html><div style='text-align: center;'>"
                + "--In Progress--" + "<br> Chars: "
                + userInput.length()
                +"<br> Chars Sorted: " + temp + "</html>");
        progressLabel.setText(labelString);

        long startTime = System.currentTimeMillis();
        for (int shift = Integer.SIZE - 1; shift > -1; shift--) {
            int[] tmp = new int[old.length];
            int j = 0;

            for (int i = 0; i < old.length; i++) {
                boolean move = old[i] << shift >= 0;

                if ((0 == shift) != move) {
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

        writeFile(outputFilePath, old);
        for (JButton buttons : buttonsToDisable)
            buttons.setEnabled(true);

        labelTime(estimatedTime, progressLabel);
    }

    private static String readFile(String filePath){
        String unsortedString = "";
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                unsortedString = unsortedString + line;
            }
            scanner.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return unsortedString;
    }

    private static void writeFile(String filePath, int[] sortedArray){
        try{
            FileWriter fileWriter = new FileWriter(filePath);
            for (int value : sortedArray){
                fileWriter.write((char)value);
            }
            fileWriter.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void labelTime(double time, JLabel label){
        if (time < 1){
            label.setText("<html><div style='text-align: center;'>Sorting Process Complete!<br>Estimated time: &#60 1ms</html>");
        } else {
            label.setText("<html><div style='text-align: center;'> Sorting Process Complete!<br>" + "Estimated time: " + time + "ms</html>");
        }
    }
}

