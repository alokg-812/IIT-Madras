public class FClass2{
	public static void main(String[] args) {
		Class c = Class.forName("SampleClass");
		Field[] fields1 = c.getFields();
		Field[] fields2 = c.getDeclaredFields();
		for(Field f : fields1) {
			System.out.print(f.getName() + " : ");
			System.out.print(f.getType());
			System.out.println();
			System.out.println("Modifier: " +
					Modifier.toString(f.getModifiers()));

		}
		for(Field f : fields2) {
			System.out.print(f.getName() + " : ");
			System.out.print(f.getType());
			System.out.println();
			System.out.println("Modifier: " +
			Modifier.toString(f.getModifiers()));

		}
	}
 }
