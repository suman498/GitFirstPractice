package oops_parctice;

// Define an interface named Shape
interface Shape {
    double calculateArea(); // Abstract method for
    // calculating the area
}

// Implement the interface in a class named Circle
class Circle implements Shape {
    private double radius;

    // Constructor for Circle
    public Circle(double radius) { this.radius = radius; }

    // Implementing the abstract method from the Shape
    // interface
    public double calculateArea()
    {
        return Math.PI * radius * radius;
    }
}

// Implement the interface in a class named Rectangle
class Rectangle implements Shape {
    private double length;
    private double width;

    // Constructor for Rectangle
    public Rectangle(double length, double width)
    {
        this.length = length;
        this.width = width;
    }

    // Implementing the abstract method from the Shape
    // interface
    public double calculateArea() { return length * width; }
}

// Main class to test the program
public class interface_ex {
    public static void main(String[] args)
    {
        // Creating instances of Circle and Rectangle
        Circle myCircle = new Circle(5.0);
        Rectangle myRectangle = new Rectangle(4.0, 6.0);

        // Calculating and printing the areas
        System.out.println("Area of Circle: "
                + myCircle.calculateArea());
        System.out.println("Area of Rectangle: "
                + myRectangle.calculateArea());
    }
}
