import java.io.*;

public class Main {
    private static int[] biggestNumbers = new int[6];
    private static String[] headOfTable = new String[6];
    public static void main (String[] args){
        String line;
        String fileLocation = "/Users/Fabian/Documents/GitHub/School/M411/CSV_As_Table/MOCK_DATA.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                getBiggestNumber(data);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        drawTable();
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                printData(data);
            }
            for (int i = 0; i < 6; i++){
                System.out.print(headOfTable[i]);
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void getBiggestNumber(String[] rawInput){
        for (int i = 0; i < rawInput.length; i++){
            if (rawInput[i].length() > biggestNumbers[i]){
                biggestNumbers[i] = rawInput[i].length();
            }
        }
    }
    private static void drawTable (){
        for(int i = 0; i < 6; i++){
            headOfTable[i] = "+";
            for (int p = 0; p < biggestNumbers[i]; p++){
                headOfTable[i] = headOfTable[i] + "-";
            }
            headOfTable[i] = headOfTable[i] + "------";
            if (i == 5){
                headOfTable[i] = headOfTable[i] + "+";
            }
            System.out.print(headOfTable[i]);
        }
        String[] whitespaces = new String[6];
        int[] numbers = {2, -5, -4, -1, -1, -5};

        for (int i = 0; i < 6; i++){
            whitespaces[i] = "";
            for (int p = 0; p < biggestNumbers[i] + numbers[i] ; p++) {
                whitespaces[i] = whitespaces[i] + " ";
            }
        }

        System.out.format("%n| ID " + whitespaces[0] + "| First Name" + whitespaces[1] + "| Last Name" + whitespaces[2]
                + "| Email " + whitespaces[3] + "| Gender" + whitespaces[4] + "| IP Address" + whitespaces[5] + "| %n");
        for (String aStringInHeader : headOfTable)
            System.out.format(aStringInHeader);
        System.out.println();
    }
    private static void printData(String[] rawData){
        String[] whitespaces = new String[6];
        for (int i = 0; i < 6; i++){
            whitespaces[i] = "";
            for (int p = 0; p <biggestNumbers[i] + 5 - rawData[i].length(); p++){
                whitespaces[i] = whitespaces[i] + " ";
            }
        }
        for (int i = 0; i < 6; i++) {
            System.out.format("| " + rawData[i] + whitespaces[i]);
        }
        System.out.format("| %n");
    }
}
