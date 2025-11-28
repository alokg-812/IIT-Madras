import java.util.*;
class Employee{
          int id;
          String name;
          int service;
          Employee(int id, String name, int service){
              this.id = id;
              this.name = name;
              this.service = service;
          }
          public int getId(){
              return id;
          }
          public String getName(){
              return name;
          }
          public int getService(){
              return service;
         }
         public String toString(){
              return "Employee{" + "id=" + id + ", Name=" + name + ", Service=" +
                          service + "}";

        }
}
public class FClass{
       public static void main(String[] args){
              var employee=new ArrayList<Employee>();
              employee.add(new Employee(1,"Mercury",10));
              employee.add(new Employee(2,"Venus",5));
              employee.add(new Employee(3,"Earth",3));
              employee.add(new Employee(6,"Saturn",2));
              employee.add(new Employee(7,"Uranus",10));
              employee.add(new Employee(8,"Neptune",10));
              Map<Integer, List<Employee>> map = employee.stream().
                        collect(Collectors.groupingBy(i->i.getService()));
              System.out.println(map.get(10).get(1));
       }
}