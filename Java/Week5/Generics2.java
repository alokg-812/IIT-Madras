import java.util.ArrayList;

class MyGeneric<T1>{
    int val;
    private T1 t1;
    public MyGeneric(int val, T1 t1){
        this.val = val;
        this.t1 = t1;
    }
    public int getVal(){
        return this.val;
    }
    public T1 getT1(){
        return this.t1;
    }
    public void setVal(int value){
        this.val = value;
    }
    public void setT1(T1 t1){
        this.t1 = t1;
    }
}

public class Generics2 {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList();
        // ArrayList<int> al = new ArrayList(); // this will produce an error
        // al.add("str1");
        al.add(12);
        al.add(689);

        int a = al.get(0);
        System.out.println(a); // output is 689
        MyGeneric<String> g1 = new MyGeneric(21, "MyString");
        String a1 = g1.getT1();
        System.out.println(a1);
    }

}