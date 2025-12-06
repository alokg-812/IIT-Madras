import java.io.*;
import java.util.ArrayList;

class VoterInfo implements Serializable {
    private String name;
    private transient int voterCode;
    //Constructor to initialize instance variables

    public String toString() {
        return "name=" + name + ", voterCode=" + voterCode;
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(voterCode + 2024);
        }
        private void readObject(ObjectInputStream in) throws Exception {
           in.defaultReadObject();
           voterCode = in.readInt() - 2024;
       }
}
public class VoterTest {
     public static void main(String[] args) throws Exception {
           var fos = new FileOutputStream("voter.ser");
           var oos = new ObjectOutputStream(fos);
           VoterInfo v = new VoterInfo("Ayesha", 987654);
           oos.writeObject(v);

           var fis = new FileInputStream("voter.ser");
           var ois = new ObjectInputStream(fis);
           VoterInfo obj = (VoterInfo) ois.readObject();
           System.out.println(obj);
       }
}