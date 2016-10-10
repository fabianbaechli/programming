
/**
 *  Program to show Inheritance
 *  @author Fabian
 *
 */
public class Main {
    public static void main (String[] args){
        Nemo nemo = new Nemo(12, 0.05, "male", "nemo");
        Shark shark = new Shark(12, 500, "male", "white shark", "bruce", "blue / white");
        Human fabian = new Human(17, 70, "male", "Fabian", "Brown", "White", "No Religion", "Software engineer");

        ClassInspector.toString(nemo);
        System.out.println("");
        ClassInspector.toString(shark);
        System.out.println("");
        ClassInspector.toString(fabian);

        Object[] objectArray = {nemo, shark, fabian};
        ClassInspector.incrementAge(objectArray, 12);

        ClassInspector.toString(nemo);
        System.out.println("");
        ClassInspector.toString(shark);
        System.out.println("");
        ClassInspector.toString(fabian);
    }
}
