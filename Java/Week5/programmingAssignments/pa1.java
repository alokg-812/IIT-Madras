import java.util.*;
//Add your code for ComplexNum here
class ComplexNum<T extends Number> {
    private double T r, i;
    ComplexNum(T r, T i) {
        this.r = r;
        this.i = i;
    }
    public <U extends Number> ComplexNum<Double> add(ComplexNum<U> other) {
        double realSum = this.r.doubleValue() + other.r.doubleValue();
        double imagSum = this.i.doubleValue() + other.i.doubleValue();
        return new ComplexNum<Double>(realSum, imagSum);
    }
    @Override
    public String toString() {
        if (i.doubleValue() >= 0)
            return r + " + " + i + "i";
        else
            return r + " - " + Math.abs(i.doubleValue()) + "i";
    }
}

class FClass{
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1, n2;
        double d1, d2;
        n1 = sc.nextInt();
        n2 = sc.nextInt();
        d1 = sc.nextDouble();
        d2 = sc.nextDouble();
        ComplexNum<Integer> c1 = new ComplexNum<Integer>(n1, n2);
        ComplexNum<Double> c2 = new ComplexNum<Double>(d1, d2);
        ComplexNum<Double> c3 = c1.add(c2);
        System.out.println(c1 + " + " + c2 + " = " + c3);
    }
}