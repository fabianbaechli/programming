public class Nemo extends Fish{
    Nemo(int age, double weight, String gender, String name) {
        super();
        this.age = age;
        this.kind = "Clown Fish";
        this.weight = weight;
        this.gender = gender;
        this.name = name;
        this.colorOfScale = "orange";
    }
    public void findDory(){
        System.out.println("-- Finding Dory --");
    }
}
