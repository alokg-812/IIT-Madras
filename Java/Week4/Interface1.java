package Java.Week4;


// Interface definition
interface Animal {
    void eat();    // abstract method (no body)
    void sleep();  // abstract method (no body)
}

// Class implementing the interface
class Dog implements Animal {
    // Implementing the eat() method
    public void eat() {
        System.out.println("Dog eats bones");
    }

    // Implementing the sleep() method
    public void sleep() {
        System.out.println("Dog sleeps in the kennel");
    }

    // Additional method (not from the interface)
    public void bark() {
        System.out.println("Dog barks loudly");
    }
}

// Main class to test
public class Interface1 {
    public static void main(String[] args) {
        Animal a = new Dog();  // Reference of interface, object of Dog
        a.eat();               // Calls Dog's eat()
        a.sleep();             // Calls Dog's sleep()

        // To access Dog-specific methods:
        Dog d = new Dog();
        d.bark();
        Animal d1 = new Dog();
        ((Dog)d1).bark();
    }
}
