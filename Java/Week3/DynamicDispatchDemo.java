public class DynamicDispatchDemo {
    public static void main(String[] args) {
        // Array of type Employee to hold mixed objects (Polymorphism) [cite: 1063]
        Employee[] staff = new Employee[2];
        
        // 1. Employee reference to an Employee object
        staff[0] = new Employee("Alice", 50000.00); 
        
        // 2. Employee reference to a Manager object (Valid substitution) [cite: 1583]
        staff[1] = new Manager("Bob (Manager)", 80000.00, "Carol"); 

        float bonusRate = 10.0f; // 10% bonus
        
        System.out.println("--- Dynamic Dispatch Demo (Original Salary) ---");
        for (Employee e : staff) {            
            System.out.printf("Name: %s (%s), Bonus: $%.2f\n", e.getName(), role, bonus);
        }
        
        System.out.println("--- Dynamic Dispatch Demo (After 10% Bonus) ---");
        for (Employee e : staff) {
            // Dynamic Dispatch: Calls the correct getBonus() based on the object's actual type
            double bonus = e.getBonus(bonusRate); 
            
            String role = (e instanceof Manager) ? "Manager" : "Employee";
            
            System.out.printf("Name: %s (%s), Bonus: $%.2f\n", e.getName(), role, bonus);
        }
        
        // Example of type casting to access subclass-specific methods [cite: 1228]
        if (staff[1] instanceof Manager) {
            Manager m = (Manager) staff[1];
            System.out.println("Manager Bob's Secretary: " + m.getSecretary());
        }
    }
}