package patterns.structural.decorator.dynamic;

public class Demo {

    public static void main(String[] args) {

        Circle circle = new Circle(10);
        System.out.println(circle.info());

        ColoredShape blueSquare = new ColoredShape(new Square(20), "Blue");
        System.out.println(blueSquare.info());

        TransparentShape myCircle = new TransparentShape(
                new ColoredShape(new Circle(5), "Green"), 50
        );
        System.out.println(myCircle.info());
    }
}

interface Shape {
    String info();
}

class Circle implements Shape {

    private float radius;

    public Circle() {
    }

    public Circle(float radius) {
        this.radius = radius;
    }

    void resize(float factor) {
        radius *= factor;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}

class Square implements Shape {

    private float side;

    public Square() {
    }

    public Square(float size) {
        this.side = size;
    }

    @Override
    public String info() {
        return "A Square with side " + side;
    }
}

class ColoredShape implements Shape {

    private Shape shape;
    private String color;

    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape implements Shape {

    private Shape shape;
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + "% transparency";
    }
}