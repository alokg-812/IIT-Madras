package Java.Week4;

abstract class Parent{
    public Parent(){
        System.out.println("This is Parent class constructor");
    }
    public void sayHello(){
        System.out.println("Hello method");
    }

    abstract public void greet1();
    abstract public void greet2();
    public void greet3(){
        System.out.println("Greet 3");
    };
}

class Child1 extends Parent{

    @Override
    public void greet1(){
        System.out.println("Child 1 greet1 Good Morning");
    }

    @Override
    public void greet2(){
        System.out.println("Child 2 greet2 Good Morning");
    }
}

/*
 * This will generate error as child2 is neither declared abstract nor has all the methods overridden
class Child2 extends Parent{
    @Override
    public void greet1(){
        System.out.println("Child 2 greet 1 GM");
    }
}
 */

abstract class Child3 extends Parent{

    public void morning(){
        System.out.println("Child 3 saying Good Morning");
    }
}

public class Abstract1 {
    public static void main(String[] args) {
        // Parent bs = new Parent(); // This will generate error b'coz abstract class Cannot be instantiated
        Child1 c1 = new Child1();
        // Child3 c3 = new Child3(); // This also will generate error
        c1.greet1();
        c1.greet2();
        Parent p1 = new Child1(); // allowed 
    }
}
