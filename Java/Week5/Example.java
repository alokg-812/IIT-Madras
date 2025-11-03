class Dict<K, V> {
    private K key;
    private V value;

    public Dict(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
public class Example {
    public static void main(String[] args) {
    // new Dict<Integer, Float>(1, 30);
    // new Dict<String, int>("John", 18);
    Dict<String, Integer> d = new Dict<String, Integer>("John", 18);
    // new Dict<Integer, Integer>(1, 30);
    }
}
