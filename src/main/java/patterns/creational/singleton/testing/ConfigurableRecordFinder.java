package patterns.creational.singleton.testing;

import java.util.List;

public class ConfigurableRecordFinder {

    private Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> cityNames) {
        int result = 0;
        for (String name : cityNames) {
            result += database.getPopulation(name);
        }
        return result;
    }
}
