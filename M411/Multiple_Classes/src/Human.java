
public class Human extends Mammal {
    private String skinColor;
    private String persuasion;
    private String job;

    Human(int age, double weight, String gender, String name, String colorOfFur, String skinColor, String persuasion, String job){
        super();
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.name = name;
        this.colorOfFur = colorOfFur;
        this.skinColor = skinColor;
        this.persuasion = persuasion;
        this.job = job;
        this.kind = "human";
        this.inWater = false;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getPersuasion() {
        return persuasion;
    }

    public void setPersuasion(String persuasion) {
        this.persuasion = persuasion;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
