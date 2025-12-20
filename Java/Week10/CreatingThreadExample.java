// Example
class Parallel extends Thread {
    // ... constructor, fields ...
    public void run() { 
        // Code to run concurrently 
    }
}

public class CreatingThreadExample{
    Parallel p = new Parallel(i);
    p.start(); // Initiates run() in a new thread
}