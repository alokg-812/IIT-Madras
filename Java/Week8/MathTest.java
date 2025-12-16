package Java.Week8;

class SumUtils{
    public static int sum(int a, int b){
        return a+b;
    }
    public static int sumOfSquare(int a, int b){
        return sum((a*a),(b*b));
    }
    public static int sumOfCube(int a, int b){
        return sum((a*a*a),(b*b*b));
    }
}
public class MathTest {
    public static void main(String[] args) {
        System.out.println(SumUtils.sum(2,3)); 
        System.out.println(SumUtils.sumOfSquare(2,3)); 
        System.out.println(SumUtils.sumOfCube(2,3)); 
    }
}
