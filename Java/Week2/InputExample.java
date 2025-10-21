import java.util.Scanner;

// InputExample.java
public class InputExample {
    public static void main(String[] args) {
        // Initialize Scanner to read from standard input [cite: 317]
        Scanner in = new Scanner(System.in); 

        // Read a String (Word)
        System.out.print("Enter your name (single word): ");
        String name = in.next(); 

        // Read an Integer
        System.out.print("Enter your age: ");
        int age = in.nextInt(); // Reads and converts the next token to int [cite: 320]

        // Consume the rest of the line (important after nextInt/next)
        in.nextLine(); 

        // Read a Full Line (Sentence)
        System.out.print("Enter a favorite motto: ");
        String motto = in.nextLine(); // Reads the entire line [cite: 319]
        
        // Output using System.out.println and System.out.printf [cite: 326, 344]
        System.out.println("\n--- Output ---");
        System.out.printf("Hello, %s! You are %d years old.\n", name, age);
        System.out.println("Your motto is: " + motto);

        in.close();
    }
}