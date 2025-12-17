package Java.QuizPractice;
interface FuelDispenser{
    public default void dispenseFuel(){
        System.out.println("Fuel is dispensing");
    }
}
interface BillGenerator{
    public default void generateBill(){
        System.out.println("Bill is generating");
    }
}
class PetrolPump implements FuelDispenser,BillGenerator {
    public void dispenseFuel(){
        System.out.println("Fuel Dispensed");
    }
}
public class TestPetrolPump {
    public static void main(String[] args) {
        FuelDispenser fd = new PetrolPump();
        fd.dispenseFuel();
        fd.generateBill();
        
    }
}
