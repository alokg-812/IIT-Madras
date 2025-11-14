public class OuterClass {
    int outerData = 10; // Member of the outer class

    class InnerClass { // Inner class
        void displayInnerData() {
            System.out.println("Accessing outerData from InnerClass: " + outerData);
        }
    }

    void createAndUseInner() {
        InnerClass inner = new InnerClass(); // Create an instance of the inner class
        inner.displayInnerData(); // Call a method of the inner class
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass(); // Create an instance of the outer class
        outer.createAndUseInner(); // Invoke the method that uses the inner class
    }
}