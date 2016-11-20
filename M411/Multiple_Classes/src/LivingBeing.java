abstract class LivingBeing {
    int age;
    double weight;
    boolean inWater;                    //true = Water , false = Land
    boolean coldBlooded;                  //true = ectotherm , false = endothermic
    String gender;
    String name;

    public void breathe(){
        System.out.println("☁ Breathing ☁");
    }
    public void reproduce(){
        System.out.println("⚤ Reproducing ⚤");
    }
    public void intakeOfFood(){
        System.out.println("-- Eating --");
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setInWater(boolean inWater) {
        this.inWater = inWater;
    }

    public boolean getIsInWater() {
        return inWater;
    }

    public void setColdBlooded(boolean ectotherm) {
        this.coldBlooded = ectotherm;
    }

    public boolean getColdBlodded() {
        return coldBlooded;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}