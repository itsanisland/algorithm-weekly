import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine());
        
        int birds = n;
        int k = 1;
        int ans = 0;

        while (birds > 0) {
            if (birds - k >= 0) {
                birds -= k;
                k++;
                ans++;
            } else {
                k = 1;
            }
        }

        System.out.println(ans);
    }
}