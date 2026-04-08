import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dp = new int[10_001];
        dp[0] = 1;

        for (int i = 1; i <= 10_000; i++) {
            dp[i] += dp[i - 1];
        }

        for (int i = 2; i <= 10_000; i++) {
            dp[i] += dp[i - 2];
        }

        for (int i = 3; i <= 10_000; i++) {
            dp[i] += dp[i - 3];
        }
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }

        System.out.println(sb);
    }
}