import java.util.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add Value: ");
        String userInput = scanner.nextLine();
        String[] parts = userInput.split(" ");
        LinkedList<String> temp = addValue(parts);
        System.out.println("Delete Value? ");
        String deleteValue = scanner.next();
        removeElement(temp, deleteValue);
        System.out.println(temp);
        scanner.close();
        for (int i = 0; i < temp.size(); i++){
            System.out.print(temp.get(temp.size()-1 -i) + " ");
        }
    }
    private static LinkedList<String> addValue(String[] input){
        LinkedList<String> linkedList = new LinkedList<>();
        Collections.addAll(linkedList, input);
        return linkedList;
    }
    private static void removeElement(LinkedList<String> linkedList, String elementToDelete){
        for (int i = 0; i < linkedList.size(); i++){
            if (linkedList.get(i).equals(elementToDelete)){
                linkedList.remove(i);
            }
        }
    }
}
