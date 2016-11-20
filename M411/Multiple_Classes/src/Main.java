public class Main {
    public static void main (String[] args){
        Nemo nemo = new Nemo(12, 0.05, "male", "nemo");
        nemo.breathe();
        ClassInspector.toString(nemo);
        System.out.println("");

        Shark shark = new Shark(12, 500, "male", "white shark", "bruce", "blue / white");
        ClassInspector.toString(shark);
        System.out.println("");

        Human fabian = new Human(17, 70, "male", "Fabian", "Brown", "White", "No Religion", "Software engineer");
        fabian.breathe();
        ClassInspector.toString(fabian);

        Object[] objectArray = {nemo, shark, fabian};
        ClassInspector.incrementAge(objectArray, 10);

        ClassInspector.toString(nemo);
        System.out.println("");
        ClassInspector.toString(shark);
        System.out.println("");
        ClassInspector.toString(fabian);
    }
}
