package Java.QuizPractice;

import java.lang.management.OperatingSystemMXBean;

abstract class Operation implements Runnable{
    static int total = 10;
}
class Operation1 extends Operation{
    public void run(){
        if(total != 8){
            total = total-3;
        }
    }
}
class Operation2 extends Operation{
    public void run(){
        if(total != 7){
            total = total-2;
        }
    }
}
public class RaceConditionDecreament {
    public static void main(String[] args) {
        Operation1 op1 = new Operation1();
        Operation2 op2 = new Operation2();
        Thread t1 = new Thread(op1);
        Thread t2 = new Thread(op2);
        t1.start();
        t2.start();
        System.out.println(Operation.total);

    }
}
