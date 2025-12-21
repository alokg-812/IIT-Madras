package Java.QuizPractice;

class Beverage {
    public void ingredients() {
        System.out.println("Basic ingredients");
    }

    public void price() {
        System.out.println("Price not set");
    }
}

class Coffee extends Beverage {
    public void ingredients() {
        System.out.println("Contains coffee beans and water");
    }

    public void price() {
        System.out.println("Price: 100");
    }
}

class Latte extends Coffee {
    public void ingredients() {
        System.out.println("Contains coffee beans, milk, and water");
    }

    public void maker() {
        System.out.println("Makes latte");
    }
}

public class BeverageTest {
    public static void main(String[] args) {
        Coffee drink = new Latte();   // LINE 1
        drink.ingredients();
        drink.price();
        drink.maker();                // LINE 2
    }
}
