public class AddressExample {
    public static void main(String[] args) {
        Object myObject = new Object();
        String myString = "Hello";
        int myPrimitive = 10;
        int res;
        res = myPrimitive;
        System.out.println("Identity hash code of myObject: " + System.identityHashCode(myObject));
        System.out.println("Identity hash code of myString: " + System.identityHashCode(myString));
        System.out.println("Identity hash code of myString: " + System.identityHashCode(myPrimitive));
        System.out.println("Identity hash code of myString: " + System.identityHashCode(res));
    }
}