package Java.QuizPractice;

class Customer{
    private String name;
    private int customerId;
    public Customer(String name, int customerId){
        this.name = name;
        this.customerId = customerId;
    }
    public boolean equals(Object ob){
        if(ob instanceof Customer){
            Customer c = (Customer) ob;
            if(this.name == c.name && this.customerId == c.customerId){
                return true;
            }
        }
        return false;
    }
}
public class TestCustomer {
    public static void main(String[] args) {
        Customer c1 = new Customer("Ravi", 101);
        Customer c2 = new Customer("Ravi", 101);
        System.out.println(c1.equals(c2));
    }
}
