package Java.Week11;


import java.util.logging.*;
class Example extends Thread{
       Thread t;
       Example(Thread t){
            this.t = t;
        }
        public void run(){
              try {
                   t.join();
             } catch (InterruptedException ex) {
                    Logger.getGlobal().log(Level.SEVERE, ex.getMessage());
             }
             for(int i = 1; i < 3; i++){
                       System.out.print(i + " ");
             }
       }
}
public class FClass2{
        public static void main(String[] args) throws InterruptedException{
            Thread t1 = Thread.currentThread();
             Example t2 = new Example(t1);
             t2.start();
             for(int i = 3; i <= 5; i++){
                  System.out.print(i + " ");
             }
        }
}