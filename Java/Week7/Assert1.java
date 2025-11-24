package Java.Week7;

public class Assert1 {

    public static double sqrt(double x){
        assert x >= 0 : "sqrt called with negative number: " + x;
        return Math.sqrt(x);
    }
    public static void main(String[] args) {
        int a = 100;
        System.out.println(sqrt(a));
    }
}
