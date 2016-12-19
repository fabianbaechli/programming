import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    private static String inputFilePath = "/Users/Fabian/Desktop/Export.sql";
    private static String outputFilePath = "/Users/Fabian/Desktop/Output.sql";

    public static void main(String[] args) {
        GUI.mainGui();
    }

    static void bubbleSort(JButton[] buttonsToDisable, JLabel progressLabel) {
        for(JButton buttons : buttonsToDisable) buttons.setEnabled(false);
        String userEingabe = readFile(inputFilePath);
        int[] userInput = new int[userEingabe.length() + 1];
        int var;
        for (int i = 0; i < userEingabe.length(); i++){
            userInput[i] = userEingabe.charAt(i);
        }
        int temp = 1;
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
                labelString = ("<html><div style='text-align: center;'>" +
                        "--In Progress--" + "<br> Chars: " +
                        userEingabe.length() +
                        "<br>Chars sorted: " + temp + "</html>");
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
        String labelString = ("<html><div style='text-align: center;'>" +
                "--In Progress--" + "<br> Chars: " +
                userInput.length() + "</html>");
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

    static String readFile(String filePath){
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

    static void writeFile(String filePath, int[] sortedArray){
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

    static void labelTime(double time, JLabel label){
        if (time < 1){
            label.setText("<html><div style='text-align: center;'>Sorting Process Complete!" +
                    "<br>Estimated time: &#60 1ms" +
                    "<br>Input File MD5 Hash: " +md5()[0]+
                    "<br>Output File MD5 Hash: " +md5()[1]+"</html>");
        } else {
            label.setText("<html><div style='text-align: center;'>Sorting Process Complete!" +
                    "<br>Estimated time: " +time +
                    "<br>Input File MD5 Hash: " +md5()[0]+
                    "<br>Output File MD5 Hash: " +md5()[1]+"</html>");
        }
    }

    private static String[] md5(){
        //MD5 of input file on index 1
        //MD5 of output file on index 2
        String md5[] = new String[2];
        try {
            FileInputStream fis1 = new FileInputStream(new File(inputFilePath));
            md5[0] = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis1);
            fis1.close();
            FileInputStream fis2 = new FileInputStream(new File(outputFilePath));
            md5[1] = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis2);
            fis2.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return md5;
    }

    static private long partition(int arr[], long left, long right) {
        long i = left, j = right;
        long tmp;
        long pivot = arr[((int)left + (int)right) / 2];

        while (i <= j) {
            while (arr[(int)i] < pivot)
                i++;
            while (arr[(int)j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[(int)i];
                arr[(int)i] = arr[(int)j];
                arr[(int)j] = (int)tmp;
                i++;
                j--;
            }
        }
        return i;
    }

    static void quickSort(int arr[], long left, long right) {
        long index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }
}

