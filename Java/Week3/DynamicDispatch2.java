class Animal {
    void sound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark!");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("Meow!");
    }
}

public class DynamicDispatch2 {
    public static void main(String[] args) {
        Animal a; // reference of parent class

        a = new Animal(); // object of parent class Animal
        a.sound();        //which sound? → "Some generic animal sound" 

        a = new Dog(); // object of subclass Dog
        a.sound();     // which sound? → "Bark!"

        a = new Cat(); // now the same reference points to Cat
        a.sound();     // which sound? → "Meow!"
    }
}
