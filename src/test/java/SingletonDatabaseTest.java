import org.junit.jupiter.api.Test;
import patterns.creational.singleton.testing.ConfigurableRecordFinder;
import patterns.creational.singleton.testing.Database;
import patterns.creational.singleton.testing.SingletonRecordFinder;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DummyDatabase implements Database {

    private Dictionary<String, Integer> data = new Hashtable<>();

    public DummyDatabase() {
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String cityName) {
        return data.get(cityName);
    }
}

public class SingletonDatabaseTest {

    @Test // integration test >:(
    public void singletonTotalPopulationTest() {
        SingletonRecordFinder rf = new SingletonRecordFinder();
        List<String> names = List.of("Seoul", "Mexico City");
        int totalPopulation = rf.getTotalPopulation(names);
        assertEquals(17500000 + 17400000, totalPopulation);
    }

    @Test // Unit test <:)
    public void dependentPopulationTest() {

        DummyDatabase db = new DummyDatabase();
        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
        assertEquals(4, rf.getTotalPopulation(List.of("alpha", "gamma")));
    }
}
