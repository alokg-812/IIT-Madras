interface Queue<E>{
    abstract void add(E element);
    abstract E remove();
    abstract int size();
}

class CircularArrayQueue<E> implements Queue<E>{
    public void add(E element){};
    public E remove(){};
    public int size(){};
}

class LinkedListQueue<E> implements Queue<E>{
    public void add(E element){};
    public E remove(){};
    public int size(){};
}

public class MultipleImplementation {
    public static void main(String[] args) {
        Queue<Date> dateq;
        Queue<String> stringq;
        dateq= new CircularArrayQueue<Date>();
        stringq= new LinkedListQueue<String>();

    }   
}
