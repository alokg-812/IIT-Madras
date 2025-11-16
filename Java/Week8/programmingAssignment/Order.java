import java.util.*;
//Define classes Items, Customer
class Items implements Cloneable {
    public String[] item;

    public Items(String[] item) {
        // Deep copy array during construction
        this.item = Arrays.copyOf(item, item.length);
    }

    public String[] getItem() {
        return item;
    }

    @Override
    public Items clone() throws CloneNotSupportedException {
        Items copy = (Items) super.clone();
        copy.item = Arrays.copyOf(this.item, this.item.length); // deep copy
        return copy;
    }

    @Override
    public String toString() {
        // join items with a space and add a trailing space to match expected output format
        if (item == null || item.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (String s : item) {
            sb.append(s).append(' ');
        }
        return sb.toString();
    }
}

// Define class Customer
class Customer implements Cloneable {
    private String name;
    private Items items;

    public Customer(String name, Items items) {
        this.name = name;
        this.items = items;
    }

    public Items getItems() {
        return items;
    }

    public void setName(String s) {
        this.name = s;
    }

    @Override
    public Customer clone() throws CloneNotSupportedException {
        Customer copy = (Customer) super.clone();
        copy.items = this.items.clone(); // deep clone Items
        return copy;
    }

    @Override
    public String toString() {
        return name + " " + items.toString();
    }
}


public class Order {
  public static void main(String[] args) throws CloneNotSupportedException{
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(); // number of items
    String[] itm = new String[n];
    for(int i = 0; i < n; i++){
      itm[i] = sc.next(); // list of items
    } 
    var c1 = new Customer("naresh", new Items(itm));
    Customer c2 = c1.clone();   
    c2.getItems().item[0] = sc.next();   //Update first item of c2
    c2.setName("suresh"); //Update name of c2
    System.out.println(c1);
    System.out.println(c2);
  }
}   