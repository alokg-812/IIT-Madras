import java.io.*;
import java.util.Scanner;

public class StreamChainingExample {
    public static void main(String[] args) {
        // I/O operations must handle exceptions, usually IOException
        try {
            // 1. Connect raw byte streams to files
            var fin = new FileInputStream("input.txt"); 
            var fout = new FileOutputStream("output.txt"); // Overwrites by default

            // 2. Wrap raw streams with text interpretation streams
            var in = new Scanner(fin);           // Scanner reads the text
            var out = new PrintWriter(fout);     // PrintWriter writes the text

            // 3. Process the data
            System.out.println("Copying file...");
            while (in.hasNextLine()) { // Check if there's another line
                String line = in.nextLine();
                out.println(line); // Writes the line and a newline character
            }

            // Must close streams to release resources (manual close is dangerous, use try-with-resources)
            in.close();
            out.close(); 
            System.out.println("Copying complete.");

        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }
}