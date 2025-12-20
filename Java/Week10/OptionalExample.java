package Java.Week10;
import java.util.Optional;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class OptionalExample {

    // Helper function that might return a User or null (old style)
    public static String oldLookup(String id) {
        if (id.equals("admin")) {
            return "Admin User Data"; // Present
        }
        return null; // Missing
    }

    public static void main(String[] args) {
        Random rand = new Random();
        
        // --- 1. Creating an Optional and Dealing with Nulls ---
        System.out.println("--- 1. Creating and Handling Nulls ---");
        
        // Use ofNullable to safely handle a potential null return
        Optional<String> maybeUser = Optional.ofNullable(oldLookup("guest"));
        
        // Handling the missing value with orElse
        String userData = maybeUser.orElse("Default Guest");
        System.out.println("User Data (orElse): " + userData); // Output: Default Guest

        // Handling the present value with ifPresent
        Optional<String> adminUser = Optional.ofNullable(oldLookup("admin"));
        adminUser.ifPresent(data -> System.out.println("Admin Present: " + data)); 
        
        // --- 2. Transforming Optional Values (map) ---
        System.out.println("\n--- 2. Transforming (map) ---");
        
        // Start with a present value
        Optional<Integer> number = Optional.of(10);
        
        // Map: Double the value if present (result is Optional<Integer>(20))
        Optional<Integer> doubled = number.map(n -> n * 2);
        System.out.println("Doubled Value: " + doubled.orElse(0)); // Output: 20

        // Map on an empty Optional (result is still empty)
        Optional<Integer> empty = Optional.empty();
        Optional<Integer> mappedEmpty = empty.map(n -> n * 2);
        System.out.println("Mapped Empty Value: " + mappedEmpty.orElse(0)); // Output: 0
        
        // --- 3. Chaining with flatMap (Concept from slide 21) ---
        System.out.println("\n--- 3. Chaining with flatMap ---");
        
        // Suppose we have an Optional that may or may not be the inverse of a number
        Optional<Double> result = inverse(2.0)
            .flatMap(OptionalExample::squareRoot); // Applies safe squareRoot to the result of safe inverse
            
        System.out.println("Inverse then SquareRoot of 2.0: " + result.orElse(Double.NaN)); // Output: 0.707...

        Optional<Double> zeroResult = inverse(0.0)
            .flatMap(OptionalExample::squareRoot);
            
        System.out.println("Inverse then SquareRoot of 0.0: " + zeroResult.orElse(Double.NaN)); // Output: NaN
    }
    
    // Example: Function returning Optional<Double>
    public static Optional<Double> inverse(Double x) {
        return (x == 0) ? Optional.empty() : Optional.of(1.0 / x);
    }

    // Example: Function returning Optional<Double>
    public static Optional<Double> squareRoot(Double x) {
        return (x < 0) ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}