package patterns.creational.prototype.serialization;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class Serialization {

    public static void main(String[] args) {

        Foo foo = new Foo(42, "life");
        Foo foo2 = SerializationUtils.roundtrip(foo);

        foo2.whatever = "xyz";
        System.out.println(foo);
        System.out.println(foo2);
    }
}

class Foo implements Serializable {

    public int stuff;
    public String whatever;

    public Foo(int stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff=" + stuff +
                ", whatever='" + whatever + '\'' +
                '}';
    }
}
