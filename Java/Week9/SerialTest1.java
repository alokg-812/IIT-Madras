import java.io.*;
import java.util.ArrayList;
class Employee implements Serializable{
      transient String empName;
      static String company="ABC limited";
      double salary;
      public Employee(String empName, double salary) {
           this.empName = empName;
           this.salary = salary;
      }
}
public class SerialTest1{
      public static void main(String[] args) throws Exception{
            var fos=new FileOutputStream("Employee.txt");
            var os=new ObjectOutputStream(fos);
            ArrayList<Employee> list=new ArrayList<Employee>();
            list.add(new Employee("John",10000.00));
            list.add(new Employee("Jock",20000.00));
            os.writeObject(list);
            var fis=new FileInputStream("Employee.txt");
            var ois=new ObjectInputStream(fis);
            ArrayList<Employee> empList=(ArrayList<Employee>)ois.readObject();
            for(Employee emp:empList){
                      System.out.println(emp.empName+" "+emp.salary+" "+emp.company);
           }
      }
}