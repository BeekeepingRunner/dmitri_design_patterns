package patterns.creational.singleton.testing;

import java.util.List;

public class SingletonRecordFinder {

    public int getTotalPopulation(List<String> cityNames) {
        int result = 0;
        for (String name : cityNames) {
            result += SingletonDatabase.getInstance().getPopulation(name);
        }
        return result;
    }
}
