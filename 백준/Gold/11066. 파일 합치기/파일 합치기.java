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

            int[] sum = new int[K + 1];
            int[][] dp = new int[K + 1][K + 1];

            // prefix sum
            for (int i = 1; i <= K; i++) {
                sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
            }

            // dp 초기화
            for (int i = 1; i <= K; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
                dp[i][i] = 0;
            }

            // 구간 길이
            for (int len = 2; len <= K; len++) {
                for (int i = 1; i + len - 1 <= K; i++) {
                    int j = i + len - 1;

                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i][k] + dp[k + 1][j]
                        );
                    }

                    // 마지막에 구간 합을 한 번만 더함
                    dp[i][j] += sum[j] - sum[i - 1];
                }
            }

            sb.append(dp[1][K]).append('\n');
        }

        System.out.print(sb);
    }
}