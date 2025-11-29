import java.io.*;
class Example implements Serializable{
      transient String str="Moon";
      transient final int x=1;
}
public class Test5{
      public static void main(String[] args) throws Exception{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream
                                                      ("File.ser"));
            os.writeObject(new Example());
            FileInputStream fis=new FileInputStream("File.ser");
            ObjectInputStream ois=new ObjectInputStream(fis);
            Example e=(Example)ois.readObject();
            System.out.print(e.str+"\n"+e.x);
      }
}