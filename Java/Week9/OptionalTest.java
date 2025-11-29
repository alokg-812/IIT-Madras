import java.util.*;
class Mobile{
     String model;
     String sdcard;
     //Constructor to initialize instance variables
}
public class OptionalTest {
    public static void main(String[] args) {
         var mList=new ArrayList<Mobile>();
         mList.add(new Mobile("Vivo z1x", "128GB"));
         mList.add(new Mobile("Lenovo k8", null));
         for(Mobile obj:mList) {
             Optional<String> op1=Optional.ofNullable(obj.sdcard);
             op1.ifPresent(s->System.out.println(s));
          }
     }
}