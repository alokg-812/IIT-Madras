package Java.Week6;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedEg1 {
    public static void main(String[] args) {
        Map<Integer, String> names = new LinkedHashMap<Integer, String>();
        names.put(40, "four");
        names.put(10, "one");
        names.put(30, "three");
        names.put(20, "two");
        System.out.println(names);
    }
}
