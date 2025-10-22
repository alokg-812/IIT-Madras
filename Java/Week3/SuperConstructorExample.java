// Parent class
class Vehicle {
    String brand;
    int year;

    // Constructor of parent class
    Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
        System.out.println("Vehicle constructor called");
    }

    void showDetails() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}

// Child class
class Car extends Vehicle {
    String model;

    // Constructor of child class
    Car(String brand, int year, String model) {
        // Call parent class constructor using super()
        super(brand, year);
        this.model = model;
        System.out.println("Car constructor called");
    }

    void showCarDetails() {
        // Reuse parent method using super
        super.showDetails();
        System.out.println("Model: " + model);
    }
}

// Main class
public class SuperConstructorExample {
    public static void main(String[] args) {
        Car c1 = new Car("Toyota", 2022, "Innova");
        c1.showCarDetails();
    }
}
