import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            int sum = 0;
            int n = Integer.parseInt(br.readLine());

            for (int i = 1; i <= n; i++) {
                if (i % 2 != 0) sum += i;
            }
            
            System.out.println(sum);
        }
    }
}