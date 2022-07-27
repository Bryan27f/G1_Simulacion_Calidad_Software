
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

public class portScanner {

    public static void main(String[] args) throws Exception {

        String host = JOptionPane.showInputDialog("Enter the IP you want to scan:");
        //  127.0.0.1
        int rangeone = Integer.parseInt(JOptionPane.showInputDialog("Enter the port range (starting port):"));
        int rangetwo = Integer.parseInt(JOptionPane.showInputDialog("Enter the port range (ending port):"));
        //rangeone = 20
        //rangetwo = 23

        InetAddress ip = InetAddress.getByName(host);
        for (; rangeone <= rangetwo; rangeone++) {
            //System.out.println(rangeone);
            try {
                Socket socket = new Socket(ip, rangeone);
                System.out.println("[+] Found Opened : " + rangeone + " on " + ip);
                socket.close();
            } catch (IOException e) {
                System.out.print(".");
            }
        }
    }
}
