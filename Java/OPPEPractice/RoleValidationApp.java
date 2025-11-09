package Java.OPPEPractice;
import java.util.*;

class User {
    String name;

    User(String n) {
        this.name = n;
    }

    public String validateAccess() {
        return name + " has user access";
    }
}

class Admin extends User {
    Admin(String n) {
        super(n);
    }

    @Override
    public String validateAccess() {
        return name + " has admin access";
    }
}

class Member extends User {
    Member(String n) {
        super(n);
    }

    @Override
    public String validateAccess() {
        return name + " has member access";
    }
}

class Guest extends User {
    Guest(String n) {
        super(n);
    }

    @Override
    public String validateAccess() {
        return name + " has guest access";
    }
}

public class RoleValidationApp {

    public static void processUser(User u, ArrayList<String> message) {
        message.add(u.validateAccess());
    }

    public static void main(String[] args) {
        ArrayList<String> messageList = new ArrayList<>();

        User u1 = new Admin("Alice");
        User u2 = new Member("Bob");
        User u3 = new Guest("Charlie");

        processUser(u1, messageList);
        processUser(u2, messageList);
        processUser(u3, messageList);

        for (String str : messageList) {
            System.out.println(str);
        }
    }
}
