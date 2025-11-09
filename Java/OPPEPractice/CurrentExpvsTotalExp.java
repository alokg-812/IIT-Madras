package Java.OPPEPractice;
import java.util.*;

class Employee implements Comparable<Employee> {
    private int currentExp, totalExp;

    // Constructor
    public Employee(int currentExp, int totalExp) {
        this.currentExp = currentExp;
        this.totalExp = totalExp;
    }

    // toString method for required output format
    @Override
    public String toString() {
        return "Current Expereince: " + currentExp + " Total Experience: " + totalExp;
    }

    // Sorting logic
    @Override
    public int compareTo(Employee other) {
        if (this.currentExp != other.currentExp) {
            return this.currentExp - other.currentExp;
        }
        if (this.totalExp != other.totalExp) {
            return this.totalExp - other.totalExp;
        }
        return 0; // duplicate case
    }
}

public class CurrentExpvsTotalExp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> list = new ArrayList<>();

        while (sc.hasNext()) {
            int cur = sc.nextInt();
            int tot = sc.nextInt();
            list.add(new Employee(cur, tot));
        }

        Collections.sort(list);

        for (Employee e : list) {
            System.out.println(e);
        }
    }
}