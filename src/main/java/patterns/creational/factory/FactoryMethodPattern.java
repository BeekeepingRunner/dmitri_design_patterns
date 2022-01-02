package patterns.creational.factory;

public class FactoryMethodPattern {

    public static void main(String[] args) {

        Point point = PointFactory.newCartesianPoint(2, 3);
        Point point1 = new Point(4, 5);
    }
}

class Point {

    private double x;
    private double y;

    public Point(double a, double b) {
        this.x = x;
        this.y = y;
    }
}

class PointFactory {

    public static Point newCartesianPoint(double x, double y) {
        return new Point(x, y);
    }

    public static Point newPolarPoint(double rho, double theta) {
        return new Point(
                rho * Math.cos(theta),
                rho * Math.sin(theta));
    }
}