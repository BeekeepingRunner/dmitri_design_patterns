package patterns.structural.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Demo {

    public static void main(String[] args) {

        User user = new User("John Smith");
        User user2 = new User("Jane Smith");
    }
}

class User2 {

    static List<String> strings = new ArrayList<>();
    private int [] names;

    public User2(String fullName) {

        Function<String, Integer> getOrAdd = (String s) -> {
            int idx = strings.indexOf(s);
            if (idx != -1)
                return idx;
            else {
                strings.add(s);
                return strings.size() - 1;
            }
        };

        names = Arrays.stream(fullName.split(" "))
                .mapToInt(getOrAdd::apply)
                .toArray();
    }
}

class User {

    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }
}