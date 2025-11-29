import java.util.*;

class SumComputes implements Runnable{
      ArrayList<Integer> half_batch = new ArrayList();
      int max;
      public SumComputes(ArrayList<Integer> hb) {
          half_batch = (ArrayList<Integer>)hb.clone();
          max = -1;
      }
      public void run() {
          max = Collections.max(half_batch);
      }
      public int getMax() {
          return max;
      }
}
public class MaxMarks {
     public static void main(String[] args) {
         int max = -1;

         //Accept the marks into
        //two ArrayList<Integer> objects batch1 and batch2

        SumComputes sc1 = new SumComputes(batch1);
        SumComputes sc2 = new SumComputes(batch2);
        Thread t1 = new Thread(sc1);
         Thread t2 = new Thread(sc2);
         t1.start();
         t2.start();
          //Line 1
          if (sc1.getMax() >= sc2.getMax()) {
               max = sc1.getMax();
          }
          else {
                 max = sc2.getMax();
          }
          System.out.println(max);
     }
}
