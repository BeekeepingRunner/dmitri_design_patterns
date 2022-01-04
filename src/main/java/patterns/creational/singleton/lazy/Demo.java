package patterns.creational.singleton.lazy;

public class Demo {

}

class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("initializing a lazy singleton");
    }

    // not thread safe
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }
}
