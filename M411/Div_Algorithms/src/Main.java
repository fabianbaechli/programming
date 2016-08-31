import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    private static String inputFilePath = "/Users/Fabian/Documents/GitHub/school/M411/Div_Algorithms/out/production/Div_Algorithms/inputfile.txt";
    private static String outputFilePath = "/Users/Fabian/Documents/GitHub/school/M411/Div_Algorithms/out/production/Div_Algorithms/outputfile.txt";

    public static void main(String[] args) {
        /*TODO
          More sorting algorithms
         */
        GUI.mainGui();
    }

    static long bubbleSort(JButton[] buttonsToDisable, JLabel progressLabel) {
        for (JButton buttons : buttonsToDisable)
            buttons.setEnabled(false);
        String userEingabe = readFile(inputFilePath);
        int[] userInput = new int[userEingabe.length() + 1];
        int var;
        for (int i = 0; i < userEingabe.length(); i++){
            userInput[i] = userEingabe.charAt(i);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < userEingabe.length(); i++) {
            for (int c = 0; c < userEingabe.length() - i; c++) {
                if (userInput[c] > userInput[c + 1]) {
                    var = userInput[c];
                    userInput[c] = userInput[c + 1];
                    userInput[c + 1] = var;
                }
            }
        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        for (int a = 0; a < userEingabe.length(); a++) {
            System.out.print((char) userInput[a + 1]);
        }
        System.out.println();
        if (estimatedTime < 1){
            System.out.println("Elapsed Time: < 1ms");
        } else {
            System.out.println("Elapsed Time: " + estimatedTime + "ms");
        }
        writeFile(outputFilePath, userInput);
        for (JButton buttons : buttonsToDisable)
            buttons.setEnabled(true);
        return estimatedTime;
    }

    static long radixSortLSD(JButton[] buttonsToDisable, JLabel progressLabel) {
        for (JButton buttons : buttonsToDisable)
            buttons.setEnabled(false);
        String userInput = readFile(inputFilePath);
        int[] old = new int[userInput.length()];
        for (int i = 0; i < userInput.length(); i++){
            old[i] = (int)userInput.charAt(i);
        }

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
        for (int value : old)
            System.out.print((char)value);
        System.out.println();
        long estimatedTime = System.currentTimeMillis() - startTime;
        if (estimatedTime < 1){
            System.out.println("Elapsed Time: < 1ms");
        } else {
            System.out.println("Elapsed Time: " + estimatedTime + "ms");
        }
        writeFile(outputFilePath, old);
        for (JButton buttons : buttonsToDisable)
            buttons.setEnabled(true);
        return estimatedTime;
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
        System.out.println(unsortedString);
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
}

