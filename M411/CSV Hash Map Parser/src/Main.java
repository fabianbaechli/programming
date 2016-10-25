import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileLocation = "/Users/Fabian/Desktop/countries_simplified.csv";
        HashMap<String, String> hashMap = new HashMap<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            while ((line = br.readLine()) != null) {
                String[] country = line.split(",");
                hashMap.put(country[0], country[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(hashMap);
        System.out.println("Nach einem Land suchen?");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Land: " + hashMap.get(scanner.nextLine()));
    }
}
