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
        for (Object anObjectInObjectsToIncrement :objectsToIncrement) {
            if (anObjectInObjectsToIncrement instanceof Nemo) {
                ClassInspector.increment(Nemo.class, anObjectInObjectsToIncrement, incrementValue);
            }
            else if (anObjectInObjectsToIncrement instanceof Human){
                ClassInspector.increment(Human.class, anObjectInObjectsToIncrement, incrementValue);
            }
            else if (anObjectInObjectsToIncrement instanceof Shark){
                ClassInspector.increment(Shark.class, anObjectInObjectsToIncrement, incrementValue);
            }
        }
    }
    private static void increment(Class<? extends LivingBeing> objectClass, Object objectOfClass, int incrementValue){
        try {
            Method setMethod = objectClass.getMethod("setAge", int.class);
            Method getMethod = objectClass. getMethod("getAge");
            Object object = getMethod.invoke(objectOfClass);
            Integer value = (Integer) object;
            setMethod.invoke(objectOfClass, value + incrementValue);
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.toString());
        }
    }
}
