package Java.Week8;
class Employee{
    public String name;
    public Employee(String n){ this.name = n; }
    public String getName(){ return this.name; }
    public void setName(String nme){ this.name = nme; }

}
class Manager implements Cloneable{
    public String post;
    Employee emp;
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
public class DeepCloning {
    public static void main(String[] args) throws CloneNotSupportedException{
        Manager m1 = new Manager();
        m1.post = "BM";

        Employee em = new Employee(null);
        em.name = "Ram";
        m1.emp = em;
       m1.emp.name = "Shyam";
        Manager m2 = (Manager) m1.clone();
        m2.post= "AM";
        System.out.println(m1.post);
        System.out.println(m2.post);
        System.out.println("++++++++++++++++++");
        System.out.println(m1.emp.name);
        System.out.println(m2.emp.name);

        m2.emp.name = "GhanShyam";
        System.out.println(m2.emp.name);
        
    }
}
