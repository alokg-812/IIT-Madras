import java.io.*;

public class StreamChainingForBinaryDataWriting{
    public static void main(String[] args) {
        try (
            // Use try-with-resources to ensure streams are closed automatically
            var fout = new FileOutputStream("data.bin");
            var dout = new DataOutputStream(fout);
        ) {
            System.out.println("Writing binary data...");
            
            // Writing primitive types directly
            dout.writeInt(12345);
            dout.writeDouble(3.14159);
            dout.writeUTF("Java Binary Data"); // Writes String using UTF-8 encoding

            // flush is automatically called on closing/try-with-resources completion
            System.out.println("Binary data written to data.bin");

        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }
}