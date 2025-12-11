import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[n + 1];
        dp[1] = 1; // 1

        // dp[2] = 2; // 00, 11
        // dp[3] = 3; // 001, 100, 111
        // dp[4] = 5; // 0000, 0011, 1001, 1100, 1111
        // dp[5] = 8; // 00001, 00100, 00111, 10000, 10011, 111001, 11100, 11111

        if (n > 1) {
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
            }
        }
        
        System.out.print(dp[n]);
    }
}