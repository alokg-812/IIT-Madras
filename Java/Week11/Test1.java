package Java.Week11;

class BookTicket{
     int available=3;
     synchronized void extract(String name,int book){
          if (book<=available){
                 System.out.println(name + " booked " + book + " ticket.");
                 available = available - book;
          }
          else {
                 System.out.println(name + ", you can not book " + book + " ticket.");
                 System.out.println("Tickets available: " + available);
           }
      }
}
class Passenger implements Runnable{
       BookTicket bt;
       String name;
        int number;
        Passenger(BookTicket b, String n, int num){
             this.bt = b;
             this.name = n;
             this.number = num;
         }
         public void run(){
                bt.extract(name, number);
         }
}
public class Test1 {
     public static void main(String[] args){
           BookTicket obj = new BookTicket();
           BookTicket obj1 = new BookTicket();
           BookTicket obj2 = new BookTicket();
           Thread t1 = new Thread(new Passenger(obj, "Sun", 2));
           Thread t2 = new Thread(new Passenger(obj1, "Moon", 1));
           Thread t3 = new Thread(new Passenger(obj2, "Earth", 2));
            t1.start();
            t2.start();
            t3.start();
     }
}