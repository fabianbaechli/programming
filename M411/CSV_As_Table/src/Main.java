import java.io.*;

public class Main {
    private static int[] biggestNumbers = new int[6];
    public static String[] headOfTable = new String[6];
    public static void main (String[] args){
        String line;
        String fileLocation = "/Users/Fabian/Documents/GitHub/school/M411/CSV_As_Table/MOCK_DATA.csv";
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
        whitespaces[0] = "";
        for (int p = 0; p < biggestNumbers[0] + 2; p++){
            whitespaces[0] = whitespaces[0] + " ";
        }
        whitespaces[1] = "";
        for (int p = 0; p < biggestNumbers[1] - 5; p++){
            whitespaces[1] = whitespaces[1] + " ";
        }
        whitespaces[2] = "";
        for (int p = 0; p < biggestNumbers[2] - 4; p++){
            whitespaces[2] = whitespaces[2] + " ";
        }
        whitespaces[3] = "";
        for (int p = 0; p < biggestNumbers[3] - 1; p++){
            whitespaces[3] = whitespaces[3] + " ";
        }
        whitespaces[4] = "";
        for (int p = 0; p < biggestNumbers[4] - 1; p++){
            whitespaces[4] = whitespaces[4] + " ";
        }
        whitespaces[5] = "";
        for (int p = 0; p < biggestNumbers[5] - 5; p++){
            whitespaces[5] = whitespaces[5] + " ";
        }
        System.out.format("%n| ID " + whitespaces[0] + "| First Name" + whitespaces[1] + "| Last Name" + whitespaces[2] + "| Email " + whitespaces[3] + "| Gender" + whitespaces[4] + "| IP Address" + whitespaces[5] + "| %n");
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
