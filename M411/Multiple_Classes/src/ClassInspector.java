import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
class ClassInspector {

    //Print all "get" Methods an their value
    static void toString(LivingBeing object) {
        try {
            String keyWord = "";
            Method[] methodsOfClass = null;
            if (object instanceof Nemo) {
                methodsOfClass = Nemo.class.getDeclaredMethods();
                keyWord = "Nemo.";
            }
            else if (object instanceof Human){
                methodsOfClass = Human.class.getDeclaredMethods();
                keyWord = "Human.";
            }
            else if (object instanceof Shark){
                methodsOfClass = Shark.class.getDeclaredMethods();
                keyWord = "Shark.";
            }

            for (Method aMethodOfMethodsOfClass : methodsOfClass) {
                String splitArray = aMethodOfMethodsOfClass.toString().split(keyWord)[1];
                if (splitArray.startsWith("get")){
                    System.out.println(splitArray + " = " + aMethodOfMethodsOfClass.invoke(object));
                }
            }
        } catch (Throwable e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    static void incrementAge(Object[] objectsToIncrement, int incrementValue) {
        for(Object object : objectsToIncrement) {
            if(object instanceof  LivingBeing) {
                LivingBeing being = (LivingBeing) object;
                being.setAge(being.getAge()+incrementValue);
            }
        }
    }
}
