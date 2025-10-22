class Animal {
    void animalNature() {
        System.out.println("Some generic animal nature");
    }
}

class Deer extends Animal {
    @Override
    void animalNature() {
        System.out.println("Deer is a herbivore animal");
    }
}

class Tiger extends Animal {
    @Override
    void animalNature() {
        System.out.println("Tiger is an carnivore animal");
    }
}

public class PolymorphismExample {
    public static void main(String[] args) {
        // Parent class reference, but different child class objects
        Animal a1 = new Animal();
        Animal a2 = new Deer();
        Animal a3 = new Tiger();

        // Calls are decided at runtime
        a1.animalNature(); // Animal's version
        a2.animalNature(); // Dog's version
        a3.animalNature(); // Cat's version
    }
}
