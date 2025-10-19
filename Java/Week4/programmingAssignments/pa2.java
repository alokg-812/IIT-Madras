import java.util.*;

interface Iterator{
    public boolean has_next();
    public Object get_next();
}

class Sequence{
    private final int maxLimit = 80;
    private SeqIterator _iter = null;
    int[] iArr; 
    int size;
//implement the parameterized constructor to initialize size
    private int currentCount = 0;

    // implement the parameterized constructor to initialize size
    public Sequence(int size) {
        // Ensure size does not exceed the max limit
        this.size = Math.min(size, maxLimit);
        this.iArr = new int[this.size];
    }

    // implement addTo(elem) to add an int elem to the sequence 
    public void addTo(int elem) {
        if (currentCount < this.size) {
            this.iArr[currentCount] = elem;
            currentCount++;
        } else {
            System.out.println("Sequence is full. Cannot add " + elem);
        }
    }

    // implement get_Iterator() to return Iterator object
    public Iterator get_Iterator() {
        if (_iter == null) {
            _iter = new SeqIterator();
        }
        return _iter;
    }

    private class SeqIterator implements Iterator{
        // indx tracks the index of the next element to be returned
        int indx; 
        
        public SeqIterator(){
            // Start before the first element (index 0)
            indx = 0; 
        }
        
        // implement has_next()
        @Override
        public boolean has_next() {
            // Check if the index is less than the actual number of elements added
            return indx < currentCount;
        }

        // implement get_next()
        @Override
        public Object get_next() {
            if (has_next()) {
                // Get the current element and then advance the index
                return iArr[indx++]; 
            }
            // In a robust implementation, this would throw a NoSuchElementException
            return null; 
        }
    }
}


class FClass{
    public static void main(String[] args) {
        Sequence sObj = new Sequence(5);
        Scanner sc = new Scanner(System.in); 
        for(int i = 0; i < 5; i++) {
            sObj.addTo(sc.nextInt());
        }
        Iterator i = sObj.get_Iterator();
        while(i.has_next())
            System.out.print(i.get_next() + ", ");
    }
}
