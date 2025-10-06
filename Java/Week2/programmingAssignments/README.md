## Programming Assignment 1:
- Write a program to find the sum of the following series up to n terms. <br>
  `1² + ( 1² + 2² ) + ( 1² + 2² + 3² ) + ......... + ( 1² + 2² + .... + n² )`
### - [_**Solution:**_](https://github.com/alokg-812/IIT-Madras/blob/main/Java/Week2/programmingAssignments/pa1.java)

## Programming Assignment 2: 
- Complete the definition of the given class by defining appropriate constructors and member functions such that it is in coherence with the given main method and produce the required output.
- Given code:
  ```java
  import java.util.Scanner;
  
  class Employee{
  	    String ename;
  	    String eid;
  	    String edept;
  	    
  	    public Employee(){
  		        ename = "guest";
  	    }
  //Define the required methods
  
  
  public class FClass 
  {
  	    public static void main(String args[]) 
  	    {
  		        Scanner s = new Scanner(System.in);
  		        Employee e1 = new Employee();
  		
  		        //Enter name of the employee
  		        String name = s.nextLine();
  		
  		        //Enter id of the employee
  		        String id = s.nextLine();
  		
  		        //Enter department of the employee
  		        String dept = s.nextLine();
  		
  		        Employee e2 = new Employee(name,id,dept);
  		
  		        e1.copyDept(e2); 
  		        //Copies the department name of e2 into e1's department name.
  		
  		        e1.displayDetails();
  	    }
  }
  ```
### - [_**Solution:**_](https://github.com/alokg-812/IIT-Madras/blob/main/Java/Week2/programmingAssignments/pa2.java)

## Programming Assignment 3

- Complete the definition of the given class by defining appropriate constructors and member functions such that it is in coherence with the given main method and produce the required output
- given code:
```java
import java.util.*;

class Employee
{
    String eid;
    String ename;
    String eprojects[];

 //Define all the required methods here

	
public void mutator()
    {
        this.ename = "Mr "+ this.ename;
        this.eprojects[0] = null;
    }
    
}
public class FClass
{
    public static void main(String[] args) 
    {
        Scanner s = new Scanner(System.in);
    	   String project[] = {"P001","P002","P003"};
        //Enter the id of employee
        String id = s.nextLine();
        //Enter the name of employee
        String name = s.nextLine();
        
        Employee e1 = new Employee(id,name,project);
        Employee e2 = new Employee(e1); 
        //The copy constructor must copy all the data members. 
       
        e1.mutator();
        
        e2.display();
    }
}
```
### - [_**Solution:**_](https://github.com/alokg-812/IIT-Madras/blob/main/Java/Week2/programmingAssignments/pa3.java)







