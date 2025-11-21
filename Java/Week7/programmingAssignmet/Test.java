import java.util.*;
//Define DivisionException class here

class DivisionException extends Exception {
    public DivisionException(String message) {
        super(message);
    }
}

public class Test {

    //Define divide(int a, int b) here
    public static void divide(int a, int b) throws DivisionException {
        if (b == 3) {
            throw new DivisionException("Division by 3 is not allowed");
        }
        int result = a / b;
        System.out.println(result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        //call divide method here
        try {
            divide(x, y);
        } catch (DivisionException e) {
            System.out.println(e.getMessage());
        }
   }
} 