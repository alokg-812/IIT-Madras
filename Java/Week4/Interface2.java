package Java.Week4;

interface Bicycle{
    void applyBrake(int decreament);
    void speedUp(int increament);
}

class Avon implements Bicycle{
    int speed = 7;
    
    public void applyBrake(int decreament){
        System.out.println("Initial Speed: "+ speed);
        System.out.println("Applying Brake");
        speed = speed-decreament;
        System.out.println("Final Speed: "+ speed);
    }

    public void speedUp(int increament){
        System.out.println("Initial Speed: "+ speed);
        System.out.println("Speeding Up");
        speed = speed + increament;
        System.out.println("Final Speed: "+ speed);
    }
}
public class Interface2 {
    public static void main(String[] args) {
        Avon a1 = new Avon();
        a1.applyBrake(2);
        a1.speedUp(4);
    }
}
