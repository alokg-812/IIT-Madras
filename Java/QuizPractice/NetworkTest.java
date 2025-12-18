package Java.QuizPractice;

class TransmissionException extends Exception {
    public TransmissionException(String msg) {
        super(msg);
    }
}
class PacketException extends Exception {
    public PacketException (String msg) {
        super(msg);
    }
}
public class NetworkTest {
    public static void sendPacket() throws TransmissionException {
        try {
            preparePacket();
        } catch (PacketException e) {
            TransmissionException te;
            new TransmissionException("Transmission failed");
            te.initCause(e);
            throw te;
        }
    }
    public static void preparePacket() throws PacketException {
        throw new PacketException("Packet corrupted");
    }
    public static void main(String[] args) {
        try {
            sendPacket();
        } catch (TransmissionException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
        }
    }
}
