import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class passCrack {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String wordlist = "test.txt";
        //load data from dict - newline seperated
        String hashtocrack = "21232f297a57a5a743894a0e4a801fc3";
        //load hash to crack
        //try all combinations
        
        //System.out.println(new File(".").getAbsolutePath());
        //admin - 21232f297a57a5a743894a0e4a801fc3 
        //user123 - 6ad14ba9986e3615423dfca256d04e3f 
        
        String algorithm = "MD5";
        boolean verbose = true;
        try (BufferedReader br = new BufferedReader(new FileReader(wordlist))) {
            System.out.println("[*] Wordlist read. Starting the cracking process... [*]\n");
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace("\n", "");
                //System.out.println(line);
                
                //md5 it
                byte[] bytesOfMessage = line.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance(algorithm);
                //System.out.println(md);
                byte[] thedigest = md.digest(bytesOfMessage);
                //System.out.println(thedigest);
                BigInteger bigInt = new BigInteger(1,thedigest);
                String hashtext = bigInt.toString(16);
                if(verbose == true){
                    System.out.println("Trying >" + hashtext);
                }
                
                if(hashtocrack.contains(hashtext)){
                    System.out.println("[+] CRACKED PASSWORD : " + hashtext + " -> " + line);
                }
                
                }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(passCrack.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}
