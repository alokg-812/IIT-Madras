import java.io.Console;

public class InputConsole {
    public static void main(String []args){
        Console cons1 = System.console();
        String name = cons1.readLine("Enter your name: ");
        int age = Integer.parseInt(cons1.readLine("Enter your age: "));

        System.out.println("Hello" + name + ",\nYour age is: " + age);

        Console cons2 = System.console();
        String username = cons2.readLine("User name: ");
        char[] password = cons2.readPassword("Password: ");

        System.out.println(username);
        System.out.println(password);
    }

}
