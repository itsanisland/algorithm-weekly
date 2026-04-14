import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String oct = br.readLine();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < oct.length(); i++) {
            int num = oct.charAt(i) - '0';
            String bin = Integer.toString(num, 2);

            if (i > 0) {
                while (bin.length() < 3) {
                    bin = "0" + bin;
                }
            }
            sb.append(bin);
        }
        
        System.out.println(sb);
    }
}