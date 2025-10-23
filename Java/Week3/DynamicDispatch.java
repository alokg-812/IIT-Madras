class Phone{
    
    public void name(){
        System.out.println("My name is Phone");
    }
    
    public void greet(){
        System.out.println("Good Morning");
    }
}

class SmartPhone extends Phone{

    public void name(){
        System.out.println("My name is SmartPhone");
    }

    public void swagat(){
        System.out.println("Namaste!");
    }

}

public class DynamicDispatch{
    public static void main(String[] args) {
        // Phone phone1 = new Phone();
        // SmartPhone sphone1 = new SmartPhone();
        // phone1.greet();
        // phone1.name();
        // phone1 = new SmartPhone();
        // phone1.name();
        // phone1.swagat();
        Phone phone2 = new SmartPhone();
        // SmartPhone phone3 = new Phone(); // error | Not allowed
        
    }
}
