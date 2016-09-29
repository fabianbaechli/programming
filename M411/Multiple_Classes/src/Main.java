public class Main {
    public static void main (String[] args){
        Nemo nemo = new Nemo(12, 0.05, "male", "nemo", "orange");
        nemo.breathe();
        ClassInspector.toString(nemo, "Nemo.");
        System.out.println("");

        Shark shark = new Shark(12, 500, "male", "white shark", "bruce", "blue / white");
        ClassInspector.toString(shark, "Shark.");
        System.out.println("");

        Human fabian = new Human(17, 70, "male", "Fabian", "Brown", "White", "No Religion", "Software engineer");
        fabian.breathe();
        ClassInspector.toString(fabian, "Human.");
    }
}
