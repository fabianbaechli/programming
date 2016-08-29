package com.company;
import java.util.Scanner;

public class Main {
    public static String welcomeMessage(String input){
        return input;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("First Name: ");
        String firstName = scanner.next();
        System.out.println("Last Name: ");
        String lastName = scanner.next();
        scanner.close();

        Test test = new Test(firstName, lastName);
        stringOut("First Name Input: ");
        stringOut(test.getFirstName());
        stringOut("Last Name Input: ");
        stringOut(test.getLastName());
    }
    public static void stringOut(String input){
        System.out.println(input);
    }
}
