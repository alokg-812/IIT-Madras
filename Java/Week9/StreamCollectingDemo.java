import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.IntSummaryStatistics;
import java.util.TreeSet;
import java.util.function.Function;

// Simple class for demonstration
class Person {
    private final int id;
    private final String name;
    private final int age;

    public Person(int id, String name, int age) {
        this.id = id; this.name = name; this.age = age;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String toString() { return name + " (" + id + ")"; }
}

public class StreamCollectingDemo {
    public static void main(String[] args) {
        Stream<Person> peopleStream = Stream.of(
            new Person(101, "Alice", 30),
            new Person(102, "Bob", 25),
            new Person(103, "Alice", 35), // Duplicate Name
            new Person(104, "Charlie", 25)
        );

        // --- 1. Collecting to Basic Collections (List & Set) ---
        List<Person> peopleList = peopleStream.collect(Collectors.toList());
        System.out.println("1. List Result: " + peopleList);
        
        // Recreate stream for next operation (streams are consumed after one terminal operation)
        peopleStream = Stream.of(new Person(101, "Alice", 30), new Person(102, "Bob", 25), new Person(103, "Alice", 35), new Person(104, "Charlie", 25));

        // --- 2. Collecting to Map with Duplicate Key Handling ---
        // Using name as key, ID as value. 'Alice' is duplicated.
        Map<String, Integer> nameToIDMap = peopleStream.collect(
            Collectors.toMap(
                Person::getName, 
                Person::getId,
                (existing, replacement) -> existing // Merge: If duplicate, keep the existing ID
            )
        );
        System.out.println("2. Map (with merge): " + nameToIDMap);
        
        // --- 3. Summarizing Statistics ---
        Stream<Person> summaryStream = Stream.of(
            new Person(101, "Alice", 30), new Person(102, "Bob", 25), new Person(103, "Alice", 35), new Person(104, "Charlie", 25)
        );
        
        IntSummaryStatistics ageStats = summaryStream.collect(
            Collectors.summarizingInt(Person::getAge) // Using age as the number field
        );
        System.out.println("3. Age Summary - Average: " + ageStats.getAverage() + ", Max: " + ageStats.getMax());

        // --- 4. Grouping and Partitioning ---
        Stream<Person> groupingStream = Stream.of(
            new Person(101, "Alice", 30), new Person(102, "Bob", 25), new Person(103, "Alice", 35), new Person(104, "Charlie", 25)
        );
        
        // Grouping: Group all people by their age
        Map<Integer, List<Person>> peopleByAge = groupingStream.collect(
            Collectors.groupingBy(Person::getAge)
        );
        System.out.println("4a. Grouped By Age (25): " + peopleByAge.get(25)); // [Bob (102), Charlie (104)]

        // Recreate stream
        groupingStream = Stream.of(new Person(101, "Alice", 30), new Person(102, "Bob", 25), new Person(103, "Alice", 35), new Person(104, "Charlie", 25));
        
        // Partitioning: Split people into those named 'Alice' and those who aren't
        Map<Boolean, List<Person>> alicePartition = groupingStream.collect(
            Collectors.partitioningBy(p -> p.getName().equals("Alice"))
        );
        System.out.println("4b. Partition (True/Alice): " + alicePartition.get(true)); 
    }
}