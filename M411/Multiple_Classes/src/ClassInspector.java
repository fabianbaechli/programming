import java.lang.reflect.Method;
public class ClassInspector {

    //Print all "get" Methods an their value
    static void toString(Object object, String keyWord) {
        try {
            Method[] methodsOfClass = null;
            if (object instanceof Nemo) {
                Class objectClass = Nemo.class;
                methodsOfClass = objectClass.getDeclaredMethods();
            }
            else if (object instanceof Human){
                Class objectClass = Human.class;
                methodsOfClass = objectClass.getDeclaredMethods();
            }
            else if (object instanceof Shark){
                Class objectClass = Shark.class;
                methodsOfClass = objectClass.getDeclaredMethods();
            }

            for (Method aMethodOfHuman : methodsOfClass) {
                String splitArray = aMethodOfHuman.toString().split(keyWord)[1];
                if (splitArray.startsWith("get")){
                    System.out.println(splitArray + " = " + aMethodOfHuman.invoke(object));
                }
            }
        } catch (Throwable e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
