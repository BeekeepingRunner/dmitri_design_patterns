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