import java.util.*;
import java.io.*;
import java.security.*;

class Main {
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        SHA256 sha256 = new SHA256();
        
        System.out.println(sha256.encrypt(s));
    }

    public static class SHA256 {

        public String encrypt(String text) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
    
            return bytesToHex(md.digest());
        }
    
        private String bytesToHex(byte[] bytes) {
            StringBuilder builder = new StringBuilder();
            for (byte b : bytes) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        }
    
    }
}