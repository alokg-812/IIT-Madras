public class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Method to be overridden
    public double getBonus(float percent) {
        // Standard bonus calculation for a regular employee
        return (percent / 100.0) * salary; 
    }

    public String getName() {
        return name;
    }
}