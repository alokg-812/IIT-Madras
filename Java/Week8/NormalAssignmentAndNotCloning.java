package Java.Week8;

class Employee{
    private String name;
    private double salary;

    public Employee(String n, double sal){
        this.name = n;
        this.salary = sal;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String nme){
        this.name = nme;
    }
}

public class NormalAssignmentAndNotCloning {
    public static void main(String[] args) {
        Employee e1 = new Employee("Ram", 10);
        Employee e2 = e1;
        e2.setName("Shyam");
        System.out.println(e1.getName());
        System.out.println(e2.getName());
    }    
}
