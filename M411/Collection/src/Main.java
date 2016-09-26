import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static String fileLocation = "/Users/Fabian/Documents/GitHub/school/M411/Collection/out/production/Collection/airports.csv";
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            while ((line = br.readLine()) != null) {
                String[] country = line.split(",");
                hashMap.put(country[0], country[1]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(hashMap);
        System.out.println("Search for value?");
        Scanner scanner = new Scanner(System.in);
        System.out.println(hashMap.get(scanner.nextLine()));
    }
}
