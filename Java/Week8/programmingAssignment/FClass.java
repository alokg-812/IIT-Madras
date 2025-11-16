import java.util.*;
import java.util.stream.*;
//define class Employee
class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " : " + department + " : " + (int)salary;
    }
}
class FClass{
    //define method query
    public static Stream<Employee> query(List<Employee> list, String dept, double sal) {
        return list.stream()
                   .filter(e -> e.getDepartment().equals(dept)
                             && e.getSalary() >= sal);
    }

    
 public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var eList = new ArrayList<Employee>();
        eList.add(new Employee("Jack", "HR", 30000));
        eList.add(new Employee("Aria", "HR", 40000));
        eList.add(new Employee("Nora", "IT", 50000));
        eList.add(new Employee("Bella", "IT", 60000));
        eList.add(new Employee("Jacob", "IT", 70000));
        eList.add(new Employee("James", "HR", 80000));
        String d = sc.next();       //read department
        double s = sc.nextInt();    //read salary
		
        var st = query(eList, d, s);
        st.forEach(n -> System.out.println(n + " "));
    }
}