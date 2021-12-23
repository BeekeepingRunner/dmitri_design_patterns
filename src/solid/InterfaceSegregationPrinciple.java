package solid;

public class InterfaceSegregationPrinciple {
}

class Document {

}

interface Machine {

    void print(Document document);
    void fax(Document document);
    void scan(Document document);
}

class MultiFunctionPrinter implements Machine {

    @Override
    public void print(Document document) {
        // ...
    }

    @Override
    public void fax(Document document) {
        // ...
    }

    @Override
    public void scan(Document document) {
        // ...
    }
}

class OldFashionedPrinter implements Machine {

    @Override
    public void print(Document document) {
        // ...
    }

    @Override
    public void fax(Document document) {
        // YAGNI
    }

    @Override
    public void scan(Document document) {
        // YAGNI
    }
}

interface Printer {

    void print(Document d);
}

interface Scanner {

    void scan(Document d);
}

class JustAPrinter implements Printer {
    @Override
    public void print(Document d) {
        // ...
    }
}

class Photocopier implements Printer, Scanner {

    @Override
    public void print(Document d) {
        // ...
    }

    @Override
    public void scan(Document d) {
        // ...
    }
}

// Different way to implement for example a Photocopier
interface MultifunctionDevice extends Printer, Scanner {}

class MultifunctionMachine implements MultifunctionDevice {

    @Override
    public void print(Document d) {
        // ...
    }

    @Override
    public void scan(Document d) {
        // ...
    }
}