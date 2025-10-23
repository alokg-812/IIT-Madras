class One{
    
    public void name(){
        System.out.println("My name is One");
    }
    
    public void greet(){
        System.out.println("Good Morning");
    }
}

class Two extends One{

    public void name(){
        System.out.println("My name is Two");
    }

    public void swagat(){
        System.out.println("Namaste!");
    }

}

public class DynamicDispatch{
    public static void main(String[] args) {
        One obj;
        obj = new One();
        obj.greet();
        obj.name();
        obj = new Two();
        obj.name();
        // obj.swagat();
    }
}