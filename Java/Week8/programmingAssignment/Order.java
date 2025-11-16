import java.util.*;

// Define class Items
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
        return Arrays.toString(item);
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
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // number of items
        String[] itm = new String[n];

        for (int i = 0; i < n; i++) {
            itm[i] = sc.next();  // items bought by Naresh
        }

        var c1 = new Customer("naresh", new Items(itm));
        Customer c2 = c1.clone();   // deep clone

        c2.getItems().item[0] = sc.next();   // Update first item for Suresh
        c2.setName("suresh");                // Update name

        System.out.println(c1);
        System.out.println(c2);
    }
}
