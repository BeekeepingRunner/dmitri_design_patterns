package patterns.creational.factory;

public class FactoryMethodPattern {

    public static void main(String[] args) {

    }
}

class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double rho, double theta) {
        x = rho * Math.cos(theta);
        y = rho * Math.sin(theta);
    }
}