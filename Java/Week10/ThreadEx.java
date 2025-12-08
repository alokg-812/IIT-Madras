class Counter implements Runnable{
      boolean stopRequested = false;
      long count = 0;
      public void run() {
           while (!stopRequested) {
               count++;
               if (count==1000000) {
                   stopRequested = true;
               }
           }
      }
      public void setStop(boolean stop){
             stopRequested = stop;
      }
      public long getCount(){
             return count;
      }
}
public class ThreadEx {
      public static void main(String[] args) throws InterruptedException {
            Counter ctr = new Counter();
            Thread backgroundThread = new Thread(ctr);
             backgroundThread.start();
             Thread.sleep(1);
              ctr.setStop(true);
              System.out.println(ctr.getCount());
      }
}