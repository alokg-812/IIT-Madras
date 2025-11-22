import java.util.*;


public class MapIterator {
    public static void main(String[] args) {
        Map<String, Integer> ageData = new HashMap<>();
        ageData.put("Alpha", 42);
        ageData.put("Beta", 18);
        ageData.put("Gama", 73);
        ageData.put("Delta", 61);

        //Iterating Over keys
        for(String name: ageData.keySet()){
            System.out.println("Name: " + name);
        }

        //Iterating Over Values only
        for(Integer age : ageData.values()){
            System.out.println("Age: " + age);
        }

        //Iterating Over entities:
        for(Map.Entry<String, Integer> entry: ageData.entrySet()){
            String name = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(name + ": " + age);
        }
    }
}
