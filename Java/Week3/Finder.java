public class Finder {
    
    public int find(Object[] objarr, Object o) {
        for (int i = 0; i < objarr.length; i++) {
            if (objarr[i].equals(o)) {   // Use equals() for object comparison
                return i;
            }
        }
        return -1;  // If not found
    }

    public static void main(String[] args) {
        Finder f = new Finder();
        String[] names  = {"Alok", "Ram", "Ravi", "Priya"};
        
        int index = f.find(names, "Ravi");
        System.out.println("Found at index: " + index);

        int notFound = f.find(names, "Neha");
        System.out.println("Found at index: " + notFound);
    }
}
