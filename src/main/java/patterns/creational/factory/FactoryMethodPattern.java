package patterns.creational.factory;

public class FactoryMethodPattern {

    public static void main(String[] args) {

        Point point = Point.newPolarPoint(2, 3);
    }
}

class Point {

    private double x;
    private double y;

    private Point(double a, double b) {
        this.x = x;
        this.y = y;
    }

    public static Point newCartesianPoint(double x, double y) {
        return new Point(x, y);
    }

    public static Point newPolarPoint(double rho, double theta) {
        return new Point(
                rho * Math.cos(theta),
                rho * Math.sin(theta));
    }
}