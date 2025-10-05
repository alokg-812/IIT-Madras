import java.util.*;

class Employee
{
    String eid;
    String ename;
    String eprojects[];
 //Define all the required methods here
	public Employee(String id, String name, String[] projects) {
		this.eid = id;
		this.ename = name;
		this.eprojects = projects;
	}
	public Employee(Employee other) {
		this.eid = other.eid;
		this.ename = other.ename; 
		if (other.eprojects != null) {
			this.eprojects = new String[other.eprojects.length];
			for (int i = 0; i < other.eprojects.length; i++) {
				this.eprojects[i] = other.eprojects[i];
			}
		}
	}
	public void display() {
		System.out.println("id:" + this.eid);
		System.out.println("name:" + this.ename);
		System.out.println("projects:");
		if (this.eprojects != null) {
			for (int i = 0; i < this.eprojects.length; i++) {
				if (this.eprojects[i] != null) {
					System.out.print(this.eprojects[i] + ":");
				}
			}
		} else {
			System.out.print("None");
		}
		System.out.println();
	}

	
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
