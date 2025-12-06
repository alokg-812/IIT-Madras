import java.util.*;
import java.util.stream.*;
public class StreamExample2 {
      public static void main(String[] args) {
            var fruits = new ArrayList<String>();
            fruits.add("Bananas");
            fruits.add("Apples");
            fruits.add("Kiwis");
            fruits.add("Grapes");
            fruits.add("pomegranates");
            fruits.add("watermelons");
            Stream<String> fruitstream=fruits.stream();
            IntSummaryStatistics summary =
                  fruitstream.collect(Collectors.summarizingInt(String::length));
            double avgLengthFruit = summary.getAverage();
            int maxLengthFruit = summary.getMax();
            System.out.println(avgLengthFruit);
            System.out.println(maxLengthFruit);
      }
}
