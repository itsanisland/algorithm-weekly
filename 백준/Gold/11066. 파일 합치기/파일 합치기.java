import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] psum = new int[K + 1];
            int[][] dp = new int[K + 1][K + 1];

            // prefix sum
            for (int i = 1; i <= K; i++) {
                psum[i] = psum[i - 1] + Integer.parseInt(st.nextToken());
            }

            // 구간 길이
            for (int len = 1; len < K; len++) {
                for (int start = 1; start + len <= K; start++) {
                    int end = start + len;
            
                    dp[start][end] = Integer.MAX_VALUE;
            
                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(
                            dp[start][end],
                            dp[start][mid] + dp[mid + 1][end]
                            + psum[end] - psum[start - 1]
                        );
                    }
                }
            }

            sb.append(dp[1][K]).append('\n');
        }

        System.out.print(sb);
    }
}