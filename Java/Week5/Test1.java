interface Walkable{
	default void showPaceLength() {
		System.out.println("Average pace length : 0.4 meters");
	}
}
class Human implements Walkable{
	double pace_length = 0.85;
	public void showPaceLength() {
		System.out.format("Average pace length : %f meters",pace_length);
 	}
}
class Mammal<T>{
	public String name;
	public T group;
	public Mammal(T obj){
		name = obj.getClass().getSimpleName();
		group = obj;
	}
	public void print() {
		System.out.println(name);
		group.showPaceLength();
	}
}

public class Test1 {
	public static void main(String[] args) {
		Mammal<Human> m = new Mammal<Human>(new Human());
		m.print();
	}
}
