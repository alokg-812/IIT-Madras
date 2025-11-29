import java.io.*;
class Example{
     String str;
     int x;
     Example(String str, int x){
         this.str = str;
         this.x = x;
     }
     public String getStr(){
          return str;
     }
     public int getX(){
          return x;
     }
}
public class Test4{
     public static void main(String[] args) throws Exception{
          ObjectOutputStream os =
                           new ObjectOutputStream(new FileOutputStream("File.ser"));
          os.writeObject(new Example("Moon",1));
          FileInputStream fis=new FileInputStream("File.ser");
          ObjectInputStream ois=new ObjectInputStream(fis);
          Example e=(Example)ois.readObject();
          System.out.print(e.getStr()+"\n"+e.getX());
     } 
}