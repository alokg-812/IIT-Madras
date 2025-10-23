class Employee{
    String name;
    int salary;

    public Employee(String name, int salary){
        this.name = name;
        this.salary = salary;
    }

    public double bonus(float percent){
        return (percent / 100.0) * salary;
    }
    public String toString(){
        return "Name: " + name + ", Salary: " + salary;
    }
}

class Manager extends Employee{
    String secretary;
    
    public Manager(String name, int salary, String secretary){
        super(name, salary);
        this.secretary = secretary;
    }

    public double bonus(float percent){
        return 1.5 * super.bonus(percent);
    }

    public String toString(){
        return "Name: " + name + ", Salary: " + salary + ",Secretary: " + secretary;
    }
}

public class EmployeeArray {
    public static void main(String[] args) {
        Employee[] emparray = new Employee[2];
        Employee e = new Employee("Akhil", 1000);
        Manager m = new Manager("Dev", 2000, "Ram");

        emparray[0] = e;
        emparray[1] = m;

        System.out.println("Before Bonus");
        for(int i = 0 ; i < emparray.length ;i++){
            System.out.println("Employee " + (i+1) + ": " + emparray[i]);
        }

        System.out.println("\nAfter Bonus");
        for(int i = 0 ; i < emparray.length ;i++){
            System.out.println("Employee " + (i+1) + "Bonus : " + emparray[i].bonus(0.5f));
        }
    }
}
