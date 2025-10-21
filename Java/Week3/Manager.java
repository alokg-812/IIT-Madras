public class Manager extends Employee {
    private String secretary; // Additional feature [cite: 1441]

    public Manager(String name, double salary, String secretary) {
        // Use super() to initialize the parent's (Employee's) private variables [cite: 1542]
        super(name, salary); 
        this.secretary = secretary;
    }

    // Overridden method [cite: 992]
    @Override
    public double getBonus(float percent) {
        // Manager's bonus is 1.5x the standard Employee bonus [cite: 989]
        return 1.5 * super.getBonus(percent); // Calls Employee's getBonus() via super
    }

    // New method specific to Manager
    public String getSecretary() {
        return secretary;
    }
}