package Java.Week7;

public class AssertionFailes {
    public static double sqrt(double x){
        assert x >= 0 : "sqrt called with negative number: " + x;
        return Math.sqrt(x);
    }
    public static void main(String[] args) {
        int a = -10;
        System.out.println(sqrt(a));
    }
}
