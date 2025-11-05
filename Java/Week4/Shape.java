package Java.Week4;

abstract class Shapes {
    public abstract double perimeter();
}

class Rectangle extends Shapes {
    int length, breadth;

    public Rectangle(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public double perimeter() {
        return 2.0 * (length + breadth);
    }
}

class Square extends Shapes {
    int side;

    public Square(int side) {
        this.side = side;
    }

    public double perimeter() {
        return 4.0 * side;
    }
}

class Circle extends Shapes {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double perimeter() {
        return 2.0 * Math.PI * radius;
    }
}

public class Shape {
    public static void main(String[] args) {
        Shapes shapearray[] = new Shapes[3];
        double sizearr[] = new double[3];

        shapearray[0] = new Rectangle(3, 4); // perimeter = 14
        shapearray[1] = new Circle(2.5); // perimeter = 2*PI*2.5
        shapearray[2] = new Square(5); // perimeter = 20

        for (int i = 0; i < shapearray.length; i++) {
            sizearr[i] = shapearray[i].perimeter();
            System.out.println("Perimeter of shape " + i + " = " + sizearr[i]);
        }
    }
}
