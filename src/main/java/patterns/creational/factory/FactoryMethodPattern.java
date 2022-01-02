package patterns.creational.factory;

public class FactoryMethodPattern {

    public static void main(String[] args) {

    }
}

enum CoordinateSystem {
    CARTESIAN,
    POLAR
}

class Point {

    private double x;
    private double y;

    /**
     *
     * @param a i x if cartesian or rho if polar
     * @param b ...
     * @param cs ...
     */
    public Point(double a, double b, CoordinateSystem cs) {
        switch (cs) {
            case CARTESIAN -> {
                this.x = x;
                this.y = y;
            }
            case POLAR -> {
                x = a * Math.cos(b);
                y = a * Math.sin(b);
            }
        }
    }
}