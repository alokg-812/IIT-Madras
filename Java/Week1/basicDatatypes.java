public class BasicDataTypes {
    public static void main(String[] args) {
        // Primitive data types
        byte byteVar = 100;          // 8-bit integer
        short shortVar = 32000;      // 16-bit integer
        int intVar = 100000;         // 32-bit integer
        long longVar = 1000000000L;  // 64-bit integer (notice the 'L')

        float floatVar = 3.14f;      // 32-bit floating point (use 'f' at the end)
        double doubleVar = 3.14159265359; // 64-bit floating point

        char charVar = 'A';          // Single character
        boolean boolVar = true;      // true or false

        // Non-primitive data type (String)
        String strVar = "Hello, Java!";

        // Printing them
        System.out.println("byte: " + byteVar);
        System.out.println("short: " + shortVar);
        System.out.println("int: " + intVar);
        System.out.println("long: " + longVar);
        System.out.println("float: " + floatVar);
        System.out.println("double: " + doubleVar);
        System.out.println("char: " + charVar);
        System.out.println("boolean: " + boolVar);
        System.out.println("String: " + strVar);
    }
}
