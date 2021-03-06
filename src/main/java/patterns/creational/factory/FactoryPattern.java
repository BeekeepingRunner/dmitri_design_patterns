package patterns.creational.factory;

public class FactoryPattern {

    public static void main(String[] args) {

        Point point = Point.Factory.newCartesianPoint(2, 3);
    }
}

class Point {

    private double x;
    private double y;

    private Point(double a, double b) {
        this.x = x;
        this.y = y;
    }

    public static class Factory {

        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(
                    rho * Math.cos(theta),
                    rho * Math.sin(theta));
        }
    }
}
