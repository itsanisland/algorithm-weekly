import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (i + j == n) {
                        sb.append(" " + i + " " + j + ","); 
                    }
                }
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            System.out.println("Pairs for " + n + ":" + sb);
        }
    }
}