import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Package {

    public static String ipBroadcast = "255.255.255.255";
    public static final int PORT = 9;

    public boolean sendWakeOnLanPackage(byte[] macFromDialog){

        try {
            byte[] bytes = new byte[6 + 16 * macFromDialog.length];

            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;  												//F�r magic packet (6 x HEX FF)
            }
            for (int i = 6; i < bytes.length; i += macFromDialog.length) {
                System.arraycopy(macFromDialog, 0, bytes, i, macFromDialog.length);		//F�gt die beiden Arrays zusammen
            }

            InetAddress address = InetAddress.getByName(ipBroadcast);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
