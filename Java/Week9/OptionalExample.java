package Java.Week9;
import java.util.*;
import java.util.stream.Stream;
public class OptionalExample{
        public static void main(String[] args){
               Optional<Double> maxvalue =
                       Stream.generate(Math::random)
                       .limit(100)
                       .max(Double::compareTo);
               var list = new ArrayList<Double>();
               maxvalue.ifPresentOrElse(
                       v -> System.out.println("max value found"),
                       () -> System.out.println("No max")
               );
         }
}
