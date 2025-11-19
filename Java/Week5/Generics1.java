import java.util.ArrayList;

public class Generics1{
    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        al.add("str1");
        al.add(12);
        al.add(689);

        int a = ((int)al.get(2));
        System.out.println(a); // output is 689
    }
}