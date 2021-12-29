package solid;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;

public class DependencyInversionPrinciple {


}

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {

    public String name;
    // other data ...

    public Person(String name) {
        this.name = name;
    }
}

class Relationships {

    private List<Triplet<Person, Relationship, Person>> relations
            = new ArrayList<>();
}