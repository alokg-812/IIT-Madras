import java.util.*;
import java.text.DecimalFormat;
class ComplexNum<T extends Number> {
    private T r, i;
    ComplexNum(T r, T i) {
        this.r = r;
        this.i = i;
    }

    public <U extends Number> ComplexNum<Double> add(ComplexNum<U> other) {
        double realSum = this.r.doubleValue() + other.r.doubleValue();
        double imagSum = this.i.doubleValue() + other.i.doubleValue();
        return new ComplexNum<Double>(realSum, imagSum);
    }

    private String formatNumber(double val) {
        if (val == Math.floor(val)) {
            return String.format("%.1f", val);
        } else {
            DecimalFormat df = new DecimalFormat("#.##########");
            return df.format(val);
        }
    }
    @Override
    public String toString() {
        double real = r.doubleValue();
        double imag = i.doubleValue();
        String realStr = formatNumber(real);
        String imagStr = formatNumber(Math.abs(imag));

        if (imag >= 0)
            return realStr + " + " + imagStr + "i";
        else
            return realStr + " - " + imagStr + "i";
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