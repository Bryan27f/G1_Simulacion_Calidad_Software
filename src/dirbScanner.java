
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class dirbScanner {

    public static void main(String[] args) throws FileNotFoundException {
        String url = null;
        HttpURLConnection con = null;
        String wordlist = "common.txt";
        //IMPLEMENT FOR LOOP TO ITERATE OVER DIRBLIST
        String exty = "";

        try (BufferedReader br = new BufferedReader(new FileReader(wordlist))) {
            System.out.println("[*] Wordlist read. Starting the dirb process... [*]\n");
            String line;

            try {
                url = JOptionPane.showInputDialog("Enter the host you want to scan (with protocol - ex. https://www.facebook.com) :");
            } catch (Exception ex) {
                System.out.println("Could not set the URL variable");
            }

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                line = line.replace("\n", "");
                exty = line;
                String EXT = "/" + exty;
                System.out.println(EXT);

                try {
                    url = url + EXT;
                    //
                    URL urly = new URL(url);
                    con = (HttpURLConnection) urly.openConnection();
                } catch (IOException ex) {
                    System.out.println("[-]" + ex);
                }

                try {
                    con.setRequestMethod("GET");
                } catch (ProtocolException ex) {
                    System.out.println("Cant Set method (GET) - " + ex);
                }
                try {
                    if (con.getResponseCode() == 200) {
                        System.out.println("Found 200 on Dir : " + EXT);

                        //System.out.println(con.getResponseMessage());
                    } else {
                        System.out.println("Found " + con.getResponseCode() + " on Dir : " + EXT);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(dirbScanner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(dirbScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
        //HERE

    }
}
