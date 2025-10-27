public interface NumberType{
          public double norm();
          public static void print(double x) {
                System.out.println("norm is : " + x);
          }
    }

    public class ComplexNum implements NumberType{
          private int r, i;
          public ComplexNum(int r_, int i_) {
                 r = r_;
                 i = i_;
          }
          public double norm() {
               return Math.sqrt(r*r + i*i);
          }
    }
    public class FClass3{
         public static void main(String[] args) {
               ComplexNum c = new ComplexNum(3, 4);
               NumberType.print(c.norm());
         }
    }
