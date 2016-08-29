import org.apache.commons.lang.math.NumberUtils;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.lang.String;
import java.util.List;
import java.io.File;

public class ParsingFile {
    public static void main(String[] args) {
        String path = "/Users/Fabian/Programming/Java/M411/3, ParsingFiles/out/production/ParsingFiles/file.txt";

        int userSelection = startMenu();
        if (userSelection == 1){
            writeFile(path);
        }
        else if (userSelection == 2){
            readFile(path);
        }
        else if (userSelection == 3){
            searchDocument(path);
        }
        else if (userSelection == 0){
            System.out.println("Invalid input you illiterate");
        }
    }
    private static void readFile(String filePath){
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                System.out.print("Name: ");
                for (String print : parts){
                    if (!NumberUtils.isNumber(print)) {
                        System.out.print(print + " ");
                    }
                    else{
                        System.out.println("Age: " + (2016 - Integer.parseInt(print)));
                    }
                }
            }
            scanner.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private static void writeFile(String filePath){
        System.out.println("Set Value \"exit\" to end");
        int temp = 0;
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        while (true){
            System.out.println("Set Name");
            String name = scanner.next();
            if (name.equals("exit")){
                break;
            }
            System.out.println("Set the birth year:");
            String year = scanner.next();
            if (year.equals("exit")){
                break;
            }
            list.add(temp, name);
            temp++;
            list.add(temp, year);
            temp++;
        }
        scanner.close();
        File f = new File(filePath);
        /* if file does not exist */
        if(!f.exists() && !f.isDirectory()) {
            try{
                FileWriter fileWriter = new FileWriter(filePath);
                for (String value : list){
                    fileWriter.write(value + ";");
                    if (NumberUtils.isNumber(value)) {
                        fileWriter.write(System.getProperty("line.separator"));
                    }
                }
                fileWriter.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

        /* if file already exists text gets added */
        else{
            try(FileWriter fw = new FileWriter(filePath, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                try{
                    for (String value : list){
                        out.print(value + ";");
                        if (NumberUtils.isNumber(value)) {
                            out.println();
                        }
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    private static void searchDocument(String filePath){
        //Simple search function
        //Only returns values which match the input 100%

        System.out.println("Enter Search Keyword: ");
        Scanner userIn = new Scanner(System.in);
        String searchKeyword = userIn.next();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            String[][] values = new String[10000][10000];
            int temp1 = 0;
            int temp2 = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");


                for (String print : parts){
                    if (print.equals(searchKeyword)) {
                        System.out.print(temp1 + " ");
                        for (String printOut : parts) {
                            System.out.print(printOut + " ");
                            values[temp1][temp2] = printOut;
                            temp2++;
                        }
                        temp2 = 0;
                        System.out.println("");
                    }
                }
            temp1++;
            }
            scanner.close();
            Scanner valueToDelete = new Scanner(System.in);
            System.out.println("Which value do you want to delete");
            String userInput = valueToDelete.next();
            if (NumberUtils.isNumber(userInput)){
                deleteValue();
            }
            else {
                System.out.println("Invalid input you illiterate");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private static int startMenu(){
        System.out.println("To write into File press 1");
        System.out.println("To read File press 2");
        System.out.println("To search the File for values press 3");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        if (input.equals("1")){
            return 1;
        }
        if (input.equals("2")){
            return 2;
        }
        if (input.equals("3")){
            return 3;
        }
        else{
            return 0;
        }
    }
    private static void deleteValue(){
        System.out.println("Can't do");
    }
}