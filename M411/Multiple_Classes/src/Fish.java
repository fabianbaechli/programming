abstract class Fish extends LivingBeing{
    String colorOfScale;
    String kind;

    @Override           //Because all fish breathe through grills
    public void breathe() {
        System.out.println("☁ Breathing through grills ☁");
    }

    Fish() {
        super();
        inWater = true;
        coldBlooded = true;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getColorOfScale() {
        return colorOfScale;
    }

    public void setColorOfScale(String colorOfScale) {
        this.colorOfScale = colorOfScale;
    }
}
