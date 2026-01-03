import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] a = new int[n + 1][m + 1];

        // dp[i][j] = (i, j)를 우하단 꼭짓점으로 갖는 가장 큰 정사각형의 한 변의 길이
        int[][] dp = new int[n + 1][m + 1];
        int max = 0;

        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = Integer.parseInt("" + s.charAt(j - 1));
                if (a[i][j] == 1) {
                    dp[i][j] = Math.min(
                        dp[i - 1][j - 1],
                        Math.min(dp[i - 1][j], dp[i][j - 1])
                    ) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.println(max * max);
    }
}