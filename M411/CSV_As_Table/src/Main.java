import java.io.*;

public class Main {
    public static void main (String[] args){
        String line;
        String fileLocation = "/Users/Fabian/Documents/GitHub/school/M411/CSV_As_Table/MOCK_DATA.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                writeTable(data);
                System.out.println("");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeTable(String[] rawData){
        int[] lengths = new int[6];
        String[] correctLength = new String[6];
        for (int i = 0; i < 6; i++){
            lengths[i] = rawData[i].length();
        }
        for (int i = 0; i < 6; i++){
            correctLength[i] = "";
            for (int p = 0; p < lengths[i]; p++){
                correctLength[i] = correctLength[i] + "-";
            }
            correctLength[i] = correctLength[i] + "--------";
        }
        String[] whitespaces = new String[6];
        whitespaces[0] = "";
        for (int p = 0; p < rawData[0].length() + 4; p++){
            whitespaces[0] = whitespaces[0] + " ";
        }
        whitespaces[1] = "";
        for (int p = 0; p < rawData[1].length() - 3; p++){
            whitespaces[1] = whitespaces[1] + " ";
        }
        whitespaces[2] = "";
        for (int p = 0; p < rawData[2].length() - 2; p++){
            whitespaces[2] = whitespaces[2] + " ";
        }
        whitespaces[3] = "";
        for (int p = 0; p < rawData[3].length() + 1; p++){
            whitespaces[3] = whitespaces[3] + " ";
        }
        whitespaces[4] = "";
        for (int p = 0; p < rawData[4].length() + 1; p++){
            whitespaces[4] = whitespaces[4] + " ";
        }
        whitespaces[5] = "";
        for (int p = 0; p < rawData[5].length() - 3; p++){
            whitespaces[5] = whitespaces[5] + " ";
        }

        System.out.format("+"+ correctLength[0] + "+"+ correctLength[1] + "+"+ correctLength[2] + "+"+ correctLength[3] + "+"+ correctLength[4] + "+" + correctLength[5] +"+%n");
        System.out.format("| ID " + whitespaces[0] + "| First Name" + whitespaces[1] + "| Last Name" + whitespaces[2] + "| Email " + whitespaces[3] + "| Gender" + whitespaces[4] + "| IP Address" + whitespaces[5] + "| %n");
        System.out.format("+"+ correctLength[0] + "+"+ correctLength[1] + "+"+ correctLength[2] + "+"+ correctLength[3] + "+"+ correctLength[4] + "+" + correctLength[5] +"+%n");
        for (int i = 0; i < 6; i++) {
            System.out.format("| " + rawData[i] + "       ");
        }
        System.out.format("| %n");
        System.out.format("+"+ correctLength[0] + "+"+ correctLength[1] + "+"+ correctLength[2] + "+"+ correctLength[3] + "+"+ correctLength[4] + "+" + correctLength[5] +"+%n");
    }
}
