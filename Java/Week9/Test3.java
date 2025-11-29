import java.util.*;
import java.util.stream.*;

public class Test3{
     public static void main(String[] args){
           var list=new ArrayList<Integer>();
           for(int i=10;i>0;i--){
                 list.add(i);
           }
           IntSummaryStatistics stat = list.stream().
                 collect(Collectors.summarizingInt(x->x));
           System.out.println(stat.getAverage()+"\n"+
                  stat.getSum()+"\n"+stat.getMax()+"\n"+stat.getMin());

      }
}