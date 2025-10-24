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
    public String getSecretary(){
        return secretary;
    }

    public double bonus(float percent){
        return 1.5 * super.bonus(percent);
    }

    public String toString(){
        return "Name: " + name + ", Salary: " + salary + ",Secretary: " + secretary;
    }
}

public class ToString {
    public static void main(String[] args) {
        Employee e = new Manager("Ram", 1000, "Shyam");
        System.out.println(e.toString());
    }
}
