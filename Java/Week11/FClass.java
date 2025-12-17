package Java.Week11;

import java.util.*;
class PrlTest extends Thread{
        Map<String, Integer> mp;
        Thread th;
        PrlTest(Map<String, Integer> ic,Thread t){
             this.mp = ic;
             this.th = t;
        }
        public void run(){
              try {
                   th.join();
              } catch (InterruptedException ex) {}
              mp.put("D", 4);
       }
}
public class FClass{
      public static void main (String[] args) throws InterruptedException{
            Thread t1 = Thread.currentThread();
            Map<String, Integer> icMap = new LinkedHashMap<String, Integer>();
            String[] str = {"A", "B", "C"};
            Integer[] arr = {1, 2, 3};
            for(int i = 0; i < str.length; i++){
                 icMap.put(str[i], arr[i]);
            }
            PrlTest t2 = new PrlTest(icMap, t1);
            t2.start();
            t2.join();
            for(Map.Entry m : icMap.entrySet()){
                   System.out.println(m.getKey() + " => "+ m.getValue());
            }
     }
}