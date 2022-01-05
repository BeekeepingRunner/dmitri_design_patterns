import org.junit.jupiter.api.Test;
import patterns.creational.singleton.testing.SingletonRecordFinder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonDatabase {

    @Test
    public void singletonTotalPopulationTest() {
        SingletonRecordFinder rf = new SingletonRecordFinder();
        List<String> names = List.of("Seoul", "Mexico City");
        int totalPopulation = rf.getTotalPopulation(names);
        assertEquals(17500000 + 17400000, totalPopulation);
    }
}
