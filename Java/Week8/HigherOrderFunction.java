package Java.Week8;
import java.util.function.IntUnaryOperator;

class SumUtils {
    public static int sum(int a, int b) { return a + b; }
    public static int sumWithOperation(int a,int b,IntUnaryOperator operation){
        return sum(operation.applyAsInt(a), operation.applyAsInt(b));
    }
}

public class HigherOrderFunction {
    public static void main(String[] args) {
        System.out.println(SumUtils.sum(2, 3));
        System.out.println(SumUtils.sumWithOperation(2, 3, x -> x * x));
        System.out.println(SumUtils.sumWithOperation(2, 3, x -> x * x * x));
    }
}
