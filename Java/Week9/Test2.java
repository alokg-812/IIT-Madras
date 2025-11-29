import java.util.*;
class UserException extends RuntimeException {
      public UserException(String str){
           super(str);
      }
}
class Example{
      Optional<Integer> op;
      Example(Optional<Integer> op){
           this.op=op;
      }
      void show(){
           System.out.println(op.orElseThrow(()->
                             new UserException("No number detected")));

      }
}
public class Test2{
      public static void main(String[] args){
           Optional<Integer> op=Optional.of(100);
           Optional<Integer> op2=Optional.empty();
           new Example(op).show();
           new Example(op2).show();
      }
}