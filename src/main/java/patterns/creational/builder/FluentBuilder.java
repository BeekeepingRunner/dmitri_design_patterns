package patterns.creational.builder;

public class FluentBuilder {

    public static void main(String[] args) {

        EmployeeBuilder pb = new EmployeeBuilder();
        Person bartek = pb
                .withName("Bartek")
                .worksAt("Google")
                .build();

        System.out.println(bartek);
    }
}

class Person {

    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>> {

    protected Person person = new Person();

    protected SELF self() {
        return (SELF) this;
    }

    public SELF withName(String name) {
        person.name = name;
        return self();
    }

    public Person build() {
        return person;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

    @Override
    protected EmployeeBuilder self() {
        return this;
    }

    public EmployeeBuilder worksAt(String position) {
        person.position = position;
        return this;
    }
}