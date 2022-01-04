package patterns.creational.singleton.lazy;

public class Demo {

}

class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("initializing a lazy singleton");
    }

    /* FIX BY SYNCHRONIZED KEYWORD
    public static synchronized LazySingleton getInstance() {

        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }
     */

    // double-checked locking
    public static LazySingleton getInstance() {
        
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }

        return instance;
    }
}
