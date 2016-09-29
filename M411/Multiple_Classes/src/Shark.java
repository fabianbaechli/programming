public class Shark extends Fish {
    Shark (int age, double weight, String gender, String kind, String name, String colorOfScale) {
        super();
        this.age = age;
        this.kind = kind;
        this.weight = weight;
        this.gender = gender;
        this.name = name;
        this.colorOfScale = colorOfScale;
    }
    public void electroPerception(){
        System.out.println("-- Sensing electromagnetic fields --");
    }
}
