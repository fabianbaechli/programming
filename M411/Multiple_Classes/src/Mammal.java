abstract class Mammal extends LivingBeing {
    String colorOfFur;
    String kind;

    @Override           //Because all mammals breathe through lungs
    public void breathe() {
        System.out.println("☁ Breathing through lungs ☁");
    }

    Mammal() {
        super();
        coldBlooded = false;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void feedChildrenWithMilk(){
        System.out.println("-- Feeding children with milk --");
    }

    public void givingBirt(){
        System.out.println("-- Giving birth --");
    }

    public String getColorOfFur() {
        return colorOfFur;
    }

    public void setColorOfFur(String colorOfFur) {
        this.colorOfFur = colorOfFur;
    }
}
