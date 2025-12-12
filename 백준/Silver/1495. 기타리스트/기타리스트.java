import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[s][0] = true;

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            int v = Integer.parseInt(st.nextToken());

            for (int j = 0; j <= m; j++) {
                if (dp[j][i - 1]) {
                    dp[j][i - 1] = false;

                    if (j - v >= 0) dp[j - v][i] = true;
                    if (j + v <= m) dp[j + v][i] = true;
                }
            }
        }

        int ans = -1;
        for (int i = m; i >= 0; i--) {
            if (dp[i][n]) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }
}