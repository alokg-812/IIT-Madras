package Java.Week11;

import java.util.concurrent.locks.*;
class DemoLock{
          ReentrantLock lck = new ReentrantLock();
          public void display(String name){
                lck.lock();
                try{
                     for(int i = 1; i < 4; i++){
                     System.out.print(i + ":" + name + " ");
                     }
                     System.out.print("\n");
                }
                finally{
                      lck.unlock();
                }
           }
}
class Example extends Thread{
       DemoLock l_obj;
       String str;
       Example(DemoLock o, String str){
             this.l_obj = o;
             this.str = str;
       }
       public void run(){
             l_obj.display(str);
       }
}
public class FClass1{
      public static void main(String[] args){
            DemoLock obj = new DemoLock();
            Example e = new Example(obj, "Sun");
            Example e2 = new Example(obj, "Moon");
            Example e3 = new Example(obj, "Earth");
            e.start();
            e2.start();
            e3.start();
      }
}