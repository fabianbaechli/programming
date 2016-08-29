import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Jahrgang: ");
        String temp = scanner.next();
        int yearGroup = Integer.parseInt(temp);
        scanner.close();

        AgeCalculator test = new AgeCalculator(name, yearGroup);
        test.tellAge();
    }
}
