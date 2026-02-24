import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int ans = 0;
        while (N >= 5) {
            ans += N / 5;
            N /= 5;
        }
            
        System.out.println(ans);
    }
}