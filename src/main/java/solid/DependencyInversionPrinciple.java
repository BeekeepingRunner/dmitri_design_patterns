package solid;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;

public class DependencyInversionPrinciple {

    public static void main(String[] args) {

        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        new Research(relationships);
    }
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

// low-level module
class Relationships {

    private List<Triplet<Person, Relationship, Person>> relations
            = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }
}

// high-level module
class Research {

    public Research(Relationships relationships) {
        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
        relations.stream()
                .filter(relation -> relation.getValue0().name.equals("John")
                && relation.getValue1() == Relationship.PARENT)
                .forEach(relation -> System.out.println(
                        "John has a child called " + relation.getValue2().name
                ));
    }
}