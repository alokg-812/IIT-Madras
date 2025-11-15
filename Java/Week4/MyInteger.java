interface Comparable { // All methods are implicitly public and abstract
    public abstract int cmp(Comparable s); 
    // return -1 if this < s return 0 if this == s return +1 if this > s 
}

// Class implementing the interface
public class MyInteger implements Comparable {
    private int value;

    public MyInteger(int v) {
        this.value = v;
    }
    // Must provide a concrete implementation for cmp()
    @Override
    public int cmp(Comparable s) {
        if (s instanceof MyInteger) {
            MyInteger other = (MyInteger) s;
            if (this.value < other.value) return -1;
            if (this.value > other.value) return 1;
            return 0;
        }
        // Handle comparison with other types or throw an error
        return 0; 
    }

    public static void main(String[] args) {
        MyInteger a1 = new MyInteger(10);
        MyInteger a2 = new MyInteger(20);
        MyInteger a3 = new MyInteger(30);
        MyInteger a4 = new MyInteger(10);
        System.out.println(a1.cmp(a2));
        System.out.println(a1.cmp(a3));
        System.out.println(a1.cmp(a4));

    }
}