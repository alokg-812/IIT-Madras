import java.util.*;

class Example{
      Optional<Integer> count;
      Example(Optional<Integer> count){
           this.count=count;
      }
      void show(){
           Optional<Integer> num=count.filter(n->n>18);
           if(num.isPresent()){
               System.out.println(num.get());
           }
           else{
                  System.out.println(count.map(n->18-n).get());
           }
      }
}
public class Test1{
      public static void main(String[] args){
           Optional<Integer> op1=Optional.of(20);
           Optional<Integer> op2=Optional.of(10);
           new Example(op1).show();
           new Example(op2).show();
      }
}

