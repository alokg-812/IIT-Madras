interface Printable{
      public default void print() {
           System.out.println("not implemented");
       }
    }

abstract class Collection{
       public void print() {
           System.out.println("no element");
       }
     }

class Queue extends Collection implements Printable{
       public void print() {
           super.print();
           System.out.println("print the queue");
       }
    }

    public class FClass{
        public static void main(String[] args) {
           Queue q = new Queue();
           q.print();
       }
    }
