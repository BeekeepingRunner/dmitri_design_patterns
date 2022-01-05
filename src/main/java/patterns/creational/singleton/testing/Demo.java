package patterns.creational.singleton.testing;

import com.google.common.collect.Iterables;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

class SingletonDatabase {

    private Dictionary<String, Integer> capitals = new Hashtable<>();

    private static int instanceCount = 0;

    public static int getCount() { return instanceCount; }

    private SingletonDatabase() {
        ++instanceCount;
        System.out.println("initializing database");

        try {
            File f = new File("capitals.txt");

            Path fullPath = Paths.get(f.getPath());
            List<String> lines = Files.readAllLines(fullPath);

            Iterables.partition(lines, 2)
                    .forEach(kv -> capitals.put(
                            kv.get(0).trim(),
                            Integer.parseInt(kv.get(1))
                    ));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final SingletonDatabase INSTANCE
            = new SingletonDatabase();

    public static SingletonDatabase getInstance() {
        return INSTANCE;
    }

    public int getPopulation(String cityName) {
        return capitals.get(cityName);
    }
}

public class Demo {
}
