import java.util.Scanner;

public class InputScanner {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        String name = sc1.nextLine();
        int age = sc1.nextInt();

        System.out.println("Hello" + name + ",\nYour age is: " + age);

        Scanner sc2 = new Scanner(System.in);
        String username = sc2.nextLine();
        int password = sc2.nextInt();

        System.out.println(username);
        System.out.println(password);

    }
}
