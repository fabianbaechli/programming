import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class ClassInspector {

    //Print all "get" Methods an their value
    static void toString(Object object) {
        try {
            String keyWord = "";
            Method[] methodsOfClass = null;
            if (object instanceof Nemo) {
                Class<Nemo> objectClass = Nemo.class;
                methodsOfClass = objectClass.getDeclaredMethods();
                keyWord = "Nemo.";
            }
            else if (object instanceof Human){
                Class<Human> objectClass = Human.class;
                methodsOfClass = objectClass.getDeclaredMethods();
                keyWord = "Human.";
            }
            else if (object instanceof Shark){
                Class<Shark> objectClass = Shark.class;
                methodsOfClass = objectClass.getDeclaredMethods();
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
                Class<Nemo> objectClass = Nemo.class;
                ClassInspector.increment(objectClass, anObjectInObjectsToIncrement, incrementValue);
            }
            else if (anObjectInObjectsToIncrement instanceof Human){
                Class<Human> objectClass = Human.class;
                ClassInspector.increment(objectClass, anObjectInObjectsToIncrement, incrementValue);
            }
            else if (anObjectInObjectsToIncrement instanceof Shark){
                Class<Shark> objectClass = Shark.class;
                ClassInspector.increment(objectClass, anObjectInObjectsToIncrement, incrementValue);
            }
        }
    }
    private static void increment(Class objectClass, Object objectOfClass, int incrementValue){
        try {
            Method setMethod = objectClass.getMethod("setAge", int.class);
            Method getMethod = objectClass.getMethod("getAge");
            Object i = getMethod.invoke(objectOfClass);
            Integer value = (Integer) i;
            setMethod.invoke(objectOfClass, value + incrementValue);
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.toString());
        }
    }
}
