import java.util.Calendar;

public class AgeCalculator {
    private String name;
    private int ageGroup;

    public AgeCalculator(String name, int ageGroup){
        setName(name);
        setAgeGroup(ageGroup);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAgeGroup(int ageGroup){
        this.ageGroup = ageGroup;
    }

    public int getAgeGroup() {
        return ageGroup;
    }
    public void tellAge(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int yearOutput = year - ageGroup;
        System.out.println("Hallo "+ name + " du wirst in diesem Jahr " + yearOutput);
    }
}
